package com.khigio234.pc.core.model.services.clouds;

import android.util.Log;

import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.responses.APIResponse;
import com.khigio234.pc.core.view.ICallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PC on 8/28/2016.
 */
public class RestaurantCloudService extends BaseCloudService<IRestaurantService> {

    //region Constructor

    public RestaurantCloudService(IRestaurantService iRestaurantService) {
        mICloudService = iRestaurantService;
    }

    //endregion

    //region Public methods

    public void getRestaurants (long offset, long limit, final ICallback<List<Restaurant>> callback) {
        getICloudService().getRestaurants(offset, limit).enqueue(new Callback<APIResponse<List<Restaurant>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Restaurant>>> call, Response<APIResponse<List<Restaurant>>> response) {
                APIResponse<List<Restaurant>> apiResponse = response.body();
                if (apiResponse.isSuccess()) {
                    callback.onResult(apiResponse.getData());

                    Log.d("TAG", apiResponse.getData().get(0).toString());
                } else {
                    Log.d("TAG", apiResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Restaurant>>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void getRestaurantsByCategory(Category category, long offset, long limit, final ICallback<List<Restaurant>> callback) {
        getICloudService().getRestaurantsByCategory(category.getId(), offset, limit).enqueue(new Callback<APIResponse<List<Restaurant>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Restaurant>>> call, Response<APIResponse<List<Restaurant>>> response) {
                APIResponse<List<Restaurant>> apiResponse = response.body();
                if (apiResponse.isSuccess()) {
                    callback.onResult(apiResponse.getData());

                    Log.d("TAG", apiResponse.getData().get(0).toString());
                } else {
                    Log.d("TAG", apiResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Restaurant>>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    //endregion
}
