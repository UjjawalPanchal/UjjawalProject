package ujjawal.giphyapp.ui.main;

import java.util.List;

import ujjawal.giphyapp.data.network.model.MainResponse;
import ujjawal.giphyapp.ui.base.MvpView;

/**
 * Created by ujjawal on 30/05/18.
 */

public interface MainMvpView extends MvpView {

    void updateAdapter(List<MainResponse.DataInner> blogList);

}
