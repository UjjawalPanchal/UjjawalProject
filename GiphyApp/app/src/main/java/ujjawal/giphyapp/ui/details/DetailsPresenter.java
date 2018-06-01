package ujjawal.giphyapp.ui.details;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ujjawal.giphyapp.data.DataManager;
import ujjawal.giphyapp.ui.base.BasePresenter;
import ujjawal.giphyapp.utils.rx.SchedulerProvider;

/**
 * Created by ujjawal on 30/05/18.
 */

public class DetailsPresenter<V extends DetailsMvpView> extends BasePresenter<V>
        implements DetailsMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public DetailsPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

}