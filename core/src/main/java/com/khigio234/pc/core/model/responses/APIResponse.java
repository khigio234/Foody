package com.khigio234.pc.core.model.responses;

import com.google.gson.annotations.SerializedName;

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

    //endregion

}
