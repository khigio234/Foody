package com.khigio234.pc.core.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.birbit.android.jobqueue.JobManager;
import com.khigio234.pc.core.BR;
import com.khigio234.pc.core.event.AddMoreRestaurantsEvent;
import com.khigio234.pc.core.event.ReplaceRestaurantsEvent;
import com.khigio234.pc.core.job.BaseJob;
import com.khigio234.pc.core.job.FetchRestaurantJob;
import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.services.clouds.IRestaurantService;
import com.khigio234.pc.core.model.services.storages.RestaurantModel;
import com.khigio234.pc.core.view.INavigator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

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

        unregister();
    }

    //endregion

    //region Private method

    private void loadLatestRestaurants() {
        mJobManager.addJobInBackground(new FetchRestaurantJob(BaseJob.UI_HIGH, -1,mIRestaurantService, mRestaurantModel));
    }

    //endregion

    //region Public method

    public void getNextPageRestaurants(int offset) {
        mJobManager.addJobInBackground(new FetchRestaurantJob(BaseJob.UI_HIGH, offset, mIRestaurantService, mRestaurantModel));
    }

    //endregion

    //region Subscribe

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(ReplaceRestaurantsEvent replaceRestaurantsEvent) {
        setRestaurants(replaceRestaurantsEvent.getRestaurants());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(AddMoreRestaurantsEvent addMoreRestaurantsEvent) {
        setRestaurants(addMoreRestaurantsEvent.getRestaurants());
    }

    //endregion
}
