package com.khigio234.pc.core.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.khigio234.pc.core.BR;
import com.khigio234.pc.core.event.FetchedRestaurantEvent;
import com.khigio234.pc.core.job.BaseJob;
import com.khigio234.pc.core.job.FetchRestaurantJob;
import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.responses.APIResponse;
import com.khigio234.pc.core.model.services.Configuration;
import com.khigio234.pc.core.model.services.clouds.IRestaurantService;
import com.khigio234.pc.core.model.services.storages.RestaurantModel;
import com.khigio234.pc.core.view.ICallback;
import com.khigio234.pc.core.view.INavigator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PC on 8/16/2016.
 */
public class RestaurantViewModel extends BaseViewModel {

    //region Properties

    private static final String TAG = "RestaurantViewModel";

    private ObservableArrayList<Restaurant> mRestaurants;

    private RestaurantModel mRestaurantModel;

    private IRestaurantService mIRestaurantService;

    private JobManager mJobManager;

    //endregion

    //region Getter and Setter

    @Bindable
    public List<Restaurant> getRestaurants() {
        return mRestaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        mRestaurants = new ObservableArrayList<>();
        mRestaurants.addAll(restaurants);
        notifyPropertyChanged(BR.restaurants);
    }

    //endregion

    //region Constructor

    public RestaurantViewModel(INavigator navigator, RestaurantModel restaurantModel, JobManager jobManager, IRestaurantService iRestaurantService) {
        super(navigator);

        mRestaurantModel = restaurantModel;
        mJobManager = jobManager;
        mIRestaurantService = iRestaurantService;
    }

    protected RestaurantViewModel() {
        super();
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        register();

        loadLatestRestaurants();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        unregister();

        super.onDestroy();
    }

    //endregion

    //region Private method

    private void loadLatestRestaurants() {

        mRestaurantModel.getLatestRestaurantsAsync(new ICallback<List<Restaurant>>() {
            @Override
            public void onResult(List<Restaurant> result) {
                setRestaurants(result);
                Log.d(TAG, result.toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        mJobManager.addJobInBackground(new FetchRestaurantJob(BaseJob.UI_HIGH, mIRestaurantService, mRestaurantModel));
    }

    //endregion

    //region Public method

    public void getNextPageRestaurants(long currentOffset) {
        long nextOffset = currentOffset + 1;

        mIRestaurantService.getRestaurants(nextOffset, Configuration.NUMBER_CACHE_RESTAURANTS).enqueue(new Callback<APIResponse<List<Restaurant>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Restaurant>>> call, Response<APIResponse<List<Restaurant>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        mRestaurants.addAll(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Restaurant>>> call, Throwable t) {

            }
        });
    }

    //endregion

    //region Subscribe

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(FetchedRestaurantEvent fetchedRestaurantEvent) {
        if (fetchedRestaurantEvent.isSuccess()) {
            setRestaurants(fetchedRestaurantEvent.getRestaurants());
        } else {
            Log.d(TAG, "Fetched restaurants failed!");
        }
    }

    //endregion
}
