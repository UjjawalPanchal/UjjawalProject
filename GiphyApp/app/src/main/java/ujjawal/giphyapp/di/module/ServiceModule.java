package ujjawal.giphyapp.di.module;

import android.app.Service;

import dagger.Module;

/**
 * Created by ujjawal on 30/05/18.
 */

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }
}
