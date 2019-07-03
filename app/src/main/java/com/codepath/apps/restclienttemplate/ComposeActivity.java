package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    public static final String RESULT_TWEET_KEY = "result_tweet";
    public static final int MAX_CHAR = 280;
    EditText etTweetInput;
    Button btnSend;
    TextView tvCharCountdown;
    TwitterClient client;
    private int charCountdown;
    private int charsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        charCountdown = MAX_CHAR;

        etTweetInput = findViewById(R.id.etTweetInput);
        btnSend = findViewById(R.id.btnSend);
        tvCharCountdown = findViewById(R.id.tvCharCountdown);

        client = new TwitterClient(this);

        // TODO set countdown to match how much you have typed
        etTweetInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvCharCountdown.setText(charCountdown);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int charsLeft = charCountdown - s.length();
                tvCharCountdown.setText(charsLeft);
                if (charsLeft < 0) {
                    tvCharCountdown.setTextColor(getResources().getColor(R.color.red));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTweet();
            }
        });
    }

    private void sendTweet() {
        client.sendTweet(etTweetInput.getText().toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // TODO check char count before sending
                if (statusCode == 200) {
                    try {
                        // parsing response
                        JSONObject responseJson = new JSONObject(new String(responseBody));
                        Tweet resultTweet = Tweet.fromJSON(responseJson);

                        // return result to calling activity with parceled tweet
                        Intent resultData = new Intent();
                        resultData.putExtra(RESULT_TWEET_KEY, Parcels.wrap(resultTweet));

                        setResult(RESULT_OK, resultData);
                        finish();

                    } catch (JSONException e) {
                        Log.e("ComposeActivity", "Error parsing response", e);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("sending_tweet", "parsing text failed");
            }
        });
    }
}
