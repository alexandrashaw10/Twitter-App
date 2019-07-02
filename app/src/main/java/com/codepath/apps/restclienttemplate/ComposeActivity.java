package com.codepath.apps.restclienttemplate;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class ComposeActivity extends AppCompatActivity {

    EditText etTweetInput;
    Button btnSend;
    TwitterClient client;
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etTweetInput = findViewById(R.id.etTweetInput);
        btnSend = findViewById(R.id.btnSend);

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
                if (statusCode == 200) {
                    try {
                        // parsing response
                        JSONObject responseJson = new JSONObject(new String(responseBody));
                        Tweet resultTweet = Tweet.fromJSON(responseJson);

                        // return result to calling activity
                        Intent resultData = new Intent();
                        // TODO wrap tweet in parcel and put parcel into intent
                        // this is wrong
                        resultData.putExtra("result_tweet", resultTweet.toString());
                        // make result tweet a key so you can reuse

                        setResult(RESULT_OK, resultData);
                        finish();

                    } catch (JSONException e) {
                        Log.e("ComposeActivity", "Error parsing response", e);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    */
}
