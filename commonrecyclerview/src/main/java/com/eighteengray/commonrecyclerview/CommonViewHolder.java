package com.eighteengray.commonrecyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;


public abstract class CommonViewHolder<DATA> extends RecyclerView.ViewHolder {

    private DATA data;
    private CommonRecyclerAdapter adapter;
    protected boolean isHolderDetached = true;

    public CommonViewHolder(View itemView) {
        super(itemView);
        if (itemView.getTag() instanceof Grain) {
            ((Grain) itemView.getTag()).setLayoutRes(getLayoutRes());
        }
    }

    @LayoutRes
    protected abstract int  getLayoutRes();

    protected abstract void onBindData(@NonNull DATA data);

    void setAdapter(CommonRecyclerAdapter winnowAdapter) {
        this.adapter = winnowAdapter;
    }

    @NonNull
    public final DATA getData() {
        return data;
    }

    public final CommonRecyclerAdapter getAdapter() {
        return adapter;
    }

    protected void onPreBind(@NonNull DATA data) {

    }

    protected void onAfterBind(@NonNull DATA data) {

    }

    @CallSuper
    protected void onHolderAttached() {
        this.isHolderDetached = false;
    }

    @CallSuper
    protected void onHolderDetached() {
        this.isHolderDetached = true;
    }

    protected void onHolderRecycled() {

    }

    protected final Context getContext() {
        return itemView.getContext();
    }

    protected final void setData(DATA data) {
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    protected final <T extends View> T findViewById(@IdRes int id) {
        return (T) itemView.findViewById(id);
    }

    @IntRange(from = 0)
    @Px
    protected final int dp2px(@FloatRange(from = 0.0F) float dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp + 0.5F);
    }

    @IntRange(from = 0)
    @Px
    protected final int sp2px(@FloatRange(from = 0.0F) float sp) {
        return (int) (getContext().getResources().getDisplayMetrics().scaledDensity * sp + 0.5F);
    }

    @ColorInt
    protected final int getColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(getContext(), colorRes);
    }

    @Nullable
    protected final Drawable getDrawable(@DrawableRes int drawableRes) {
        return ContextCompat.getDrawable(getContext(), drawableRes);
    }

    @NonNull
    protected final String getString(@StringRes int stringRes) {
        return getContext().getString(stringRes);
    }

    @NonNull
    protected final String getString(@StringRes int stringRes, @NonNull Object... formatArgs) {
        return getContext().getString(stringRes, formatArgs);
    }

    @NonNull
    protected final List getAdapterDataList() {
        return getAdapter().getDataList();
    }

    protected final int getIndex() {
        return getAdapterDataList().indexOf(getData());
    }

    protected final <T> T getInterfaceImpl(Class<T> interfaceClass){
        return getAdapter().getInterfaceImpl(interfaceClass);
    }
}
