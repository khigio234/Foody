package com.khigio234.pc.foody;

import com.khigio234.pc.core.di.AppModule;
import com.khigio234.pc.core.di.CloudModule;
import com.khigio234.pc.core.di.StorageModule;
import com.khigio234.pc.core.di.ViewModelModule;
import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.services.storages.CategoryStorageService;
import com.khigio234.pc.core.model.services.storages.RestaurantStorageService;
import com.khigio234.pc.core.view.BaseApplication;
import com.khigio234.pc.core.view.Constants;
import com.khigio234.pc.core.view.ICallback;
import com.khigio234.pc.core.view.INavigator;
import com.khigio234.pc.foody.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by PC on 8/2/2016.
 */
public class App extends BaseApplication{

    //region Properties

    private static AppComponent sAppComponent;

    //endregion

    //region Getter and Setter

    public synchronized static AppComponent sharedComponent() {
        return sAppComponent;
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        AppModule appModule = new AppModule(this);
        INavigator navigator = appModule.getNavigator();
        navigator.configure(Constants.MAIN_PAGE, MainActivity.class);


        sAppComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .cloudModule(new CloudModule())
                .storageModule(new StorageModule())
                .viewModelModule(new ViewModelModule())
                .build();

        initCategories();
        initRestaurants();
    }

    private void initCategories() {
        List<Category> categories = new ArrayList<>();

        new CategoryStorageService(Realm.getDefaultInstance()).saveCategories(categories, new ICallback<Boolean>() {
            @Override
            public void onResult(Boolean result) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void initRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();

        new RestaurantStorageService(Realm.getDefaultInstance()).saveRestaurants(restaurants, new ICallback<Boolean>() {
            @Override
            public void onResult(Boolean result) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    //endregion
}
