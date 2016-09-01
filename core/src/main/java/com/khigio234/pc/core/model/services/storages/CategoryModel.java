package com.khigio234.pc.core.model.services.storages;

import com.khigio234.pc.core.model.entities.Category;

import io.realm.Realm;
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



    //endregion

    //region Private methods



    //endregion
}
