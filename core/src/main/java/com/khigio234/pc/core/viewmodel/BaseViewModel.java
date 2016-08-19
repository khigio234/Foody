package com.khigio234.pc.core.viewmodel;

import android.databinding.BaseObservable;

import com.khigio234.pc.core.view.INavigator;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by PC on 8/2/2016.
 */
public abstract class BaseViewModel extends BaseObservable implements IViewModelLifecycle {

    //region Properties

    private INavigator mNavigator;

    private EventBus mEventBus;

    //endregion

    //region Getter and Setter

    /**
     * @return
     */
    protected INavigator getNavigator() {
        return mNavigator;
    }

    protected EventBus getEventBus() {
        if (mEventBus == null) {
            mEventBus = EventBus.getDefault();
        }
        return mEventBus;
    }

    //endregion

    //region Constructors

    /**
     * @param navigator
     */
    protected BaseViewModel (INavigator navigator) {
        mNavigator = navigator;
    }

    protected BaseViewModel() {
        super();
    }

    //endregion

    //region IViewModelLifecycle implements

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mEventBus = null;
    }

    //endregion

}
