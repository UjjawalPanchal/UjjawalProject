package ujjawal.giphyapp.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ujjawal.giphyapp.BuildConfig;
import ujjawal.giphyapp.R;
import ujjawal.giphyapp.data.AppDataManager;
import ujjawal.giphyapp.data.DataManager;
import ujjawal.giphyapp.data.network.ApiHeader;
import ujjawal.giphyapp.data.network.ApiHelper;
import ujjawal.giphyapp.data.network.AppApiHelper;
import ujjawal.giphyapp.di.ApiInfo;
import ujjawal.giphyapp.di.ApplicationContext;
import ujjawal.giphyapp.di.DatabaseInfo;
import ujjawal.giphyapp.di.PreferenceInfo;
import ujjawal.giphyapp.utils.AppConstants;

/**
 * Created by ujjawal on 30/05/18.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey) {
        return new ApiHeader.ProtectedApiHeader(apiKey);
    }
}
