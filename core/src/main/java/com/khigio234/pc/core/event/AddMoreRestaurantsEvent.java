package com.khigio234.pc.core.event;

import com.khigio234.pc.core.model.entities.Restaurant;

import java.util.List;

/**
 * Created by PC on 9/27/2016.
 */
public class AddMoreRestaurantsEvent {

    //region Properties

    public final List<Restaurant> mRestaurants;

    //endregion

    //region Constructor

    public AddMoreRestaurantsEvent(List<Restaurant> restaurants) {
        mRestaurants = restaurants;
    }

    //endregion

    //region Getter and Setter

    public List<Restaurant> getRestaurants() {
        return mRestaurants;
    }

    //endregion
}
