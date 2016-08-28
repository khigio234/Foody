package com.khigio234.pc.core.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khigio234.pc.core.model.services.Configuration;
import com.khigio234.pc.core.model.services.clouds.IRestaurantCloudService;
import com.khigio234.pc.core.model.services.clouds.RestaurantCloudService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PC on 8/2/2016.
 */

@Module(includes = {AppModule.class})
public class CloudModule {

    //region Provide methods

    @Provides
    @Singleton
    public IRestaurantCloudService providesRestaurantService() {
        Gson gson = createGson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configuration.FOODY_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(IRestaurantCloudService.class);
    }

    @Provides
    @Singleton
    public RestaurantCloudService providesRestaurantCloudService(IRestaurantCloudService iRestaurantCloudService) {
        return new RestaurantCloudService(iRestaurantCloudService);
    }

    //endregion

    //region Private methods

    private Gson createGson() {
        return new GsonBuilder().setLenient()
                .setDateFormat(Configuration.TIMESTAMP_FORMAT)
                .create();
    }

    //endregion

}
