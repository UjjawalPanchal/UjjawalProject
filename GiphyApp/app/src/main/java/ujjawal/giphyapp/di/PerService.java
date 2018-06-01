package ujjawal.giphyapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ujjawal on 30/05/18.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}

