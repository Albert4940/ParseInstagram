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

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText etUserName;
    private EditText etPassWord;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName=findViewById(R.id.etUserName);
        etPassWord=findViewById(R.id.etPassWord);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUserName.getText().toString();
                String pass = etPassWord.getText().toString();
                login(user,pass);
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
