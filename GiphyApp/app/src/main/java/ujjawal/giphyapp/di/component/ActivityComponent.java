package ujjawal.giphyapp.di.component;

import dagger.Component;
import ujjawal.giphyapp.di.PerActivity;
import ujjawal.giphyapp.di.module.ActivityModule;
import ujjawal.giphyapp.ui.details.DetailsActivity;
import ujjawal.giphyapp.ui.main.MainActivity;
import ujjawal.giphyapp.ui.splash.SplashActivity;

/**
 * Created by ujjawal on 30/05/18.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(MainActivity activity);

    void inject(DetailsActivity activity);

}
