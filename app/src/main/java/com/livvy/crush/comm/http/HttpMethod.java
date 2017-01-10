package com.livvy.crush.comm.http;

import com.livvy.crush.BuildConfig;
import com.livvy.crush.comm.Constants;
import com.livvy.crush.comm.entity.Photo;
import com.livvy.crush.comm.util.AuthInterceptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Finch on 2016/11/28 0028.
 */

public class HttpMethod {
    private Retrofit retrofit;
    private static volatile HttpMethod instance;

    private static final int DEFAULT_TIMEOUT = 5;

    private static final String API_BASE_URL = "https://api.unsplash.com/";

    private static final String OAUTH_BASE_URL = "https://unsplash.com/oauth/";

    public static final String APPLICATION_ID = "5753550e88412189ec9cb65298cf4e57b440aec77b6063ea0204fc8057ce6934";

    public static final String CLIENT_SECRET = "628264b6837df3f64ac5de82fd321a45f1007b0c018c776fbee0603aaeeb7055";

    public static final String REDICRECT_URI = "redirect_uri = urn:ietf:wg:oauth:2.0:oob";

    public static final String RESPONSE_TYPE = "code";

    public static final String CACHE_URI = "/storage/emulated/0/Android/data/com.livvy.crush/cache";

    private PhotoService photoService;

    private OAuthService authService;


    public static HttpMethod getInstance() {
        if (instance == null) {
            synchronized (HttpMethod.class) {
                if (instance == null) {
                    return new HttpMethod();
                }
            }
        }
        return instance;
    }

    private HttpMethod() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new AuthInterceptor());
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            //包含header、body数据
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }
        clientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder().client(clientBuilder.build()).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(API_BASE_URL).build();

        photoService = retrofit.create(PhotoService.class);
    }

    public void getPhotos(Subscriber<List<Photo>> observable, final int page, final int per_page, final String order_by) {
        photoService.getPhoto(page, per_page, order_by).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observable);
    }

    public void getPhotos(Subscriber<List<Photo>> observable, final int page) {
        getPhotos(observable, page, Constants.PER_PAGE, Photo.LATEST);
    }

}
