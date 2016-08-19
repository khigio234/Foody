package com.khigio234.pc.core.model.services;

import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.view.ICallback;

import java.util.List;

/**
 * Created by PC on 8/16/2016.
 */
public interface IRestaurantService {

    void getAllRestaurants(ICallback<List<Restaurant>> callback);

    void saveRestaurants(List<Restaurant> restaurants, ICallback<Boolean> callback);
}
