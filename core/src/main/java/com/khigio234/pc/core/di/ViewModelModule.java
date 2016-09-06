package com.khigio234.pc.core.di;

import com.birbit.android.jobqueue.JobManager;
import com.khigio234.pc.core.model.services.clouds.CategoryCloudService;
import com.khigio234.pc.core.model.services.clouds.ICategoryService;
import com.khigio234.pc.core.model.services.clouds.RestaurantCloudService;
import com.khigio234.pc.core.model.services.storages.CategoryModel;
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

@Module(includes = {CloudModule.class, StorageModule.class, JobModule.class})
public class ViewModelModule {

    //region Provide methods

    @Provides
    @Singleton
    MainViewModel providesMainViewModel(INavigator navigator) {
        return new MainViewModel(navigator);
    }

    @Provides
    @Singleton
    public CategoryViewModel providesCategoryViewModel(INavigator navigator, CategoryCloudService categoryCloudService, CategoryModel categoryModel, JobManager jobManager, ICategoryService iCategoryService) {
        return new CategoryViewModel(navigator, categoryCloudService, categoryModel, jobManager, iCategoryService);
    }

    @Provides
    @Singleton
    public RestaurantViewModel providesRestaurantViewModel(INavigator navigator, RestaurantCloudService restaurantCloudService) {
        return new RestaurantViewModel(navigator, restaurantCloudService);
    }

    //endregion

}
