package com.khigio234.pc.core.viewmodel;

import com.khigio234.pc.core.view.INavigator;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by PC on 8/2/2016.
 */
public class MainViewModel extends BaseViewModel{

    //region Constructor

    public MainViewModel(INavigator navigator) {
        super(navigator);
    }

    //endregion

    //region Getter and Setter

    @Override
    protected INavigator getNavigator() {
        return super.getNavigator();
    }

    @Override
    protected EventBus getEventBus() {
        return super.getEventBus();
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    //endregion
}
