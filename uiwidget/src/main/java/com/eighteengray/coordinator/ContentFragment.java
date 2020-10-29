package com.eighteengray.coordinator;

import android.support.v7.widget.RecyclerView;

import com.eighteengray.commonrecyclerview.BaseListFragment;
import com.eighteengray.commonrecyclerview.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;


public class ContentFragment extends BaseListFragment {


    @Override
    public RecyclerView.LayoutManager setLayoutManager() {
        return null;
    }

    @Override
    public void addHolderClasses(List<Class<? extends CommonViewHolder>> originList) {
        originList.add(ContentViewHolder.class);
    }

    @Override
    public void refreshData() {
        List<String> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i + "refresh");
        }
        notifyRefreshSuccess(list, true);
    }

    @Override
    public void loadMoreData() {
        List<String> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i + "loadMore");
        }
        notifyLoadMoreSuccess(list, true);
    }
}
