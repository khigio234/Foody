package com.khigio234.pc.core.view;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by PC on 8/2/2016.
 */
public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {

    //region Properties

    private Activity mCurrentActivity;

    //endregion

    //region Getter and Setter

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public boolean isCurrentActivityAvailable() {
        return mCurrentActivity != null;
    }

    //endregion

    //region Application Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getApplicationContext()).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    //endregion

    //region ActivityLifecycleCallbacks implement

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if(mCurrentActivity != activity){
            mCurrentActivity = activity;
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if(mCurrentActivity != activity){
            mCurrentActivity = activity;
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    //endregion
}
