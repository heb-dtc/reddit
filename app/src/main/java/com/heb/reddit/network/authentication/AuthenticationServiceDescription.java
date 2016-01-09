package com.heb.reddit.network.authentication;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.mime.TypedInput;

interface AuthenticationServiceDescription {
    @POST("/api/v1/access_token")
    void authenticate(@Body TypedInput body, Callback<TokenResponse> result);
}
