package com.khigio234.pc.core.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.khigio234.pc.core.viewmodel.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by PC on 8/2/2016.
 */
public abstract class BaseActivity< B extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity{

    //region Properties

    private Toolbar mToolbar;

    protected B mViewDataBinding;

    @Inject
    protected V mBaseViewModel;

    //endregion

    //region Lifecycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mBaseViewModel != null) {
            mBaseViewModel.onCreate();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mBaseViewModel != null) {
            mBaseViewModel.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBaseViewModel != null) {
            mBaseViewModel.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mBaseViewModel != null) {
            mBaseViewModel.onDestroy();
        }
    }

    //endregion

    //region Protected Methods

    protected void setBindingContentView(int layoutResId, int variableId) {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutResId);
        mViewDataBinding.setVariable(variableId, mBaseViewModel);
    }

    protected void setToolbar(int resId) {
        mToolbar = (Toolbar) findViewById(resId);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    //endregion

}
