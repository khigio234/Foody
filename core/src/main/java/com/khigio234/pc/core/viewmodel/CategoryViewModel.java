package com.khigio234.pc.core.viewmodel;

import android.databinding.Bindable;
import android.util.Log;

import com.birbit.android.jobqueue.JobManager;
import com.khigio234.pc.core.BR;
import com.khigio234.pc.core.event.FetchedCategoryEvent;
import com.khigio234.pc.core.job.BaseJob;
import com.khigio234.pc.core.job.FetchCategoryJob;
import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.services.clouds.CategoryCloudService;
import com.khigio234.pc.core.model.services.clouds.ICategoryService;
import com.khigio234.pc.core.model.services.storages.CategoryModel;
import com.khigio234.pc.core.view.INavigator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by PC on 8/4/2016.
 */
public class CategoryViewModel extends BaseViewModel{

    //region Properties

    private static final String TAG = "CategoryViewModel";

    private RealmResults<Category> mCategories;

    private CategoryModel mCategoryModel;

    private CategoryCloudService mCategoryCloudService;

    private ICategoryService mICategoryService;

    private JobManager mJobManager;

    //endregion

    //region Getter and Setter

    @Bindable
    public RealmResults<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(RealmResults<Category> categories) {
        mCategories = categories;

        notifyPropertyChanged(BR.categories);
    }

    @Override
    protected EventBus getEventBus() {
        return super.getEventBus();
    }

    //endregion

    //region Constructors

    /**
     * @param navigator Navigate controller.
     */
    public CategoryViewModel(INavigator navigator, CategoryCloudService categoryCloudService, CategoryModel categoryModel, JobManager jobManager, ICategoryService iCategoryService) {
        super(navigator);

        mCategoryCloudService = categoryCloudService;
        mCategoryModel = categoryModel;
        mJobManager = jobManager;
        mICategoryService = iCategoryService;
    }

    protected CategoryViewModel() {
        super();
    }

    //endregion

    //region Lifecycle


    @Override
    public void onCreate() {
        super.onCreate();

        getNavigator().showBusyIndicator("Loading...");

        getEventBus().register(this);

        loadCategory();
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
        super.onDestroy();

        mCategories = null;

        getEventBus().unregister(this);
    }

    //endregion

    //region Private methods

    private void loadCategory(){
        RealmResults<Category> categories = mCategoryModel.getAllCategories();
        setCategories(categories);
        getNavigator().hideBusyIndicator();

        categories.addChangeListener(new RealmChangeListener<RealmResults<Category>>() {
            @Override
            public void onChange(RealmResults<Category> element) {
                setCategories(element);
            }
        });

        mJobManager.addJobInBackground(new FetchCategoryJob(BaseJob.UI_HIGH, mICategoryService, mCategoryModel));
    }

    //endregion

    //region Public methods

    public void showRestaurantsByCategory(Category category) {
        getEventBus().postSticky(category);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(FetchedCategoryEvent fetchedCategoryEvent) {
        if (fetchedCategoryEvent.isSuccess()) {
            Log.d(TAG, "Fetched categories successfully!");
        } else {
            Log.d(TAG, "Fetched categories failed!");
        }
    }

    //endregion

}
