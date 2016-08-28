package com.khigio234.pc.core.model.services.clouds;

/**
 * Created by PC on 8/28/2016.
 */
public abstract class BaseCloudService<T> {

    //region Properties

    protected T mICloudService;

    //endregion

    //region Constructor

    public T getICloudService() {
        return mICloudService;
    }

    //endregion
}
