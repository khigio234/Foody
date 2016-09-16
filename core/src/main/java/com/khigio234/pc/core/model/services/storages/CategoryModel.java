package com.khigio234.pc.core.model.services.storages;

import android.util.Log;

import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.entities.SyncHistory;
import com.khigio234.pc.core.view.ICallback;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by PC on 9/1/2016.
 */
public class CategoryModel extends BaseModel {

    //region Properties

    private static final String CATEGORY_TABLE_NAME = "Category";

    private static final String TAG = "CategoryModel";

    //endregion

    //region Constructor

    public CategoryModel(Realm realm) {
        super(realm);
    }

    //endregion

    //region Public methods

    public RealmResults<Category> getAllCategories() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Category> categories = realm.where(Category.class).findAll();
        return categories;
    }

    public void getAllCategoriesAsync(final ICallback<List<Category>> callback) {
        final Realm realm = Realm.getDefaultInstance();

        final RealmResults<Category> categories = realm.where(Category.class).findAllAsync();
        categories.addChangeListener(new RealmChangeListener<RealmResults<Category>>() {
            @Override
            public void onChange(RealmResults<Category> element) {
                List<Category> unManagedCategories = realm.copyToRealm(element);
                callback.onResult(unManagedCategories);
                categories.removeChangeListener(this);
            }
        });
    }

    public Date getLatestSynchronizeTimestamp() {
        Realm realm = Realm.getDefaultInstance();

        SyncHistory syncHistory = realm.where(SyncHistory.class).equalTo("mNameTable", CATEGORY_TABLE_NAME).findFirst();
        if (syncHistory != null) {
            return syncHistory.getLastSyncTimestamp();
        }

        return null;
    }

    public void handleFetchedCategories(List<Category> categories, Date latestSynchronizeTimestamp) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for (Category category : categories) {

            Log.d(TAG, category.toString());
            if (category.isDeleted()) {
                deleteCategory(category);
            } else {
                addNewOrUpdateCategory(category);
            }
        }
        updateLatestSynchronizeTimestamp(latestSynchronizeTimestamp);

        realm.commitTransaction();
    }

    //endregion

    //region Private methods

    private void addNewOrUpdateCategory(Category category) {
        Realm realm = Realm.getDefaultInstance();

        realm.copyToRealmOrUpdate(category);
    }

    private void updateLatestSynchronizeTimestamp (Date latestSynchronizeTimestamp) {
        Realm realm = Realm.getDefaultInstance();

        SyncHistory syncHistory = realm.where(SyncHistory.class).equalTo("mNameTable", CATEGORY_TABLE_NAME).findFirst();
        if (syncHistory == null) {
            syncHistory = realm.createObject(SyncHistory.class);
            syncHistory.setNameTable(CATEGORY_TABLE_NAME);
        }

        syncHistory.setLastSyncTimestamp(latestSynchronizeTimestamp);
    }

    private void deleteCategory(Category category) {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Category> categories = realm.where(Category.class).equalTo("mId",category.getId()).findAll();
        if (categories.size() > 0) {
            categories.deleteAllFromRealm();
        }
    }

    //endregion
}
