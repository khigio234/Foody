package com.khigio234.pc.core.di;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.khigio234.pc.core.view.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by PC on 9/2/2016.
 */
@Module(includes = AppModule.class)
public class JobModule {

    @Provides
    @Singleton
    JobManager providesJobManager(BaseApplication baseApplication) {
        Configuration.Builder builder = new Configuration.Builder(baseApplication)
                .minConsumerCount(1)
                .maxConsumerCount(3)
                .loadFactor(3)
                .consumerKeepAlive(120);

        return new JobManager(builder.build());
    }
}
