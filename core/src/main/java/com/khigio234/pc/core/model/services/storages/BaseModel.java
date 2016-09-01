package com.khigio234.pc.core.model.services.storages;

import io.realm.Realm;

/**
 * Created by PC on 8/8/2016.
 */
public abstract class BaseModel {

    //region Properties

    protected Realm mRealm;

    //endregion

    //region Constructor

    public BaseModel(Realm realm) {
        mRealm = realm;
    }

    //endregion
}
