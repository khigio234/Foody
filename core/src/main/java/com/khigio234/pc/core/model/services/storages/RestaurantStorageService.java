package com.khigio234.pc.core.model.services.storages;

import com.khigio234.pc.core.model.entities.Restaurant;
import com.khigio234.pc.core.model.services.IRestaurantService;
import com.khigio234.pc.core.view.ICallback;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by PC on 8/16/2016.
 */
public class RestaurantStorageService extends BaseStorageService implements IRestaurantService {

    //region Constructor

    public RestaurantStorageService(Realm realm) {
        super(realm);
    }

    //endregion

    //region Lifecycle

    @Override
    public void getAllRestaurants(final ICallback<List<Restaurant>> callback) {
        final RealmResults<Restaurant> restaurants = mRealm.where(Restaurant.class).findAllAsync();
        restaurants.addChangeListener(new RealmChangeListener<RealmResults<Restaurant>>() {
            @Override
            public void onChange(RealmResults<Restaurant> element) {
                callback.onResult(element);

                restaurants.removeChangeListener(this);
            }
        });
    }

    @Override
    public void saveRestaurants(final List<Restaurant> restaurants, final ICallback<Boolean> callback) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(restaurants);
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
}
