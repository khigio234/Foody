package com.khigio234.pc.foody;

import com.khigio234.pc.core.di.ViewModelModule;
import com.khigio234.pc.foody.activities.MainActivity;
import com.khigio234.pc.foody.fragments.CategoryFragment;
import com.khigio234.pc.foody.fragments.NewsFeedFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by PC on 8/2/2016.
 */

@Singleton
@Component(modules = {ViewModelModule.class })
public interface AppComponent {

    //region Activities

    void inject(MainActivity activity);

    //endregion

    //region Fragment

    void inject(CategoryFragment fragment);

    void inject(NewsFeedFragment fragment);

    //endregion
}
