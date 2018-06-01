package ujjawal.giphyapp;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import javax.inject.Inject;

import ujjawal.giphyapp.data.DataManager;
import com.androidnetworking.interceptors.HttpLoggingInterceptor.Level;
import ujjawal.giphyapp.di.component.ApplicationComponent;
import ujjawal.giphyapp.di.component.DaggerApplicationComponent;
import ujjawal.giphyapp.di.module.ApplicationModule;
import ujjawal.giphyapp.utils.AppLogger;

public class MyGiphyApp extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(Level.BODY);
        }
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}

