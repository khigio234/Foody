package com.khigio234.pc.core.view;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by PC on 8/2/2016.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    //region Properties

    private ViewDataBinding mViewDataBinding;

    //endregion

    //region Getter and Setter

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

    public void setViewDataBinding(ViewDataBinding viewDataBinding) {
        mViewDataBinding = viewDataBinding;
    }


    //endregion

    //region Constructors

    public ViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());

        mViewDataBinding = viewDataBinding;
    }

    //endregion
}
