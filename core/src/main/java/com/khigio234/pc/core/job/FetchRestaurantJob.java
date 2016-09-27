package com.khigio234.pc.core.job;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.khigio234.pc.core.event.AddMoreRestaurantsEvent;
import com.khigio234.pc.core.event.FetchedRestaurantEvent;
import com.khigio234.pc.core.event.ReplaceRestaurantsEvent;
import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.responses.APIResponse;
import com.khigio234.pc.core.model.services.Configuration;
import com.khigio234.pc.core.model.services.clouds.IRestaurantService;
import com.khigio234.pc.core.model.services.storages.RestaurantModel;
import com.khigio234.pc.core.util.QueryDate;

import java.util.Date;
import java.util.List;

import retrofit2.Response;

/**
 * Created by PC on 9/10/2016.
 */
public class FetchRestaurantJob extends BaseJob {

    //region Properties

    private IRestaurantService mIRestaurantService;

    private RestaurantModel mRestaurantModel;

    public static final String GROUP = "FetchRestaurantJob";

    private int mOffset = -1;

    //endregion

    //region Constructor

    public FetchRestaurantJob(@BaseJob.Priority int priority, int offset, IRestaurantService iRestaurantService, RestaurantModel restaurantModel) {
        super(new Params(priority).groupBy(GROUP).requireNetwork());

        mIRestaurantService = iRestaurantService;

        mRestaurantModel = restaurantModel;

        mOffset = offset;
    }

    //endregion

    //region Override method

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {

        if (isFirstSync()) {
            List<Restaurant> localResult = mRestaurantModel.getLatest();
            if (localResult != null) {
                post(new ReplaceRestaurantsEvent(localResult));
            }

            Date lastSyncAt = mRestaurantModel.getLatestSynchronize();
            List<Restaurant> fetchedRestaurants;

            if (lastSyncAt != null) {
                fetchedRestaurants = getSyncData(lastSyncAt);
            } else {
                fetchedRestaurants = getMoreData(mOffset + 1);
            }

            if (fetchedRestaurants != null && fetchedRestaurants.size() > 0) {
                syncRestaurants(fetchedRestaurants);
                mRestaurantModel.saveLatestSynchronize(lastSyncAt);
                List<Restaurant> newRestaurants = mRestaurantModel.getLatest();
                post(new ReplaceRestaurantsEvent(newRestaurants));
            }
        } else {
            List<Restaurant> moreRestaurants = getMoreData(mOffset);
            if (moreRestaurants != null && moreRestaurants.size() > 0) {
                post(new AddMoreRestaurantsEvent(moreRestaurants));
            }
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
        return 2;
    }

    //endregion

    //region Private method

    private boolean isFirstSync() {
        return mOffset < 0;
    }

    private List<Restaurant> getSyncData(Date lastSyncAt) throws Throwable {

        Response<APIResponse<List<Restaurant>>> response = mIRestaurantService.getNewRestaurants(new QueryDate(lastSyncAt)).execute();
        if (response.isSuccessful()){

            APIResponse<List<Restaurant>> apiResponse = response.body();

            if (apiResponse != null && apiResponse.isSuccess()) {
                List<Restaurant> syncRestaurants = apiResponse.getData();
                return syncRestaurants;
            }
        }
        return null;
    }

    private List<Restaurant> getMoreData(int offset) throws Throwable {

        Response<APIResponse<List<Restaurant>>> response = mIRestaurantService.getRestaurants(offset, Configuration.NUMBER_RECORDS_PER_PAGE).execute();
        if (response.isSuccessful()){

            APIResponse<List<Restaurant>> apiResponse = response.body();

            if (apiResponse != null && apiResponse.isSuccess()) {
                return apiResponse.getData();
            }
        }
        return null;
    }

    private void syncRestaurants(List<Restaurant> restaurants) {
        for (Restaurant restaurant: restaurants) {
            if (restaurant.isDeleted()) {
                mRestaurantModel.delete(restaurant);
            } else {
                mRestaurantModel.addNewOrUpdate(restaurant);
            }
        }
        mRestaurantModel.optimizeCached();
    }

    //endregion
}
