package com.khigio234.pc.core.model.services.clouds;

import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.responses.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PC on 8/30/2016.
 */
public interface ICategoryCloudService {

    @GET("/api/v1/categories")
    Call<APIResponse<List<Category>>> getAllCategories();
}
