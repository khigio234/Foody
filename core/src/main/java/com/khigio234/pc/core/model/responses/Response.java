package com.khigio234.pc.core.model.responses;

/**
 * Created by PC on 8/1/2016.
 */
public abstract class Response {

    //region Properties

    private String mMessage;

    private int mStatusCode;

    //endregion

    //region Getter and Setter

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int statusCode) {
        mStatusCode = statusCode;
    }

    //endregion

}
