package com.albert.parseinstagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText etUserName;
    private EditText etPassWord;
    private Button btnLogin;
    private Button btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName=findViewById(R.id.etUserName);
        etPassWord=findViewById(R.id.etPassWord);
        btnLogin=findViewById(R.id.btnLogin);
        btnSign=findViewById(R.id.btnSign);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUserName.getText().toString();
                String pass = etPassWord.getText().toString();
                login(user,pass);
            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
// Set core properties
                final String userN = etUserName.getText().toString();
                final String pass = etPassWord.getText().toString();
                user.setUsername(userN);
                user.setPassword(pass);
                //user.setEmail("email@example.com");
// Set custom properties
               // user.put("phone", "650-253-0000");
// Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            login(userN,pass);
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });
    }

    private void login(String user, String pass){
        //TODO: NAVIGATE
        ParseUser.logInInBackground(user, pass, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null)
                {
                    Log.e(TAG,"Issue with login");
                    e.printStackTrace();
                    return;
                }

                //TODO: NAVIGATE TO NEW
                goMainActivity();
            }
        });
    }

    private void goMainActivity()
    {
        Log.d(TAG,"Navigating to main activity");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
