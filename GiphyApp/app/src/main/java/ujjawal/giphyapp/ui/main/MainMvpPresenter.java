package ujjawal.giphyapp.ui.main;


import ujjawal.giphyapp.di.PerActivity;
import ujjawal.giphyapp.ui.base.MvpPresenter;

/**
 * Created by ujjawal on 30/05/18.
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void doSearch(String keyWord);

}
