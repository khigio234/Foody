package com.khigio234.pc.core.di;

import com.khigio234.pc.core.view.BaseApplication;
import com.khigio234.pc.core.view.INavigator;
import com.khigio234.pc.core.view.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by PC on 8/2/2016.
 */

@Module
public class AppModule {

    //region Properties

    private INavigator mNavigator;

    //endregion

    //region Getter and Setter

    public INavigator getNavigator() {
        return mNavigator;
    }

    //endregion

    //region Constructors

    public AppModule(BaseApplication application) {
        mNavigator = new Navigator(application);
    }

    //endregion

    //region Provide methods

    @Provides
    @Singleton
    BaseApplication providesApplication() {
        return mNavigator.getApplication();
    }

    @Provides
    @Singleton
    INavigator providesNavigator() {
        return mNavigator;
    }

    //endregion

}
