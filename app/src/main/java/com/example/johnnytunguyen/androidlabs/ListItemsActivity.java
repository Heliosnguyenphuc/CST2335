package com.example.johnnytunguyen.androidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {

    ImageButton img;
    Switch swt_bar;
    CheckBox checkBox;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        img= findViewById(R.id.imgBtn);
        swt_bar = findViewById(R.id.switch_bar);
        checkBox = findViewById(R.id.cb);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });


        swt_bar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                CharSequence text = "Switch is off";// "Switch is Off"
                int duration = Toast.LENGTH_SHORT; //= Toast.LENGTH_LONG if Off

                if(b==true) {
                    Toast toast = Toast.makeText(ListItemsActivity.this, text, duration);
                    toast.show();
                }else {
                    Toast toast = Toast.makeText(ListItemsActivity.this,"Switch is on", duration);
                    toast.show();
                }
            }// event click
        });


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                        .setTitle(R.string.dialog_title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", "Here is my response");
                                setResult(Activity.RESULT_OK, resultIntent);

                                finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        }).show();

            }
        });

    }// end on create

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
        }
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
