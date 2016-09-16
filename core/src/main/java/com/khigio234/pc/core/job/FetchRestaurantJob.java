package com.khigio234.pc.core.job;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.khigio234.pc.core.event.FetchedRestaurantEvent;
import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.responses.APIResponse;
import com.khigio234.pc.core.model.services.Configuration;
import com.khigio234.pc.core.model.services.clouds.IRestaurantService;
import com.khigio234.pc.core.model.services.storages.RestaurantModel;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by PC on 9/10/2016.
 */
public class FetchRestaurantJob extends BaseJob {

    //region Properties

    private IRestaurantService mIRestaurantService;

    private RestaurantModel mRestaurantModel;

    public static final String TAG = "FetchRestaurantJob";

    public static final String GROUP = "FetchRestaurantJob";

    //endregion

    //region Constructor

    public FetchRestaurantJob(@BaseJob.Priority int priority, IRestaurantService iRestaurantService, RestaurantModel restaurantModel) {
        super(new Params(priority).groupBy(GROUP).requireNetwork());

        mIRestaurantService = iRestaurantService;

        mRestaurantModel = restaurantModel;
    }

    //endregion

    //region Override methods

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        Call<APIResponse<List<Restaurant>>> call;

        Date latestSynchronizeTimestamp = mRestaurantModel.getLatestSynchronizeTimestamp();

        if (latestSynchronizeTimestamp != null) {
            call = mIRestaurantService.getNewRestaurants(latestSynchronizeTimestamp);
        } else {
            call = mIRestaurantService.getRestaurants(0, Configuration.NUMBER_CACHE_RESTAURANTS);
        }

        Response<APIResponse<List<Restaurant>>> response = call.execute();
        if (response.isSuccessful()) {
            APIResponse<List<Restaurant>> apiResponse = response.body();

            if (apiResponse.isSuccess()) {
                if (apiResponse.getData().size() > 0) {
                    mRestaurantModel.handleFetchedRestaurant(apiResponse.getData(), apiResponse.getLastSyncTimestamp());
                    List<Restaurant> restaurants = mRestaurantModel.getNewRestaurants();
                    post(new FetchedRestaurantEvent(true, restaurants));
                    return;
                } else {
                    Log.d(TAG, "There is nothing changed");
                    return;
                }
            } else {
                post(new FetchedRestaurantEvent(false));
                return;
            }
        } else {
            post(new FetchedRestaurantEvent(false));
            return;
        }
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        post(new FetchedRestaurantEvent(false));
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        if (shouldRetry(throwable)) {
            return RetryConstraint.RETRY;
        }
        return RetryConstraint.CANCEL;
    }

    @Override
    protected int getRetryLimit() {
        return 5;
    }

    //endregion
}
