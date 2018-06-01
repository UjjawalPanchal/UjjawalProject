package ujjawal.giphyapp.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by ujjawal on 30/05/18.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
