package com.khigio234.pc.core.di;

import com.khigio234.pc.core.model.services.storages.CategoryStorageService;
import com.khigio234.pc.core.model.services.storages.RestaurantStorageService;
import com.khigio234.pc.core.view.INavigator;
import com.khigio234.pc.core.viewmodel.CategoryViewModel;
import com.khigio234.pc.core.viewmodel.MainViewModel;
import com.khigio234.pc.core.viewmodel.RestaurantViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by PC on 8/2/2016.
 */

@Module(includes = {CloudModule.class, StorageModule.class})
public class ViewModelModule {

    //region Provide methods

    @Provides
    @Singleton
    MainViewModel providesMainViewModel(INavigator navigator) {
        return new MainViewModel(navigator);
    }

    @Provides
    @Singleton
    public CategoryViewModel providesCategoryViewModel(INavigator navigator, CategoryStorageService categoryStorageService) {
        return new CategoryViewModel(navigator, categoryStorageService);
    }

    @Provides
    @Singleton
    public RestaurantViewModel providesRestaurantViewModel(INavigator navigator, RestaurantStorageService restaurantStorageService) {
        return new RestaurantViewModel(navigator, restaurantStorageService);
    }

    //endregion

}
