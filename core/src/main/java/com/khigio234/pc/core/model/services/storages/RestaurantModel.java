package com.khigio234.pc.core.model.services.storages;

import android.util.Log;

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

    public void getRestaurants(long offset, long limit, ICallback<List<Restaurant>> callback) {

    }

    public void getRestaurantsAsync(final ICallback<List<Restaurant>> callback) {
        Realm realm = Realm.getDefaultInstance();

        final RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).findAllAsync();
        restaurants.addChangeListener(new RealmChangeListener<RealmResults<Restaurant>>() {
            @Override
            public void onChange(RealmResults<Restaurant> element) {
                callback.onResult(element);

                restaurants.removeChangeListener(this);
            }
        });
    }

    public void getLatestRestaurantsAsync(final ICallback<List<Restaurant>> callback) {
        final Realm realm = Realm.getDefaultInstance();

        final RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).findAllSortedAsync("mCreatedAt", Sort.DESCENDING);

        restaurants.addChangeListener(new RealmChangeListener<RealmResults<Restaurant>>() {
            @Override
            public void onChange(RealmResults<Restaurant> element) {
                List<Restaurant> detachedRestaurants = realm.copyFromRealm(element);
                callback.onResult(detachedRestaurants);
            }
        });
    }

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

    public RealmResults<Restaurant> getLatestRestaurants() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).findAllSortedAsync("mCreatedAt", Sort.DESCENDING);
        return restaurants;
    }

    public List<Restaurant> getNewRestaurants() {
        final Realm realm = Realm.getDefaultInstance();

        RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).findAllSorted("mCreatedAt", Sort.DESCENDING);
        List<Restaurant> newRestaurants = realm.copyFromRealm(restaurants);
        return newRestaurants;
    }

    public void saveRestaurant(final Restaurant restaurant, final ICallback<Boolean> callback) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Restaurant realmRestaurant = bgRealm.copyFromRealm(restaurant);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onResult(true);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callback.onFailure(error);
            }
        });
    }

    public Date getLatestSynchronizeTimestamp() {
        Realm realm = Realm.getDefaultInstance();

        SyncHistory syncHistory = realm.where(SyncHistory.class).equalTo("mNameTable", RESTAURANT_TABLE_NAME).findFirst();
        if (syncHistory != null) {
            return syncHistory.getLastSyncTimestamp();
        }
        return null;
    }

    public void handleFetchedRestaurant(List<Restaurant> restaurants, Date latestSynchronizeTimestamp) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        for (Restaurant restaurant: restaurants) {
            Log.d(TAG, restaurant.toString());
            if (restaurant.isDeleted()) {
                deleteRestaurant(restaurant);
            } else {
                addNewOrUpdateRestaurant(restaurant);
            }
        }

        optimizeCachedRestaurant();

        updateLatestSynchronizeTimestamp(latestSynchronizeTimestamp);

        realm.commitTransaction();
    }

    //endregion

    //region Private methods

    private void deleteRestaurant(Restaurant restaurant) {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).equalTo("mId", restaurant.getId()).findAll();
        if (restaurants.size() > 0) {
            restaurants.deleteAllFromRealm();
        }
    }

    private void addNewOrUpdateRestaurant(Restaurant restaurant) {
        Realm realm = Realm.getDefaultInstance();

        realm.copyToRealmOrUpdate(restaurant);
    }

    private void updateLatestSynchronizeTimestamp(Date latestSynchronizeTimestamp) {
        Realm realm = Realm.getDefaultInstance();

        SyncHistory syncHistory = realm.where(SyncHistory.class).equalTo("mNameTable", RESTAURANT_TABLE_NAME).findFirst();
        if (syncHistory == null) {
            syncHistory = realm.createObject(SyncHistory.class);
            syncHistory.setNameTable(RESTAURANT_TABLE_NAME);
        }
        syncHistory.setLastSyncTimestamp(latestSynchronizeTimestamp);
    }

    private void optimizeCachedRestaurant() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).findAllSorted("mUpdatedAt", Sort.DESCENDING);
        for (int i = Configuration.NUMBER_CACHE_RESTAURANTS; i < restaurants.size(); i++) {
            restaurants.get(i).deleteFromRealm();
        }
    }

    //endregion
}
