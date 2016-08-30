package com.khigio234.pc.core.model.services;

import com.khigio234.pc.core.model.entities.User;
import com.khigio234.pc.core.view.ICallback;

/**
 * Created by PC on 8/31/2016.
 */
public interface IUserService {

    void logIn(User user, ICallback<User> callback);

    void register(User user, ICallback<Boolean> callback);
}
