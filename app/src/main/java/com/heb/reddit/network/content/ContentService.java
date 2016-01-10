package com.heb.reddit.network.content;

import com.heb.reddit.network.authentication.TokenSafe;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class ContentService {
    private ContentServiceDescription serviceDescription;
    private TokenSafe tokenSafe;
    private Callback contentRequestCallback;

    public ContentService(TokenSafe tokenSafe, Callback contentRequestCallback) {
        this.tokenSafe = tokenSafe;
        this.contentRequestCallback = contentRequestCallback;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(getRequestInterceptorToken())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://oauth.reddit.com")
                .build();

        serviceDescription = restAdapter.create(ContentServiceDescription.class);
    }

    private RequestInterceptor getRequestInterceptorToken() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
                String token = tokenSafe.retrieveToken();

                if (token.isEmpty()) {
                    //TODO: exit gracefully? renew token?
                } else {
                    request.addHeader("Authorization", "bearer " + token);
                }
            }
        };

        return requestInterceptor;
    }

    public void getContent(String pageName) {
        serviceDescription.getContent(pageName, "2", "", contentRequestCallback);
    }

    public void getSubRedditContent(String subReddit) {
        serviceDescription.getSubRedditContent(subReddit, "2", "", contentRequestCallback);
    }
}

