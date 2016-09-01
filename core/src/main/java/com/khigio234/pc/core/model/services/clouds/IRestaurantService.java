package com.khigio234.pc.core.model.services.clouds;

import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.responses.APIResponse;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PC on 8/16/2016.
 */
public interface IRestaurantService {

    @GET("/foody_api_son/api/v1/restaurants")
    Call<APIResponse<List<Restaurant>>> getRestaurants(@Query("offset") long offset, @Query("limit") long limit);

    @GET("/foody_api_son/api/v1/restaurants")
    Call<APIResponse<List<Restaurant>>> getNewRestaurants(@Query("offset")long offset, @Query("limit")long limit, @Query("last_sync_timestamp") Date lastSyncTimestamp);

    @GET("/foody_api_son/api/v1/restaurants")
    Call<APIResponse<List<Restaurant>>> getRestaurantsByCategory(@Query("category_id") int categoryId, @Query("offset") long offset, @Query("limit") long limit);

}
