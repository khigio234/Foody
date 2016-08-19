package com.khigio234.pc.core.model.services.storages;

import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.model.services.ICategoryService;
import com.khigio234.pc.core.view.ICallback;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by PC on 8/8/2016.
 */
public class CategoryStorageService extends BaseStorageService implements ICategoryService {

    //region Constructor

    public CategoryStorageService(Realm realm) {
        super(realm);
    }

    //endregion

    //region Lifecycle

    @Override
    public void getAllCategories(final ICallback<List<Category>> callback) {
        final RealmResults<Category> categories = mRealm.where(Category.class).findAllAsync();
        categories.addChangeListener(new RealmChangeListener<RealmResults<Category>>() {
            @Override
            public void onChange(RealmResults<Category> element) {
                callback.onResult(element);

                categories.removeChangeListeners();
            }
        });
    }

    @Override
    public void saveCategories(final List<Category> categories, final ICallback<Boolean> callback) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(categories);
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
