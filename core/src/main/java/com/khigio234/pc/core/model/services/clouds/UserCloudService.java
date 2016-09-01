package com.khigio234.pc.core.model.services.clouds;

import android.util.Log;

import com.khigio234.pc.core.model.entities.User;
import com.khigio234.pc.core.model.responses.APIResponse;
import com.khigio234.pc.core.view.ICallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PC on 8/31/2016.
 */
public class UserCloudService extends BaseCloudService<IUserService> {

    //region Constructor

    public UserCloudService(IUserService iUserService) {
        mICloudService = iUserService;
    }

    //endregion

    //region Override method

    public void logIn(User user, final ICallback<User> callback) {
        getICloudService().logIn(user.getEmail(),user.getPassword()).enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if (apiResponse != null) {
                    if (apiResponse.isSuccess()) {
                        callback.onResult(apiResponse.getData());

                        Log.d("TAG", apiResponse.getData().toString());
                    } else {
                        callback.onFailure(new Throwable(apiResponse.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void signUp(User user, final ICallback<Boolean> callback) {
        getICloudService().signUp(user.getEmail(), user.getPassword(), user.getUserName()).enqueue(new Callback<APIResponse<Boolean>>() {
            @Override
            public void onResponse(Call<APIResponse<Boolean>> call, Response<APIResponse<Boolean>> response) {
                APIResponse<Boolean> apiResponse = response.body();
                if (apiResponse != null) {
                    if (apiResponse.isSuccess()) {
                        callback.onResult(apiResponse.getData());
                        Log.d("TAG", apiResponse.getData().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Boolean>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    //endregion
}
