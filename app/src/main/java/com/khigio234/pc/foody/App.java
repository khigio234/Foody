package com.khigio234.pc.foody;

import com.khigio234.pc.core.di.AppModule;
import com.khigio234.pc.core.di.CloudModule;
import com.khigio234.pc.core.di.StorageModule;
import com.khigio234.pc.core.di.ViewModelModule;
import com.khigio234.pc.core.view.BaseApplication;
import com.khigio234.pc.core.view.Constants;
import com.khigio234.pc.core.view.INavigator;
import com.khigio234.pc.foody.activities.MainActivity;

/**
 * Created by PC on 8/2/2016.
 */
public class App extends BaseApplication{

    //region Properties

    private static AppComponent sAppComponent;

    //endregion

    //region Getter and Setter

    public synchronized static AppComponent sharedComponent() {
        return sAppComponent;
    }

    //endregion

    //region Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        AppModule appModule = new AppModule(this);
        INavigator navigator = appModule.getNavigator();
        navigator.configure(Constants.MAIN_PAGE, MainActivity.class);


        sAppComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .cloudModule(new CloudModule())
                .storageModule(new StorageModule())
                .viewModelModule(new ViewModelModule())
                .build();

    }

    //endregion
}
