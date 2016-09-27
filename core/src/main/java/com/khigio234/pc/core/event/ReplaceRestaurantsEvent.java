package com.khigio234.pc.core.event;

import com.khigio234.pc.core.model.entities.Restaurant;

import java.util.List;

/**
 * Created by PC on 9/16/2016.
 */
public class ReplaceRestaurantsEvent {

    //region Properties

    private final List<Restaurant> mRestaurants;

    //endregion

    //region Constructor

    public ReplaceRestaurantsEvent(List<Restaurant> restaurants) {
        mRestaurants = restaurants;
    }

    //endregion

    //region Getter and Setter

    public List<Restaurant> getRestaurants() {
        return mRestaurants;
    }

    //endregion
}
