package com.eighteengray.commonrecyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.eighteengray.basecomponent.basefragment.BaseFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class BaseListFragment extends BaseFragment {

    View view;

    @BindView(R2.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipe_refresh_layout;

    @BindView(R2.id.recycler_view)
    protected RecyclerView recycler_view;
    private RecyclerView.LayoutManager layoutManager;
    protected CommonRecyclerAdapter mAdapter;

    @BindView(R2.id.progress_bar)
    protected ContentLoadingProgressBar progress_bar;

    @BindView(R2.id.rl_empty)
    protected RelativeLayout rl_empty;

    private boolean mHasLazyLoaded = false;
    private boolean mDoLazyLoadLater = false;
    private boolean isLoadingData;
    private boolean loadingMoreEnabled = true;
    private int limitNumberToCallLoadMore = 1;
    private boolean isEnd;

    public static int LAYOUT_MANAGER_LINEAR = 1;
    public static int LAYOUT_MANAGER_GRID = 2;
    public static int LAYOUT_MANAGER_STAGGERED_HORIZONTAL = 3;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mDoLazyLoadLater && isVisibleToUser && !mHasLazyLoaded) {
            if (getView() != null) {
                onLazyLoad();
            } else {
                mDoLazyLoadLater = true;
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_base_list, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initView()
    {
        swipe_refresh_layout.setColorSchemeColors(getResources().getColor(R.color.primary), getResources().getColor(R.color.accent), getResources().getColor(R.color.primary_text), getResources().getColor(R.color.text));
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refreshData();
            }
        });


        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager = setLayoutManager();
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(mAdapter);

        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int state) {
                if (state == RecyclerView.SCROLL_STATE_IDLE && !isLoadingData && loadingMoreEnabled) {
                    int lastVisibleItemPosition;
                    if (layoutManager instanceof GridLayoutManager) {
                        lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                        lastVisibleItemPosition = findMax(into);
                    } else {
                        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    }

                    if (layoutManager.getChildCount() > 0
                            && lastVisibleItemPosition >= mAdapter.getItemCount() - limitNumberToCallLoadMore
                            && !isEnd
                            && !isLoadingData) {
                        loadMoreData();
                    }
                }
            }
        });
        List<Class<? extends CommonViewHolder>> classes = addHolderClass();
        Class<? extends CommonViewHolder>[] array = classes.toArray(new Class[]{});
        mAdapter = CommonRecyclerAdapter.create(array);
        recycler_view.setAdapter(mAdapter);
        refreshData();
    }

    private static int findMax(int[] array) {
        if (array == null || array.length <= 0) {
            return Integer.MIN_VALUE;
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            int v = array[i];
            if (v > max) {
                max = v;
            }
        }
        return max;
    }

    private List<Class<? extends CommonViewHolder>> addHolderClass() {
        List<Class<? extends CommonViewHolder>> baseList = new ArrayList<>();
        addHolderClasses(baseList);
        return baseList;
    }

    protected void onLazyLoad() {
        mHasLazyLoaded = true;
        mDoLazyLoadLater = false;
        refreshData();
    }



    // abstract方法
    public abstract RecyclerView.LayoutManager setLayoutManager();
    public abstract void addHolderClasses(List<Class<? extends CommonViewHolder>> originList);
    public abstract void refreshData();
    public abstract void loadMoreData();

    // 继承方法

    // public方法
    public CommonRecyclerAdapter getAdapter() {
        return mAdapter;
    }

    protected void notifyRefreshSuccess(@NonNull List<?> result, boolean isEnd) {
        this.isEnd = isEnd;
        mAdapter.updateAll(result);
    }

    protected void notifyRefreshFailed(Throwable throwable) {
        isEnd = false;
        if (mAdapter.getDataList().size() > 0) {
            Toast.makeText(getActivity(), "数据无法加载,请稍后重试！", Toast.LENGTH_SHORT);
        } else {
            rl_empty.setVisibility(View.VISIBLE);
            rl_empty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshData();
                }
            });
        }
    }

    protected void notifyLoadMoreFailed(Throwable throwable) {
        isEnd = false;
        Toast.makeText(getActivity(), "数据无法加载,请稍后重试！", Toast.LENGTH_SHORT);
    }

    protected void notifyLoadMoreSuccess(@NonNull List<?> result, boolean isEnd) {
        this.isEnd = isEnd;
        mAdapter.addItems(result);
    }

}
