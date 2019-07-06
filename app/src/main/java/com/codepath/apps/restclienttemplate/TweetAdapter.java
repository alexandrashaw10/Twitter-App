package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private List<Tweet> mTweets;
    private Context context;

    // pass in the tweets array into the constructor
    public TweetAdapter(List<Tweet> tweets) {
        mTweets = tweets;
    }

    // for each row, inflate the layout and cache references into ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }


    // bind values based on the position of that element
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // populate the view with the needed information
        final Tweet tweet = mTweets.get(position);
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);

        String formattedTime = TimeFormatter.getTimeDifference(tweet.createdAt);
        holder.tvRelativeTime.setText(formattedTime);

        holder.likeCount.setText(Integer.toString(tweet.favoriteCount));
        holder.retweetCount.setText(Integer.toString(tweet.retweetCount));

        holder.ic_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tweet.isFavorited) {
                //    client.unfavoriteTweet(tweet.uid);
                } else {
                 //   client.favoriteTweet(tweet.uid);
                }
            }
        });

        holder.ic_retweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // import image as a circle
        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivProfileImage);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    // create the ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvRelativeTime;
        public ImageButton ic_like;
        public ImageButton ic_retweet;
        public ImageButton ic_reply;

        public TextView likeCount;
        public TextView retweetCount;

        public ViewHolder(View itemView) {
            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvRelativeTime = (TextView) itemView.findViewById(R.id.tvRelativeTime);

            ic_like = itemView.findViewById(R.id.ic_like);
            ic_retweet = itemView.findViewById(R.id.ic_retweet);
            ic_reply = itemView.findViewById(R.id.ic_reply);

            likeCount = itemView.findViewById(R.id.tvLikeCount);
            retweetCount = itemView.findViewById(R.id.tvRetweetCount);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // switch to details activity when you click on a tweet
            int position = getAdapterPosition();

            Log.i("onClickedTweet", String.format("position %s ", position));
            if (position != RecyclerView.NO_POSITION) {
                Tweet clickedTweet = mTweets.get(position);

                Intent intent = new Intent(context, DetailViewActivity.class);
                intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(clickedTweet));
                Log.i("onClickedTweet", "created_intent");

                context.startActivity(intent);
            }
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(ArrayList<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }
}
