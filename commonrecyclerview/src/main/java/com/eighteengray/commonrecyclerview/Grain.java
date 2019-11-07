package com.eighteengray.commonrecyclerview;


class Grain {
    private Class<? extends CommonViewHolder> mHolderClass;
    private Class<?> mDataClass;

    private int mLayoutRes;
    private Object mData;
    // flag for check potential exception when one data type has multi holder classes but
    // not assign the mapping rule
    private boolean mIsSharingDataClass;

    Grain(Class<? extends CommonViewHolder> mHolderClass, Class<?> mDataClass) {
        this.mHolderClass = mHolderClass;
        this.mDataClass = mDataClass;
    }

    // to make the data available in the holder's Constructor.
    void setData(Object data) {
        this.mData = data;
    }

    void setLayoutRes(int mLayoutRes) {
        this.mLayoutRes = mLayoutRes;
    }

    Object getData() {
        return mData;
    }

    Class<? extends CommonViewHolder> getHolderClass() {
        return mHolderClass;
    }

    Class<?> getDataClass() {
        return mDataClass;
    }

    int getLayoutRes() {
        return mLayoutRes;
    }


    boolean isSharingDataClass() {
        return mIsSharingDataClass;
    }

    void setIsSharingDataClass(boolean isSharingDataClass) {
        this.mIsSharingDataClass = isSharingDataClass;
    }
}
