package com.heb.reddit.network.content;

import com.google.gson.annotations.SerializedName;

public class ContentResponse {
    @SerializedName("display_name")
    String displayName;

    @SerializedName("public_description")
    String publicDescription;

    @SerializedName("title")
    String title;

    @SerializedName("url")
    String url;
}
