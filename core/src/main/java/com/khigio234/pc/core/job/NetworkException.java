package com.khigio234.pc.core.job;

/**
 * Created by PC on 9/2/2016.
 */
public class NetworkException extends RuntimeException {

    //region Properties

    private final int mErrorCode;

    //endregion

    //region Construction

    public NetworkException(int errorCode) {
        mErrorCode = errorCode;
    }

    //endregion

    //region Public method

    public boolean shouldRetry() {
        return mErrorCode < 400 || mErrorCode > 499;
    }

    //endregion
}
