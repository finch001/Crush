package com.livvy.crush.comm.http;

import com.livvy.crush.comm.entity.Photo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Finch on 2016/11/28 0028.
 */

public interface PhotoService
{
    public String LATEST = "latest";
    public String OLDEST = "oldest";
    public String POPULAR = "popular";

    @GET("photos")
    Observable<List<Photo>> getPhoto(
            @Query("page")
                    int page,
            @Query("per_page")
                    int per_page,
            @Query("order_by")
                    String orderBy);

    @GET("photos")
    Observable<List<Photo>> getPhoto(
            @Query("page")
                    int page);




}
