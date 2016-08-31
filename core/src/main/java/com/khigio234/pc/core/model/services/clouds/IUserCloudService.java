package com.khigio234.pc.core.model.services.clouds;

import com.khigio234.pc.core.model.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PC on 8/31/2016.
 */
public interface IUserCloudService {

    @GET("/foody_api_son/logIn")
    Call<User> logIn(@Query("username")String username, @Query("password")String password);
}
