package com.khigio234.pc.core.event;

import com.khigio234.pc.core.model.entities.Restaurant;

import java.util.List;

/**
 * Created by PC on 9/10/2016.
 */
public class FetchedRestaurantEvent {

    //region Properties

    private boolean mSuccess;

    private String mMessage;

    private List<Restaurant> mRestaurants;

    //endregion

    //region Getter and Setter

    public boolean isSuccess() {
        return mSuccess;
    }

    public String getMessage() {
        return mMessage;
    }

    public List<Restaurant> getRestaurants() {
        return mRestaurants;
    }

    //endregion

    //region Constructor

    public FetchedRestaurantEvent(boolean success) {
        mSuccess = success;
    }

    public FetchedRestaurantEvent(boolean success, String message) {
        mSuccess = success;
        mMessage = message;
    }

    public FetchedRestaurantEvent(boolean success, List<Restaurant> restaurants) {
        mSuccess = success;
        mRestaurants = restaurants;
    }

    //endregion
}
