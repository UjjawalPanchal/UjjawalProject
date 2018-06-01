package ujjawal.giphyapp.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ujjawal.giphyapp.MyGiphyApp;
import ujjawal.giphyapp.data.DataManager;
import ujjawal.giphyapp.di.ApplicationContext;
import ujjawal.giphyapp.di.module.ApplicationModule;

/**
 * Created by ujjawal on 30/05/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyGiphyApp app);

    //void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}