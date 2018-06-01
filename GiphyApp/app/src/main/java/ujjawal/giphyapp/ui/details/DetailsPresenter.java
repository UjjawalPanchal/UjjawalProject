package ujjawal.giphyapp.ui.details;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ujjawal.giphyapp.data.DataManager;
import ujjawal.giphyapp.ui.base.BasePresenter;
import ujjawal.giphyapp.utils.rx.SchedulerProvider;

public class DetailsPresenter<V extends DetailsMvpView> extends BasePresenter<V>
        implements DetailsMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public DetailsPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void doSearch(String keyWord) {
//        getMvpView().showLoading();
//        getCompositeDisposable().add(getDataManager()
//                .getBlogApiCall(keyWord)
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(new Consumer<MainResponse>() {
//                    @Override
//                    public void accept(@NonNull MainResponse blogResponse) throws Exception {
//                        Log.e("BlogResp", "---" + blogResponse.getData().get(0).getTitle());
//                        if (blogResponse.getData() != null) {
//                            getMvpView().updateAdapter(blogResponse.getData());
//                        }
//                        getMvpView().hideLoading();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable)
//                            throws Exception {
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        getMvpView().hideLoading();
//
//                        // handle the error here
//                        if (throwable instanceof ANError) {
//                            ANError anError = (ANError) throwable;
//                            handleApiError(anError);
//                        }
//                    }
//                }));
    }
}