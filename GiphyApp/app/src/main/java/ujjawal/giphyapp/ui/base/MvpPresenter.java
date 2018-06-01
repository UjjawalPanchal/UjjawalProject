package ujjawal.giphyapp.ui.base;

/**
 * Created by ujjawal on 30/05/18.
 */

import com.androidnetworking.error.ANError;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(ANError error);

}
