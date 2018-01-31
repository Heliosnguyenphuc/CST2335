package com.example.johnnytunguyen.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements View.OnClickListener{
    protected static final String ACTIVITY_NAME = "StartActivity";
    private Button btn;
    private EditText email;
    SharedPreferences   sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();

        sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
        // take value from sharedPreferences
        email.setText(sharedPreferences.getString("DefaultEmail","email@domain.com"));

        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
        startActivity(intent);



    }

    public void anhXa(){
        btn = findViewById(R.id.login_btn);
        email = findViewById(R.id.email_name);

    }

    // for the button
    @Override
    public void onClick(View view) {
        String Email_login = email.getText().toString().trim();
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("DefaultEmail",Email_login);
        editor.commit();


    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("ACTIVITY_NAME","IN onStart()");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("ACTIVITY_NAME","IN onResume()");

    }


    @Override
    public void onPause(){
        super.onPause();
        Log.i("ACTIVITY_NAME","IN onPause()");

    }


    @Override
    public void onStop(){
        super.onStop();
        Log.i("ACTIVITY_NAME","IN onStop()");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("ACTIVITY_NAME","IN  onDestroy()");

    }



}
