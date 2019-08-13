package com.albert.parseinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText etDescription;
    private Button btnImage;
    private ImageView ivPoster;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDescription = findViewById(R.id.etDescription);
        btnImage = findViewById(R.id.btnCaptureImage);
        ivPoster = findViewById(R.id.ivPosterImage);
        btnSubmit = findViewById(R.id.btnSubmit);

        queryPosts();
    }

    private void queryPosts(){
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e!=null)
                {
                    Log.d(TAG,"error with query");
                }
                for(int i=0; i<posts.size(); i++)
                {
                    Post post = posts.get(i);
                    Log.d(TAG,"Post: "+post.getDescription()+" username " + post.getUser().getUsername());
                }
            }
        });
    }
}
