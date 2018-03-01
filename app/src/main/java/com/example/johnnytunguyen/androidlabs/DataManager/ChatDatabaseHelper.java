package com.example.johnnytunguyen.androidlabs.DataManager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JohnnyTuNguyen on 2018-02-28.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="Messages_Management" ;
    public static final String TABLE_NAME = "Messages";
    public static final int VERSION_NUM = 2;
    public static final String KEY_ID ="id";
    public static final String KEY_MESSAGE ="message";


    private static final String SQL= "CREATE TABLE "+TABLE_NAME + " ( "+
            KEY_ID +" integer primary key autoincrement, "
            + KEY_MESSAGE +" TEXT ); ";
    private static final String ACTIVITY_NAME ="ChatDatabaseHelper" ;


    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
        Log.i("ChatDatabaseHelper", "Calling onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion="+ i + "newVersion="+ i1);

    }



}
