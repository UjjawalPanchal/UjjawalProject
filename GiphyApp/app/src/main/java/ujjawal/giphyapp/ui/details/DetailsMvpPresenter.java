package ujjawal.giphyapp.ui.details;

import ujjawal.giphyapp.ui.base.MvpPresenter;

/**
 * Created by ujjawal on 30/05/18.
 */

public interface DetailsMvpPresenter<V extends DetailsMvpView> extends MvpPresenter<V> {

    void updateTextCount(long id, boolean isUp, String vidId);

}
