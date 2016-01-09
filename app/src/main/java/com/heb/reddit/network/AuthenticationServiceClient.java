package com.heb.reddit.network;

import android.util.Log;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AuthenticationServiceClient implements Callback<TokenResponse> {
    private static final String TAG = AuthenticationServiceClient.class.getCanonicalName();
    private final AuthenticationService service;
    private final TokenSafe tokenSafe;

    public AuthenticationServiceClient(TokenSafe tokenSafe) {
        this.tokenSafe = tokenSafe;
        this.service = AuthenticationService.newInstance(this);
    }

    public void authenticate() {
        service.authenticate();
    }

    @Override
    public void success(TokenResponse tokenResponse, Response response) {
        if (tokenResponse != null) {
            Log.d(TAG, "Token retrieval success: " + tokenResponse.accessToken);
            tokenSafe.saveToken(tokenResponse.accessToken);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d(TAG, "Token retrieval failure: " + error.getMessage());
    }
}
