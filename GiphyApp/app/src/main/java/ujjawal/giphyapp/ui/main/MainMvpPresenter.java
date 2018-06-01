package ujjawal.giphyapp.ui.main;


import ujjawal.giphyapp.di.PerActivity;
import ujjawal.giphyapp.ui.base.MvpPresenter;

/**
 * Created by janisharali on 27/01/17.
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void doSearch(String keyWord);

}
