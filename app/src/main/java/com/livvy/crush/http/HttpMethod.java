package com.livvy.crush.http;

import com.livvy.crush.BuildConfig;
import com.livvy.crush.comm.Constants;
import com.livvy.crush.comm.entity.Photo;
import com.livvy.crush.util.AuthInterceptor;

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

public class HttpMethod
{
    private Retrofit retrofit;
    private static volatile HttpMethod instance;

    private static final int DEFAULT_TIMEOUT = 5;

    private static final String BASE_URL = "https://api.unsplash.com/";

    public static final String APPLICATION_ID = "5753550e88412189ec9cb65298cf4e57b440aec77b6063ea0204fc8057ce6934";

    private PhotoService photoService;


    public static HttpMethod getInstance()
    {
        if (instance == null)
        {
            synchronized (HttpMethod.class)
            {
                if (instance == null)
                {
                    return new HttpMethod();
                }
            }
        }
        return instance;
    }

    private HttpMethod()
    {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new AuthInterceptor());
        if (BuildConfig.DEBUG)
        {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            //包含header、body数据
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }
        clientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(clientBuilder.build()).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(BASE_URL).build();

        photoService = retrofit.create(PhotoService.class);
    }

    public void getPhotos(Subscriber<List<Photo>> observable, final int page, final int per_page, final String order_by)
    {
        photoService.getPhoto(page, per_page, order_by).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observable);
    }

    public void getPhotos(Subscriber<List<Photo>> observable, final int page)
    {
        getPhotos(observable, page, Constants.PER_PAGE, Photo.LATEST);
    }


}
