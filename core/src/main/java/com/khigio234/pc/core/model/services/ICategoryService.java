package com.khigio234.pc.core.model.services;

import com.khigio234.pc.core.model.entities.Category;
import com.khigio234.pc.core.view.ICallback;

import java.util.List;

/**
 * Created by PC on 8/4/2016.
 */
public interface ICategoryService {

    void getAllCategories(ICallback<List<Category>> callback);

    void saveCategories(List<Category> categories, ICallback<Boolean> callback);
}
