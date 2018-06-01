package ujjawal.giphyapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;


import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ujjawal.giphyapp.data.network.model.MainResponse;
import ujjawal.giphyapp.di.ActivityContext;
import ujjawal.giphyapp.di.PerActivity;
import ujjawal.giphyapp.ui.details.DetailsMvpPresenter;
import ujjawal.giphyapp.ui.details.DetailsMvpView;
import ujjawal.giphyapp.ui.details.DetailsPresenter;
import ujjawal.giphyapp.ui.main.GiphyListAdapter;
import ujjawal.giphyapp.ui.main.MainMvpPresenter;
import ujjawal.giphyapp.ui.main.MainMvpView;
import ujjawal.giphyapp.ui.main.MainPresenter;
import ujjawal.giphyapp.ui.splash.SplashMvpPresenter;
import ujjawal.giphyapp.ui.splash.SplashMvpView;
import ujjawal.giphyapp.ui.splash.SplashPresenter;
import ujjawal.giphyapp.utils.rx.AppSchedulerProvider;
import ujjawal.giphyapp.utils.rx.SchedulerProvider;

/**
 * Created by ujjawal on 30/05/18.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailsMvpPresenter<DetailsMvpView> provideDetailsPresenter(
            DetailsPresenter<DetailsMvpView> presenter) {
        return presenter;
    }

    @Provides
    GiphyListAdapter provideBlogAdapter() {
        return new GiphyListAdapter(new ArrayList<MainResponse.DataInner>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
