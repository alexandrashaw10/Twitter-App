package com.codepath.apps.restclienttemplate;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailViewActivity extends AppCompatActivity {

    Tweet tweet;
    TextView username;
    TextView body;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.twitter_blue)));
        getSupportActionBar().setTitle("Twitter");

        // resolve view objects
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        username = findViewById(R.id.username);
        body = findViewById(R.id.body);
        profileImage = findViewById(R.id.profileImage);

        username.setText(tweet.user.name);
        body.setText(tweet.body);

        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(profileImage);
    }
}
