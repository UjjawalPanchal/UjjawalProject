package ujjawal.giphyapp.data.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import ujjawal.giphyapp.data.network.model.MainResponse;

/**
 * Created by janisharali on 28/01/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<MainResponse> getBlogApiCall(String keyWord) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG + keyWord)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(MainResponse.class);
    }

}

