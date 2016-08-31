package com.khigio234.pc.core.viewmodel;

import android.databinding.Bindable;
import android.util.Log;

import com.khigio234.pc.core.BR;
import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.services.clouds.RestaurantCloudService;
import com.khigio234.pc.core.model.services.storages.RestaurantStorageService;
import com.khigio234.pc.core.view.ICallback;
import com.khigio234.pc.core.view.INavigator;

import java.util.List;

/**
 * Created by PC on 8/16/2016.
 */
public class RestaurantViewModel extends BaseViewModel {

    //region Properties

    private static final String TAG = "RestaurantViewModel";

    private List<Restaurant> mRestaurants;

    private RestaurantStorageService mRestaurantStorageService;

    private RestaurantCloudService mRestaurantCloudService;

    //endregion

    //region Getter and Setter

    @Bindable
    public List<Restaurant> getRestaurants() {
        return mRestaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        mRestaurants = restaurants;

        notifyPropertyChanged(BR.restaurants);
    }

    //endregion

    //region Constructor

    /*
        Storage
     */
//    public RestaurantViewModel(INavigator navigator, RestaurantStorageService restaurantStorageService) {
//        super(navigator);
//        mRestaurantStorageService = restaurantStorageService;
//    }

    /*
        Cloud
     */
    public RestaurantViewModel(INavigator navigator, RestaurantCloudService restaurantCloudService) {
        super(navigator);
        mRestaurantCloudService = restaurantCloudService;
    }

    protected RestaurantViewModel() {
        super();
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        loadRestaurant();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mRestaurants = null;
    }

    private void loadRestaurant() {
        Log.d("cc","ccc");
        mRestaurantCloudService.getAllRestaurants(new ICallback<List<Restaurant>>() {
            @Override
            public void onResult(List<Restaurant> result) {
                Log.d(TAG, "Load Restaurant");
                setRestaurants(result);
                for (Restaurant restaurant: result) {
                    Log.d(TAG, restaurant.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("cc","",t);
            }
        });
    }

    //endregion
}
