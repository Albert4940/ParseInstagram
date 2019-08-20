package com.albert.parseinstagram.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albert.parseinstagram.Post;
import com.albert.parseinstagram.PostsAdapter;
import com.albert.parseinstagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    private static final String TAG = "PostsFragment";

    private RecyclerView rvPosts;
    protected List<Post> mPosts;
    protected PostsAdapter  adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_posts, parent, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
       rvPosts = view.findViewById(R.id.rvPosts);

        //create the data source
        mPosts = new ArrayList<>();
        //create the adapter
        adapter = new PostsAdapter(getContext(),mPosts);
        //set the adapter on the RecyclerView
        rvPosts.setAdapter(adapter);
        //set the layout manager on the RecyclerView
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }

    protected void queryPosts(){
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.setLimit(20);
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e!=null)
                {
                    Log.d(TAG,"error with query");
                    e.printStackTrace();
                    return;
                }
                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                for(int i=0; i<posts.size(); i++)
                {
                    Post post = posts.get(i);
                    Log.d(TAG,"Post: "+post.getDescription()+" username " + post.getUser().getUsername());
                }
            }
        });
    }
}
