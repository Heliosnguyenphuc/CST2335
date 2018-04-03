package com.example.johnnytunguyen.androidlabs;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class MessageDetails extends Activity {

    //Running on the phone.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

//Just need to pass the Bundle to Message Fragemnt will take care the rest
        Bundle temp = getIntent().getExtras();
//
//        //adding fragement
        FragmentManager manager = getFragmentManager();
        MessageFragment messageFragment = new MessageFragment();
        messageFragment.setArguments(temp);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frameHolder, messageFragment );
        transaction.commit();

    }



}
