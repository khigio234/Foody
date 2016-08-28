package com.khigio234.pc.core.model.services.clouds;

import android.util.Log;

import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.responses.APIResponse;
import com.khigio234.pc.core.model.services.IRestaurantService;
import com.khigio234.pc.core.view.ICallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PC on 8/28/2016.
 */
public class RestaurantCloudService extends BaseCloudService<IRestaurantCloudService> implements IRestaurantService {

    //region Constructor

    public RestaurantCloudService(IRestaurantCloudService iRestaurantCloudService) {
        mICloudService = iRestaurantCloudService;
    }

    //endregion

    //region Override methods

    @Override
    public void getAllRestaurants(final ICallback<List<Restaurant>> callback) {
        getICloudService().getAllRestaurants().enqueue(new Callback<APIResponse<List<Restaurant>>>() {
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

    @Override
    public void saveRestaurants(List<Restaurant> restaurants, ICallback<Boolean> callback) {

    }

    //endregion
}
