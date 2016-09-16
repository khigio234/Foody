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

    //endregion

    //region Getter and Setter

    /**
     * @return
     */
    protected INavigator getNavigator() {
        return mNavigator;
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

    }

    //endregion

    //region Protected method

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
