package com.khigio234.pc.core.model.responses;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by PC on 8/1/2016.
 */
public class APIResponse<E> {

    //region Properties

    @SerializedName("success")
    private boolean mSuccess;

    @SerializedName("data")
    private E mData;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("last_sync_timestamp")
    private Date mLastSyncTimestamp;

    //endregion

    //region Getter and Setter

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        mSuccess = success;
    }

    public E getData() {
        return mData;
    }

    public void setData(E data) {
        mData = data;
    }

    public Date getLastSyncTimestamp() {
        return mLastSyncTimestamp;
    }

    public void setLastSyncTimestamp(Date lastSyncTimestamp) {
        mLastSyncTimestamp = lastSyncTimestamp;
    }

    //endregion

    //region Override method

}
