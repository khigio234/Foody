package com.khigio234.pc.core.view;

import android.support.v7.widget.RecyclerView;

import com.khigio234.pc.core.viewmodel.BaseViewModel;

/**
 * Created by PC on 8/2/2016.
 */
public abstract class BaseRecyclerViewAdapter <V extends BaseViewModel, T> extends RecyclerView.Adapter {

    //region Properties

    protected V mBaseViewModel;

    protected T mData;

    //endregion

    //region Getter and Setter

    public void setBaseViewModel(V baseViewModel) {
        mBaseViewModel = baseViewModel;
    }

    public void setData(T data) {
        mData = data;

        notifyDataSetChanged();
    }

    public T getData() {
        return mData;
    }

    //endregion

}
