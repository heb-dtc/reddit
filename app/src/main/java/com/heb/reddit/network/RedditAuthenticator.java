package com.heb.reddit.network;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.Proxy;

import retrofit.client.OkClient;

public class RedditAuthenticator implements Authenticator {
    private static final String CLIENT_ID = "S0jNUnPTkDvJNQ";
    private static final String CLIENT_SECRET = "";

    private OkHttpClient okHttpClient;

    public RedditAuthenticator() {
        okHttpClient = new OkHttpClient();
        okHttpClient.setAuthenticator(this);
    }

    public OkClient getClient() {
        return new OkClient(okHttpClient);
    }

    @Override
    public Request authenticate(Proxy proxy, Response response) throws IOException {
        String credential = Credentials.basic(CLIENT_ID, CLIENT_SECRET);
        return response.request().newBuilder().header("Authorization", credential).build();
    }

    @Override
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        return null;
    }
}
