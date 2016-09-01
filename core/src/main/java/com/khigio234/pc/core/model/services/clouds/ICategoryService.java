package com.khigio234.pc.core.model.services.clouds;

import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.responses.APIResponse;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PC on 8/4/2016.
 */
public interface ICategoryService {

    @GET("/foody_api_son/api/v1/categories")
    Call<APIResponse<List<Category>>> getAllCategories();

    @GET("/foody_api_son/api/v1/categories")
    Call<APIResponse<List<Category>>> getNewCategories(@Query("last_sync_timestamp")Date lastSyncTimestamp);
}
