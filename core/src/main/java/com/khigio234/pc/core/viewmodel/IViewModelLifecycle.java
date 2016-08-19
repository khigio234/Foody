package com.khigio234.pc.core.viewmodel;

/**
 * Created by PC on 8/2/2016.
 */
public interface IViewModelLifecycle {

    void onCreate();

    void onStart();

    void onStop();

    void onDestroy();

}
