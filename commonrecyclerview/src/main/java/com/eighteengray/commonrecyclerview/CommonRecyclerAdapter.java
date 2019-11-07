package com.eighteengray.commonrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public final class CommonRecyclerAdapter extends RecyclerView.Adapter {
    private static final String TAG = "WinnowAdapter";

    private static final int EVENT_CREATED = 0x01;
    private static final int EVENT_PRE_BIND = 0x02;
    private static final int EVENT_BIND = 0x03;
    private static final int EVENT_AFTER_BIND = 0x04;
    private static final int EVENT_ATTACHED = 0x05;
    private static final int EVENT_DETACHED = 0x06;
    private static final int EVENT_RECYCLED = 0x07;

    private final List<Object> mDataList;
    private final SparseArray<Grain> mHolderTypes;

    private Map<Class, MappingPolicy> mPolicyMap;
    private List<HolderListener> mHolderListeners;
    private Map<Class, Object> mInterfaceImplMap;
    private WeakReference<View> mDummyView;

    @SafeVarargs
    public static CommonRecyclerAdapter create(Class<? extends CommonViewHolder>... holderClasses) {
        ArrayList<Object> dataList = new ArrayList<>();
        SparseArray<Grain> holderTypes = new SparseArray<>();
        Set<Class> tempSetForCheckDuplicate = new HashSet<>();
        for (Class<? extends CommonViewHolder> holderClass : holderClasses) {
            Class dataClass = parameterizedTypeOf(holderClass);
            Grain grain = new Grain(holderClass, dataClass);
            holderTypes.put(holderClass.hashCode(), grain);

            // mark the duplicate data class.
            if (tempSetForCheckDuplicate.contains(dataClass)) {
                for (int i = 0; i < holderTypes.size(); i++) {
                    Grain duplicate = holderTypes.valueAt(i);
                    if (duplicate.getDataClass() == dataClass) {
                        duplicate.setIsSharingDataClass(true);
                    }
                }
            } else {
                tempSetForCheckDuplicate.add(dataClass);
            }
        }
        return new CommonRecyclerAdapter(dataList, holderTypes);
    }

    private CommonRecyclerAdapter(List<Object> dataList, SparseArray<Grain> holderTypes) {
        this.mDataList = dataList;
        this.mHolderTypes = holderTypes;
    }

    public CommonRecyclerAdapter addMappingPolicy(@NonNull MappingPolicy policy) {
        Class dataClass = parameterizedTypeOf(policy.getClass());
        if (mPolicyMap == null) {
            mPolicyMap = new HashMap<>();
        }
        mPolicyMap.put(dataClass, policy);
        return this;
    }

    public CommonRecyclerAdapter addHolderListener(@NonNull HolderListener listener) {
        if (mHolderListeners == null) {
            mHolderListeners = new ArrayList<>();
        }
        mHolderListeners.add(listener);
        return this;
    }

    public void removeHolderListener(@NonNull HolderListener listener) {
        if (mHolderListeners != null) {
            mHolderListeners.remove(listener);
        }
    }

    public <T> CommonRecyclerAdapter registerInterface(Class<T> interfaceClass, T impl) {
        if (mInterfaceImplMap == null) {
            mInterfaceImplMap = new HashMap<>();
        }
        mInterfaceImplMap.put(interfaceClass, impl);
        return this;
    }

    @SuppressWarnings("unchecked")
    <T> T getInterfaceImpl(Class<T> interfaceClass) {
        if (mInterfaceImplMap != null) {
            return (T) mInterfaceImplMap.get(interfaceClass);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        Object data = mDataList.get(position);
        if (data == null) {
            throw new RuntimeException("Data at position " + position + " is null!");
        }
        Class<?> dataClass = data.getClass();

        // 1.First find unique HolderClass <--> DataClass
        for (int i = 0; i < mHolderTypes.size(); i++) {
            Grain grain = mHolderTypes.valueAt(i);
            // here we use == but not instanceof,because Object type will covers all the others types.
            if (grain.getDataClass() == dataClass) {
                grain.setData(data);
                return getHolderClass(grain).hashCode();
            }
        }

        // 2.If not found, find with closest superclass
        Grain closestGrain = null;
        for (int i = 0; i < mHolderTypes.size(); i++) {
            Grain grain = mHolderTypes.valueAt(i);
            Class<?> grainDataClass = grain.getDataClass();
            if (grainDataClass.isAssignableFrom(dataClass)) {
                if (closestGrain == null) {
                    closestGrain = grain;
                } else {
                    closestGrain = grainDataClass.isAssignableFrom(closestGrain.getDataClass()) ? closestGrain : grain;
                }
            }
        }
        if (closestGrain != null) {
            closestGrain.setData(data);
            return getHolderClass(closestGrain).hashCode();
        }
        throw new RuntimeException("Type " + dataClass.getSimpleName() + " has no preset holder," +
                "please add it when create adapter.");
    }

    private Class<? extends CommonViewHolder> getHolderClass(Grain grain) {
        if (grain.isSharingDataClass()) {
            Class<? extends CommonViewHolder> classFromPolicyMap = getHolderClassFromPolicyMap(grain);
            if (classFromPolicyMap != null) {
                return classFromPolicyMap;
            } else {
                throw new RuntimeException("Type " + grain.getData().getClass().getSimpleName() + " has multi holders," +
                        "please add a PolicyMap when create adapter.");
            }
        } else {
            return grain.getHolderClass();
        }
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private Class<? extends CommonViewHolder> getHolderClassFromPolicyMap(Grain grain) {
        if (mPolicyMap != null && mPolicyMap.containsKey(grain.getDataClass())) {
            MappingPolicy mappingPolicy = mPolicyMap.get(grain.getDataClass());
            return mappingPolicy.map(grain.getData());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Grain grain = mHolderTypes.get(viewType);
        try {
            Context context = parent.getContext();
            Constructor<? extends CommonViewHolder> holderConstructor = grain.getHolderClass().getDeclaredConstructor(View.class);
            if (grain.getLayoutRes() == 0) {
                if (mDummyView == null || mDummyView.get() == null) {
                    mDummyView = new WeakReference<>(new View(context));
                }
                View view = mDummyView.get();
                view.setTag(grain);
                try {
                    // to get layout res.
                    holderConstructor.newInstance(view);
                } catch (Exception e) {
                    // Ignore
                }
            }
            if (grain.getLayoutRes() == 0) {
                throw new RuntimeException("Please implements the getLayoutRes function in " + grain.getHolderClass().getSimpleName());
            }
            View itemView = LayoutInflater.from(context).inflate(grain.getLayoutRes(), parent, false);
            CommonViewHolder holder = holderConstructor.newInstance(itemView);

            holder.setAdapter(this);
            holder.setData(grain.getData());

            dispatchHolderEvent(holder, EVENT_CREATED);
            return holder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object data = mDataList.get(position);
        if (holder instanceof CommonViewHolder) {
            CommonViewHolder h = (CommonViewHolder) holder;
            h.onPreBind(data);
            dispatchHolderEvent(h, EVENT_PRE_BIND);

            h.setData(data);
            h.onBindData(data);
            dispatchHolderEvent(h, EVENT_BIND);

            h.onAfterBind(data);
            dispatchHolderEvent(h, EVENT_AFTER_BIND);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof CommonViewHolder) {
            CommonViewHolder h = (CommonViewHolder) holder;
            h.onHolderAttached();
            dispatchHolderEvent(h, EVENT_ATTACHED);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof CommonViewHolder) {
            CommonViewHolder h = (CommonViewHolder) holder;
            h.onHolderDetached();
            dispatchHolderEvent(h, EVENT_DETACHED);
        }
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof CommonViewHolder) {
            CommonViewHolder h = (CommonViewHolder) holder;
            h.onHolderRecycled();
            dispatchHolderEvent(h, EVENT_RECYCLED);
        }
    }

    @SuppressWarnings("unchecked")
    private void dispatchHolderEvent(CommonViewHolder holder, int event) {
        if (mHolderListeners == null) {
            return;
        }
        for (HolderListener listener : mHolderListeners) {
            if (listener.holderClass.isInstance(holder)) {
                switch (event) {
                    case EVENT_CREATED:
                        listener.onHolderCreated(holder);
                        break;
                    case EVENT_PRE_BIND:
                        listener.onHolderPreBind(holder);
                        break;
                    case EVENT_BIND:
                        listener.onHolderBind(holder);
                        break;
                    case EVENT_AFTER_BIND:
                        listener.onHolderAfterBind(holder);
                        break;
                    case EVENT_ATTACHED:
                        listener.onHolderAttached(holder);
                        break;
                    case EVENT_DETACHED:
                        listener.onHolderDetached(holder);
                        break;
                    case EVENT_RECYCLED:
                        listener.onHolderRecycled(holder);
                        break;
                        default:
                }
            }
        }
    }

    @NonNull
    private static Class parameterizedTypeOf(Class targetClass) {
        try {
            Type genericSuperclass = targetClass.getGenericSuperclass();

            while (genericSuperclass instanceof Class) {
                genericSuperclass = ((Class) genericSuperclass).getGenericSuperclass();
            }
            if (genericSuperclass instanceof ParameterizedType) {
                String className = ((ParameterizedType) genericSuperclass)
                        .getActualTypeArguments()[0].toString().split(" ")[1];
                return Class.forName(className);
            }
            Log.e(TAG, targetClass.getSimpleName() + "'s ParameterizedType class fallback to Object.class");
            return Object.class;
        } catch (@NonNull Exception e) {
            e.printStackTrace();
            return Object.class;
        }
    }

    public final List<Object> getDataList() {
        return mDataList;
    }

    // you should assign the T type,otherwise it will fallback to Object
    public abstract static class MappingPolicy<T> {

        public abstract Class<? extends CommonViewHolder> map(@NonNull T data);

    }

    public abstract static class HolderListener<H extends CommonViewHolder> {
        private Class holderClass = parameterizedTypeOf(getClass());

        protected void onHolderCreated(@NonNull H holder) {
        }

        protected void onHolderPreBind(@NonNull H holder) {
        }

        protected void onHolderBind(@NonNull H holder) {
        }

        protected void onHolderAfterBind(@NonNull H holder) {
        }

        protected void onHolderAttached(@NonNull H holder) {
        }

        protected void onHolderDetached(@NonNull H holder) {
        }

        protected void onHolderRecycled(@NonNull H holder) {
        }
    }

    public final void addItem(Object data) {
        addItem(getDataList().size(), data);
    }

    public final void addItem(int index, Object data) {
        if (index > -1 && index <= getDataList().size()) {
            getDataList().add(index, data);
            notifyItemInserted(index);
        }
    }

    public final void addItems(List dataList) {
        addItems(getDataList().size(), dataList);
    }

    public final void addItems(int index, List dataList) {
        if (index > -1 && index <= getDataList().size() && dataList != null) {
            getDataList().addAll(index, dataList);
            notifyDataSetChanged();
        }
    }

    public final void removeItem(Object data) {
        int index = getDataList().indexOf(data);
        if (index != -1) {
            getDataList().remove(data);
            notifyItemRemoved(index);
        }
    }

    public final void removeAll() {
        getDataList().clear();
        notifyDataSetChanged();
    }

    public final void replaceItem(Object origin, Object newData) {
        int index = getDataList().indexOf(origin);
        if (index != -1 && newData != null) {
            getDataList().set(index, newData);
            notifyItemChanged(index);
        }
    }

    public final void updateItem(Object data) {
        int index = getDataList().indexOf(data);
        if (index != -1) {
            notifyItemChanged(index);
        }
    }

    public final void updateAll(List dataList) {
        if (dataList != null) {
            getDataList().clear();
            getDataList().addAll(dataList);
            notifyDataSetChanged();
        }
    }
}
