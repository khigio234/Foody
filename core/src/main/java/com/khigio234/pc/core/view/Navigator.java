package com.khigio234.pc.core.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.SparseArray;

/**
 * Created by PC on 8/2/2016.
 */
public class Navigator implements INavigator {

    //region Properties

    private BaseApplication mBaseApplication;

    private BusyIndicator mBusyIndicator;

    private SparseArray<Class> mPages = new SparseArray<>();

    //endregion

    //region Constructors

    public Navigator(BaseApplication baseApplication) {
        mBaseApplication = baseApplication;
    }

    //endregion

    //region INavigator implement

    @Override
    public void configure(@Constants.PageKey int pageKey, Class pageClass) {
        mPages.put(pageKey, pageClass);
    }

    @Override
    public BaseApplication getApplication() {
        return mBaseApplication;
    }

    @Override
    public int getCurrentKey() {
        Class currentClass = mBaseApplication.getCurrentActivity().getClass();

        int valueIndex = mPages.indexOfValue(currentClass);
        @Constants.PageKey int pageKey = mPages.keyAt(valueIndex);
        return pageKey;
    }

    @Override
    public void goBack() {
        if(mBaseApplication.isCurrentActivityAvailable()){
            ActivityCompat.finishAfterTransition(mBaseApplication.getCurrentActivity());
        }
    }

    @Override
    public void navigateTo(@Constants.PageKey int pageKey) {
        if(mBaseApplication.isCurrentActivityAvailable()){
            Activity currentActivity = mBaseApplication.getCurrentActivity();
            Class targetClass = mPages.get(pageKey);

            Intent intent = new Intent(currentActivity, targetClass);
            ActivityCompat.startActivity(currentActivity, intent, null);
        }
    }

    @Override
    public void showMessage(String title, String message, String buttonText, final ICallback callback) {
        if (mBaseApplication.isCurrentActivityAvailable()){
            Activity currentActivity = mBaseApplication.getCurrentActivity();

            new AlertDialog.Builder(currentActivity).setNegativeButton(buttonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (callback != null){
                        callback.onResult(false);
                    }
                    dialogInterface.dismiss();
                }
            }).show();
        }
    }

    @Override
    public void showMessage(String title, String message, String buttonConfirmText, String buttonCancelText, final ICallback callback) {
        if (mBaseApplication.isCurrentActivityAvailable()){
            Activity currentActivity = mBaseApplication.getCurrentActivity();

            new AlertDialog.Builder(currentActivity).setPositiveButton(buttonConfirmText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (callback != null){
                        callback.onResult(true);
                    }
                    dialogInterface.dismiss();
                }
            }).setPositiveButton(buttonCancelText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (callback != null){
                        callback.onResult(false);
                    }
                    dialogInterface.dismiss();
                }
            }).show();
        }
    }

    @Override
    public void showBusyIndicator(String title) {
        if (mBaseApplication.isCurrentActivityAvailable()){
            mBusyIndicator = new BusyIndicator(mBaseApplication.getCurrentActivity());
            mBusyIndicator.setTitle(title);
            mBusyIndicator.show();
        }
    }

    @Override
    public void hideBusyIndicator() {
        if (isBusyIndicatorShowing()){
            try {
                mBusyIndicator.dismiss();
            } catch (Exception e) {
                Log.e(Navigator.class.getSimpleName(), e.getMessage());
            } finally {
                mBusyIndicator = null;
            }
        }
    }

    @Override
    public boolean isBusyIndicatorShowing() {
        return mBusyIndicator != null && mBusyIndicator.isShowing();
    }

    //endregion
}
