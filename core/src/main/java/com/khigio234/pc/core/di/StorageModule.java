package com.khigio234.pc.core.di;

import com.khigio234.pc.core.model.services.storages.CategoryModel;
import com.khigio234.pc.core.model.services.storages.RestaurantModel;
import com.khigio234.pc.core.model.services.storages.UserModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by PC on 8/2/2016.
 */
@Module
public class StorageModule {

    //region Provide methods

    @Provides
    @Singleton
    public Realm providesRealm() {
        return Realm.getDefaultInstance();
    }


    @Provides
    @Singleton
    public CategoryModel providesCategoryStorageService(Realm realm) {
        return new CategoryModel(realm);
    }

    @Provides
    @Singleton
    public RestaurantModel providesRestaurantStorageService(Realm realm) {
        return new RestaurantModel(realm);
    }

    @Provides
    @Singleton
    public UserModel providesUserStorageService(Realm realm) {
        return new UserModel(realm);
    }

    //endregion
}
