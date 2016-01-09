package com.heb.reddit.network.authentication;

import com.google.gson.annotations.SerializedName;

class TokenResponse {
    @SerializedName("access_token")
    String accessToken;

    @SerializedName("token_type")
    String tokenType;

    @SerializedName("expires_in")
    String expiresIn;

    @SerializedName("scope")
    String scope;
}
