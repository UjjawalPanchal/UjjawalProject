package ujjawal.giphyapp.data;


import ujjawal.giphyapp.data.network.ApiHelper;

/**
 * Created by ujjawal on 30/05/18.
 */

public interface DataManager extends ApiHelper {

    void updateApiHeader(Long userId, String accessToken);
}
