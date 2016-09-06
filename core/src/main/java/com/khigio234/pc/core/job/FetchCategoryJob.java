package com.khigio234.pc.core.job;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.khigio234.pc.core.event.FetchedCategoryEvent;
import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.responses.APIResponse;
import com.khigio234.pc.core.model.services.clouds.ICategoryService;
import com.khigio234.pc.core.model.services.storages.CategoryModel;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by PC on 9/2/2016.
 */
public class FetchCategoryJob extends BaseJob {

    //region Properties

    private ICategoryService mICategoryService;

    private CategoryModel mCategoryModel;

    private Realm mRealm;

    private static final String TAG = "FetchCategoryJob";
    private static final String GROUP = "FetchCategoryJob";

    //endregion

    //region Constructor

    public FetchCategoryJob(@BaseJob.Priority int priority, ICategoryService iCategoryService, CategoryModel categoryModel) {
        super(new Params(priority).groupBy(GROUP).requireNetwork());

        mICategoryService = iCategoryService;

        mCategoryModel = categoryModel;
    }

    //endregion

    //region Override methods

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        Call<APIResponse<List<Category>>> call;

        Date latestSynchronizeTimestamp = mCategoryModel.getLatestSychronizeTimestamp();

        if (latestSynchronizeTimestamp != null) {
            call = mICategoryService.getNewCategories(latestSynchronizeTimestamp);
            Log.d(TAG, latestSynchronizeTimestamp.toString());
        } else {
            call = mICategoryService.getAllCategories();
        }

        Response<APIResponse<List<Category>>> response = call.execute();
        if (response.isSuccessful()) {
            APIResponse<List<Category>> apiResponse = response.body();
            if (apiResponse.isSuccess()) {
                if (apiResponse.getData().size() > 0) {
                    mCategoryModel.handleFetchedCategories(apiResponse.getData(), apiResponse.getLastSyncTimestamp());
                    getEventBus().post(new FetchedCategoryEvent(true));
                    return;
                }
            }
        }
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

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
