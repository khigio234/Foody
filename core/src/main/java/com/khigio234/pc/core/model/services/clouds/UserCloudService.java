package com.khigio234.pc.core.model.services.clouds;

import com.khigio234.pc.core.model.entities.User;
import com.khigio234.pc.core.model.services.IUserService;
import com.khigio234.pc.core.view.ICallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PC on 8/31/2016.
 */
public class UserCloudService extends BaseCloudService<IUserCloudService> implements IUserService {

    //region Constructor

    public UserCloudService(IUserCloudService iUserCloudService) {
        mICloudService = iUserCloudService;
    }

    //endregion

    //region Override method

    @Override
    public void logIn(User user, final ICallback<User> callback) {
        getICloudService().logIn(user.getUserName(), user.getPassword()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null) {
                    callback.onResult(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void register(User user, ICallback<Boolean> callback) {

    }


    //endregion
}
