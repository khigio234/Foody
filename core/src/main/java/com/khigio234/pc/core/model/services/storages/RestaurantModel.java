package com.khigio234.pc.core.model.services.storages;

import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.view.ICallback;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

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

    //endregion

    //region Private methods



    //endregion
}
