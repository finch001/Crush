package com.livvy.crush.comm.util;

import com.livvy.crush.comm.http.HttpMethod;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Finch on 2016/11/28 0028.
 */

public class AuthInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request().newBuilder().addHeader("Authorization", "Client-ID " + HttpMethod.APPLICATION_ID).build();
        return chain.proceed(request);
    }
}
