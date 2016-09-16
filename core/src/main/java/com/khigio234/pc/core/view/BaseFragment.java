package com.khigio234.pc.core.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.khigio234.pc.core.viewmodel.BaseViewModel;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by PC on 8/9/2016.
 */
public class BaseFragment<B extends ViewDataBinding, V extends BaseViewModel> extends Fragment {


    //region Properties

    protected B mViewDataBinding;

    @Inject
    protected V mBaseViewModel;

    //endregion

    //region Lifecycle

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mBaseViewModel != null) {
            mBaseViewModel.onCreate();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mBaseViewModel != null) {
            mBaseViewModel.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mBaseViewModel != null) {
            mBaseViewModel.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mBaseViewModel != null) {
            mBaseViewModel.onDestroy();
        }
    }

    //endregion

    //region Protected methods

    protected void setBindingContentView(LayoutInflater inflater, @Nullable ViewGroup container, int layoutResId, int variableId) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false);
        mViewDataBinding.setVariable(variableId, mBaseViewModel);
    }

    protected final void register() {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    protected final void unregister() {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }
    }

    protected static final void post(Object object) {
        EventBus.getDefault().post(object);
    }

    protected static final void postSticky(Object object) {
        EventBus.getDefault().postSticky(object);
    }

    //endregion
}
