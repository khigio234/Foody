package com.khigio234.pc.core.model.services.clouds;

import android.util.Log;

import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.responses.APIResponse;
import com.khigio234.pc.core.model.services.ICategoryService;
import com.khigio234.pc.core.view.ICallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PC on 8/30/2016.
 */
public class CategoryCloudService extends BaseCloudService<ICategoryCloudService> implements ICategoryService {

    //region Constructor

    public CategoryCloudService(ICategoryCloudService iCategoryCloudService) {
        mICloudService = iCategoryCloudService;
    }

    //endregion

    //region Override method

    @Override
    public void getAllCategories(final ICallback<List<Category>> callback) {
        getICloudService().getAllCategories().enqueue(new Callback<APIResponse<List<Category>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Category>>> call, Response<APIResponse<List<Category>>> response) {
                APIResponse<List<Category>> apiResponse = response.body();
                if (apiResponse.isSuccess()){
                    callback.onResult(apiResponse.getData());

                    Log.d("TAG", apiResponse.getData().get(0).toString());
                } else {
                    Log.d("TAG", apiResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Category>>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void saveCategories(List<Category> categories, ICallback<Boolean> callback) {

    }


    //endregion
}
