package ujjawal.giphyapp.data.network;

import io.reactivex.Single;
import ujjawal.giphyapp.data.network.model.MainResponse;

/**
 * Created by ujjawal on 30/05/18.
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<MainResponse> getBlogApiCall(String keyWord);

}
