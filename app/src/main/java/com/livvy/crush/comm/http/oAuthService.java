package com.livvy.crush.comm.http;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by finch on 2017/1/3.
 */

public interface OAuthService {

    @GET("authorize")
    Observable<String> getOauthCode(@Query("client_id") String client_id, @Query("redirect_uri") String redirect_uri, @Query("response_type") String response_type);

    @FormUrlEncoded
    @POST("token")
    Observable<String> getToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret, @Field("code") String code, @Field("grant_type") String grant_type);

}
