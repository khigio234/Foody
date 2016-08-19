package com.khigio234.pc.core.view;

/**
 * Created by PC on 8/2/2016.
 */
public interface ICallback<T> {

    void onResult(T result);

    void onFailure(Throwable t);

}
