package com.heb.reddit.network;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.mime.TypedInput;

public interface AuthenticationServiceDescription {
    @POST("/api/v1/access_token")
    void authenticate(@Body TypedInput body, Callback<TokenResponse> result);
}
