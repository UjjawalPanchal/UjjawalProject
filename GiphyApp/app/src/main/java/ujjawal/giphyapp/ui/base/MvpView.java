package ujjawal.giphyapp.ui.base;

/**
 * Created by ujjawal on 30/05/18.
 */

import android.support.annotation.StringRes;

import io.objectbox.BoxStore;

public interface MvpView {

    BoxStore getBox();

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

}
