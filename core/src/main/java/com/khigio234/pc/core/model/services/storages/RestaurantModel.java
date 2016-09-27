package com.khigio234.pc.core.model.services.storages;

import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.entities.SyncHistory;
import com.khigio234.pc.core.model.services.Configuration;
import com.khigio234.pc.core.view.ICallback;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by PC on 9/1/2016.
 */
public class RestaurantModel extends BaseModel {

    //region Properties

    public static final String RESTAURANT_TABLE_NAME = "Restaurant";

    public static final String TAG = "RestaurantModel";

    //endregion

    //region Constructor

    public RestaurantModel(Realm realm) {
        super(realm);
    }

    //endregion

    //region Public methods

//    public void getLatestAsync(final ICallback<List<Restaurant>> callback) {
//        final Realm realm = Realm.getDefaultInstance();
//
//        final RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).findAllSortedAsync("mCreatedAt", Sort.DESCENDING);
//
//        restaurants.addChangeListener(new RealmChangeListener<RealmResults<Restaurant>>() {
//            @Override
//            public void onChange(RealmResults<Restaurant> element) {
//                List<Restaurant> detachedRestaurants = realm.copyFromRealm(element);
//                callback.onResult(detachedRestaurants);
//            }
//        });
//    }

    public void getRestaurantById(String id, final ICallback<Restaurant> callback) {

        final Restaurant restaurant = mRealm.where(Restaurant.class).equalTo("id", id).findFirstAsync();
        restaurant.addChangeListener(new RealmChangeListener<Restaurant>() {
            @Override
            public void onChange(Restaurant element) {
                callback.onResult(element);

                restaurant.removeChangeListener(this);
            }
        });
    }

    public List<Restaurant> getLatest() {
        final Realm realm = Realm.getDefaultInstance();

        RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).findAllSorted("mCreatedAt", Sort.DESCENDING);
        List<Restaurant> detachedRestaurants = realm.copyFromRealm(restaurants);
        return detachedRestaurants;
    }

    public Date getLatestSynchronize() {
        Realm realm = Realm.getDefaultInstance();

        SyncHistory syncHistory = realm.where(SyncHistory.class).equalTo("mNameTable", RESTAURANT_TABLE_NAME).findFirst();
        if (syncHistory != null) {
            return syncHistory.getLastSyncTimestamp();
        }
        return null;
    }

    public void delete(Restaurant restaurant) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).equalTo("mId", restaurant.getId()).findAll();
        if (restaurants.size() > 0) {
            restaurants.deleteAllFromRealm();
        }

        realm.commitTransaction();
    }

    public void addNewOrUpdate(Restaurant restaurant) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        realm.copyToRealmOrUpdate(restaurant);

        realm.commitTransaction();
    }

    public void saveLatestSynchronize(Date latestSynchronizeTimestamp) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        SyncHistory syncHistory = realm.where(SyncHistory.class).equalTo("mNameTable", RESTAURANT_TABLE_NAME).findFirst();

        if (syncHistory == null) {
            syncHistory = realm.createObject(SyncHistory.class);
            syncHistory.setNameTable(RESTAURANT_TABLE_NAME);
        } else {
            syncHistory.setLastSyncTimestamp(latestSynchronizeTimestamp);
        }

        realm.commitTransaction();
    }

    public void optimizeCached() {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).findAllSorted("mUpdatedAt", Sort.DESCENDING);
        for (int i = Configuration.NUMBER_CACHE_RESTAURANTS; i < restaurants.size(); i++) {
            restaurants.get(i).deleteFromRealm();
        }

        realm.commitTransaction();
    }

    //endregion

}
