package com.khigio234.pc.core.event;

/**
 * Created by PC on 9/2/2016.
 */
public class FetchedCategoryEvent {

    //region Properties

    private boolean mSuccess;

    private String mMessage;

    //endregion

    //region Getter and Setter

    public boolean isSuccess() {
        return mSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

    //endregion

    //region Constructor

    public FetchedCategoryEvent(boolean success) {
        mSuccess = success;
    }

    public FetchedCategoryEvent(boolean success, String message) {
        mSuccess = success;
        mMessage = message;
    }

    //endregion
}
