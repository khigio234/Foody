package com.khigio234.pc.core.viewmodel;

import android.databinding.Bindable;

import com.khigio234.pc.core.BR;
import com.khigio234.pc.core.model.entities.User;
import com.khigio234.pc.core.view.INavigator;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by PC on 8/2/2016.
 */
public class MainViewModel extends BaseViewModel{

    //region Properties

    private static final String TAG = "MainViewModel";

    private User mUser;

    //endregion

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

    @Bindable
    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;

        notifyPropertyChanged(BR.user);
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        getEventBus().register(this);
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

        getEventBus().unregister(this);
    }


    //endregion
}
