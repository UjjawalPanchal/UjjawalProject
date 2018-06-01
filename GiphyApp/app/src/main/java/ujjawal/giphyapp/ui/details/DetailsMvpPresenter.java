package ujjawal.giphyapp.ui.details;

import ujjawal.giphyapp.ui.base.MvpPresenter;

public interface DetailsMvpPresenter<V extends DetailsMvpView> extends MvpPresenter<V> {

    void doSearch(String keyWord);

}
