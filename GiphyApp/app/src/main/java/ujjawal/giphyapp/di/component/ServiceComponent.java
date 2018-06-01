package ujjawal.giphyapp.di.component;

import dagger.Component;
import ujjawal.giphyapp.di.PerService;
import ujjawal.giphyapp.di.module.ServiceModule;

/**
 * Created by ujjawal on 30/05/18.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    //void inject(SyncService service);

}
