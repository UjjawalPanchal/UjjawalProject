package ujjawal.giphyapp.ui.splash;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ujjawal.giphyapp.data.DataManager;
import ujjawal.giphyapp.ui.base.BasePresenter;
import ujjawal.giphyapp.utils.rx.SchedulerProvider;

/**
 * Created by janisharali on 27/01/17.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        getMvpView().openMainActivity();
    }
}
