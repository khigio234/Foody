package com.khigio234.pc.core.model.services.clouds;

import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.responses.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PC on 8/28/2016.
 */
public interface IRestaurantCloudService {

    @GET("/foody_api_son/api/v1/restaurants")
    Call<APIResponse<List<Restaurant>>> getAllRestaurants();
}
