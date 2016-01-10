package com.heb.reddit.network.content;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ContentServiceDescription {
    @GET("/r/{subreddit}/.json")
    void getSubRedditContent(@Path("subreddit") String subreddit, @Query("limit") String limit, @Query("after") String after, Callback<ContentResponse> result);

    @GET("/{pageName}/.json")
    void getContent(@Path("pageName") String pageName, @Query("limit") String limit, @Query("after") String after, Callback<ContentResponse> result);
}
