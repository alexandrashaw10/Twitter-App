package com.codepath.apps.restclienttemplate.models;

import org.json.JSONObject;

public class Tweet {

    // list out the attributes
    public String body;
    public long uid; // database ID for the tweet
    public String createdAt;

    // deserialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) {
        return new Tweet();
    }
}
