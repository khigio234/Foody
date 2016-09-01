package com.khigio234.pc.core.viewmodel;

import android.databinding.Bindable;
import android.util.Log;

import com.khigio234.pc.core.BR;
import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.services.clouds.CategoryCloudService;
import com.khigio234.pc.core.model.services.storages.CategoryModel;
import com.khigio234.pc.core.view.ICallback;
import com.khigio234.pc.core.view.INavigator;

import java.util.List;

/**
 * Created by PC on 8/4/2016.
 */
public class CategoryViewModel extends BaseViewModel{

    //region Properties

    private static final String TAG = "CategoryViewModel";

    private List<Category> mCategories;

    private CategoryModel mCategoryModel;

    private CategoryCloudService mCategoryCloudService;

    //endregion

    //region Getter and Setter

    @Bindable
    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;

        notifyPropertyChanged(BR.categories);
    }


    //endregion

    //region Constructors

    /**
     * @param navigator Navigate controller.
     */
    public CategoryViewModel(INavigator navigator, CategoryCloudService cloudService, CategoryModel categoryModel) {
        super(navigator);

        mCategoryCloudService = cloudService;
        mCategoryModel = categoryModel;
    }

    protected CategoryViewModel() {
        super();
    }

    //endregion

    //region Lifecycle


    @Override
    public void onCreate() {
        super.onCreate();

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
    }

    //endregion

    //region Private methods

    private void loadCategory(){
        mCategoryCloudService.getAllCategories(new ICallback<List<Category>>() {
            @Override
            public void onResult(List<Category> result) {
                Log.d(TAG, "DONE");
                setCategories(result);
                for (Category category: result) {
                    Log.d(TAG, category.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    //endregion

    //region Public methods



    //endregion

}
