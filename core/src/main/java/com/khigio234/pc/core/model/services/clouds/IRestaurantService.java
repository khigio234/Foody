package com.khigio234.pc.core.model.services.clouds;

import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.responses.APIResponse;
import com.khigio234.pc.core.util.QueryDate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PC on 8/16/2016.
 */
public interface IRestaurantService {

    @GET("/foody_api_son/api/v1/restaurants")
    Call<APIResponse<List<Restaurant>>> getRestaurants(@Query("offset") int offset, @Query("limit") int limit);

    @GET("/foody_api_son/api/v1/restaurants")
    Call<APIResponse<List<Restaurant>>> getNewRestaurants(@Query("last_sync_timestamp") QueryDate lastSyncTimestamp);

    @GET("/foody_api_son/api/v1/restaurants")
    Call<APIResponse<List<Restaurant>>> getRestaurantsByCategory(@Query("category_id") int categoryId, @Query("offset") int offset, @Query("limit") int limit);

}
