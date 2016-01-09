package com.heb.reddit.network.authentication;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenSafe {
    private static final String SP_FILE_NAME = "reddit-preferences";
    private static final String TOKEN = "TOKEN";
    private static final String EMPTY_TOKEN = "";
    private final SharedPreferences sharedPreferences;

    public static TokenSafe newInstance(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return new TokenSafe(sharedPreferences);
    }

    public TokenSafe(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveToken(String token) {
        sharedPreferences.edit()
                .putString(TOKEN, token)
                .apply();
    }

    public String retrieveToken() {
        return sharedPreferences.getString(TOKEN, EMPTY_TOKEN);
    }
}
