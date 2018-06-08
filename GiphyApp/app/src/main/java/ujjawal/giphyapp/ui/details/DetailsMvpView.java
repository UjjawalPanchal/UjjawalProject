package ujjawal.giphyapp.ui.details;

import java.util.List;

import ujjawal.giphyapp.data.network.model.MainResponse;
import ujjawal.giphyapp.ui.base.MvpView;

/**
 * Created by ujjawal on 30/05/18.
 */

public interface DetailsMvpView extends MvpView {

    void doThumbsUp();

    void doThumbsDown();

    void getReviews();
}