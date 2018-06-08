package ujjawal.giphyapp;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import javax.inject.Inject;

import io.objectbox.BoxStore;
import ujjawal.giphyapp.data.DataManager;

import com.androidnetworking.interceptors.HttpLoggingInterceptor.Level;

import ujjawal.giphyapp.data.network.model.MyObjectBox;
import ujjawal.giphyapp.di.component.ApplicationComponent;
import ujjawal.giphyapp.di.component.DaggerApplicationComponent;
import ujjawal.giphyapp.di.module.ApplicationModule;
import ujjawal.giphyapp.utils.AppLogger;

public class MyGiphyApp extends Application {

    @Inject
    DataManager mDataManager;

    private BoxStore boxStore;

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

       boxStore = MyObjectBox.builder().androidContext(MyGiphyApp.this).build();
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

    public BoxStore getBoxStore() {
        return boxStore;
    }
}

