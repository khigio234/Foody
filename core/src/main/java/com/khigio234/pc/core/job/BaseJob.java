package com.khigio234.pc.core.job;

import android.support.annotation.IntDef;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;

import org.greenrobot.eventbus.EventBus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by PC on 9/2/2016.
 */
public abstract class BaseJob extends Job{

    //region Properties

    public static final int UI_HIGH = 10;
    public static final int BACKGROUND = 1;

    //endregion

    //region Constructor

    public BaseJob(Params params) {
        super(params);
    }

    //endregion

    //region Public method

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({UI_HIGH,BACKGROUND})
    public @interface Priority {

    }

    //endregion

    //region Protected methods

    protected boolean shouldRetry(Throwable throwable) {
        if (throwable instanceof NetworkException) {
            NetworkException exception = (NetworkException) throwable;
            return exception.shouldRetry();
        }
        return true;
    }

    protected static final void post(Object object) {
        EventBus.getDefault().post(object);
    }

    protected static final void postSticky(Object object) {
        EventBus.getDefault().postSticky(object);
    }

    //endregion
}
