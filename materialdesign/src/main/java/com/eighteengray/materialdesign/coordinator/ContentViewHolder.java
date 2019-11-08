package com.eighteengray.materialdesign.coordinator;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.eighteengray.commonrecyclerview.CommonViewHolder;
import com.eighteengray.materialdesign.R;


public class ContentViewHolder extends CommonViewHolder<String> {

    TextView text_view;

    public ContentViewHolder(View itemView) {
        super(itemView);
        text_view = itemView.findViewById(R.id.text_view);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_content;
    }

    @Override
    protected void onBindData(@NonNull String s) {
        text_view.setText(s);
    }
}
