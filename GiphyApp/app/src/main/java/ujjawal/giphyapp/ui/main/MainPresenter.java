package ujjawal.giphyapp.ui.main;

import android.util.Log;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import io.objectbox.BoxStore;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ujjawal.giphyapp.data.DataManager;
import ujjawal.giphyapp.data.network.model.MainResponse;
import ujjawal.giphyapp.ui.base.BasePresenter;
import ujjawal.giphyapp.utils.rx.SchedulerProvider;


/**
 * Created by ujjawal on 30/05/18.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void doSearch(String keyWord) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getBlogApiCall(keyWord)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MainResponse>() {
                    @Override
                    public void accept(@NonNull MainResponse blogResponse) throws Exception {
                        Log.e("BlogResp", "---" + blogResponse.getData().get(0).getTitle());
                        if (blogResponse.getData() != null) {
                            getMvpView().updateAdapter(blogResponse.getData());
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
