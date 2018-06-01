package ujjawal.giphyapp.data.network;


import ujjawal.giphyapp.BuildConfig;

/**
 * Created by ujjawal on 30/05/18.
 */

public final class ApiEndPoint {

    public static final String ENDPOINT_BLOG = BuildConfig.BASE_URL + "/search?api_key=dc6zaTOxFJmzC&q=";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
