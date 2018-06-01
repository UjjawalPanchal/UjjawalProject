package ujjawal.giphyapp.data;


import android.content.Context;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import ujjawal.giphyapp.data.network.ApiHeader;
import ujjawal.giphyapp.data.network.ApiHelper;
import ujjawal.giphyapp.data.network.model.MainResponse;
import ujjawal.giphyapp.di.ApplicationContext;

/**
 * Created by ujjawal on 30/05/18.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<MainResponse> getBlogApiCall(String keyWord) {
        return mApiHelper.getBlogApiCall(keyWord);
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
//        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
//        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

}
