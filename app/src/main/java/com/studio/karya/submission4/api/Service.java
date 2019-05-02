package com.studio.karya.submission4.api;

import com.studio.karya.submission4.model.ContentResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    //get movie
    @GET("3/discover/movie")
    Observable<ContentResponse> getMovie(@Query("api_key") String api_key,
                                         @Query("language") String language);

    //get tv
    @GET("3/discover/tv")
    Observable<ContentResponse> getTv(@Query("api_key") String api_key,
                                      @Query("language") String language);

    //search movie
    @GET("3/search/movie")
    Observable<ContentResponse> searchMovie(@Query("api_key") String api_key,
                                            @Query("language") String language,
                                            @Query("query") String query);

    //search tv
    @GET("3/search/tv")
    Observable<ContentResponse> searchTv(@Query("api_key") String api_key,
                                            @Query("language") String language,
                                            @Query("query") String query);
}
