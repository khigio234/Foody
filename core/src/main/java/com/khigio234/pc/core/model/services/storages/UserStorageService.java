package com.khigio234.pc.core.model.services.storages;

import com.khigio234.pc.core.model.entities.User;
import com.khigio234.pc.core.model.services.IUserService;
import com.khigio234.pc.core.view.ICallback;

import java.util.Calendar;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * Created by PC on 8/31/2016.
 */
public class UserStorageService extends BaseStorageService implements IUserService {

    //region Constructor

    public UserStorageService(Realm realm) {
        super(realm);
    }


    //endregion

    //region Override method

    @Override
    public void logIn(User user, final ICallback<User> callback) {

        final User checkUser = mRealm.where(User.class).equalTo("mEmail", user.getEmail()).equalTo("mPassword", user.getPassword()).findFirstAsync();

        checkUser.addChangeListener(new RealmChangeListener<User>() {
            @Override
            public void onChange(User element) {
                callback.onResult(element);
                checkUser.removeChangeListener(this);
            }
        });
    }

    @Override
    public void register(final User user, final ICallback<Boolean> callback) {

        User checkUser = mRealm.where(User.class).equalTo("mEmail", user.getEmail()).findFirst();

        if (checkUser == null) {
            user.setId(UUID.randomUUID().toString());
            user.setCreatedAt(Calendar.getInstance().getTime());

            mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    User realmRestaurant = realm.copyFromRealm(user);
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
        }else {
            callback.onResult(false);
        }
    }


    //endregion
}
