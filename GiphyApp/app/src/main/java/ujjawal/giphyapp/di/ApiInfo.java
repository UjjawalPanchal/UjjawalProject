package ujjawal.giphyapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ujjawal on 30/05/18.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiInfo {
}
