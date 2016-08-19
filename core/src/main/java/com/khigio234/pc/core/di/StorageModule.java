package com.khigio234.pc.core.di;

import com.khigio234.pc.core.model.services.storages.CategoryStorageService;
import com.khigio234.pc.core.model.services.storages.RestaurantStorageService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by PC on 8/2/2016.
 */
@Module(includes = {AppModule.class})
public class StorageModule {

    //region Provide methods

    @Provides
    @Singleton
    public Realm providesRealm() {
        return Realm.getDefaultInstance();
    }


    @Provides
    @Singleton
    public CategoryStorageService providesCategoryStorageService(Realm realm) {
        return new CategoryStorageService(realm);
    }

    @Provides
    @Singleton
    public RestaurantStorageService providesRestaurantStorageService(Realm realm) {
        return new RestaurantStorageService(realm);
    }

    //endregion
}
