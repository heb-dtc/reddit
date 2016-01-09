package com.heb.reddit.network.authentication;

import java.nio.charset.Charset;
import java.util.UUID;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

class AuthenticationService {
    private static final String BASE_URL = "https://www.reddit.com";
    private static final String REQUEST_BODY = "grant_type=https://oauth.reddit.com/grants/installed_client&device_id=";

    private final AuthenticationServiceDescription serviceDescription;
    private final Callback authenticationCallback;

    public static AuthenticationService newInstance(Callback authenticationCallback) {
        RedditAuthenticator redditAuthenticator = new RedditAuthenticator();
        return new AuthenticationService(redditAuthenticator, authenticationCallback);
    }

    AuthenticationService(RedditAuthenticator redditAuthenticator, Callback authenticationCallback) {
        this.authenticationCallback = authenticationCallback;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(redditAuthenticator.getClient())
                .setEndpoint(BASE_URL).build();

        serviceDescription = restAdapter.create(AuthenticationServiceDescription.class);
    }

    public void authenticate() {
        String bodyString =  REQUEST_BODY + UUID.randomUUID().toString();
        TypedInput requestBody = new TypedByteArray("application/x-www-form-urlencoded", bodyString.getBytes(Charset.forName("UTF-8")));

        serviceDescription.authenticate(requestBody, authenticationCallback);
    }
}
