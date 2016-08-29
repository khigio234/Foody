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
import java.util.Date;
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

        categories.add(new Category(1,"Sang trọng","1.png",new Date(123456),new Date(234567),new Date(345678), false));
        categories.add(new Category(2,"Buffet","1.png",new Date(123456),new Date(234567),new Date(345678), false));
        categories.add(new Category(3,"Nhà hàng","1.png",new Date(123456),new Date(234567),new Date(345678), false));
        categories.add(new Category(4,"Ăn vặt/vỉa hè","1.png",new Date(123456),new Date(234567),new Date(345678), false));
        categories.add(new Category(5,"Ăn chay","1.png",new Date(123456),new Date(234567),new Date(345678), false));
        categories.add(new Category(6,"Cafe/Dessert","1.png",new Date(123456),new Date(234567),new Date(345678), false));
        categories.add(new Category(7,"Quán ăn","1.png",new Date(123456),new Date(234567),new Date(345678), false));
        categories.add(new Category(8,"Bar/Pub","1.png",new Date(123456),new Date(234567),new Date(345678), false));
        categories.add(new Category(9,"Quán nhậu","1.png",new Date(123456),new Date(234567),new Date(345678), false));
        categories.add(new Category(10,"Beer club","1.png",new Date(123456),new Date(234567),new Date(345678), false));


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

        restaurants.add(new Restaurant(1, "Công Vinh", "34 Âu Cơ, Hòa Khánh, Liên Chiểu", "7:00 AM", "10:30 PM", "01206145258", "1.png", "Nơi tụ hội của Team Công Vinh", new Date(123456),new Date(234567),new Date(345678), false));
        restaurants.add(new Restaurant(2, "Meo Meo", "55 Ngô Thì Nhậm, Hòa Khánh, Liên Chiểu", "7:00 AM", "10:30 PM", "01206145258", "1.png", "Nơi tụ hội của Team Công Vinh", new Date(123456),new Date(234567),new Date(345678), false));
        restaurants.add(new Restaurant(3, "Muối Ớt", "34 Âu Cơ, Hòa Khánh, Liên Chiểu", "7:00 AM", "10:30 PM", "01206145258", "1.png", "Nơi tụ hội của Team Công Vinh", new Date(123456),new Date(234567),new Date(345678), false));
        restaurants.add(new Restaurant(4, "Phong Phú", "55 Ngô Thì Nhậm, Hòa Khánh, Liên Chiểu", "7:00 AM", "10:30 PM", "01206145258", "1.png", "Nơi tụ hội của Team Công Vinh", new Date(123456),new Date(234567),new Date(345678), false));

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
