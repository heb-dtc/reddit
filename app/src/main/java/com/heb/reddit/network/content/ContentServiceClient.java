package com.heb.reddit.network.content;

import android.util.Log;

import com.heb.reddit.network.authentication.TokenSafe;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

public class ContentServiceClient implements Callback<ContentResponse> {
    private static final String TAG = ContentServiceClient.class.getCanonicalName();

    private ContentService contentService;

    public ContentServiceClient(TokenSafe tokenSafe) {
        contentService = new ContentService(tokenSafe, this);
    }

    public void fetchPageContent(String pageName) {
        contentService.getContent(pageName);
    }

    public void fetchSubRedditContent(String subReddit) {
        contentService.getSubRedditContent(subReddit);
    }

    @Override
    public void success(ContentResponse contentResponse, Response response) {
        Log.d(TAG, "Content retrieval success: " + response.getUrl());
        TypedInput body = response.getBody();

        String rawJson =body.toString();
        rawJson.isEmpty();
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d(TAG, "Content retrieval failure: " + error.getMessage());
    }
}
