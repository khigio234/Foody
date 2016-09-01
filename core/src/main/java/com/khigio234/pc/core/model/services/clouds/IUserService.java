package com.khigio234.pc.core.model.services.clouds;

import com.khigio234.pc.core.model.entities.User;
import com.khigio234.pc.core.model.responses.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PC on 8/31/2016.
 */
public interface IUserService {

    @GET("/foody_api_son/api/v1/authenticate/sign-in")
    Call<APIResponse<User>> logIn(@Query("email")String email, @Query("password")String password);

    @GET("/foody_api_son/api/v1/authenticate/sign-up")
    Call<APIResponse<Boolean>> signUp(@Query("email")String email, @Query("password")String password, @Query("name")String name);
}
