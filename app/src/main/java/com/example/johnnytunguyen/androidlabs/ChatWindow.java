package com.example.johnnytunguyen.androidlabs;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnnytunguyen.androidlabs.DataManager.ChatDatabaseHelper;

import java.util.ArrayList;

import static com.example.johnnytunguyen.androidlabs.LoginActivity.ACTIVITY_NAME;

public class ChatWindow extends Activity {

    ListView chatV;
    Button btn;
    EditText edt;
    ArrayList<String> messages;
    ChatDatabaseHelper dbManager;
    Boolean isTablet;
    ChatAdapter messageAdapter;
    int REQUEST_CODE = 6666;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        //and xa
        chatV = findViewById(R.id.chatView);
        btn = findViewById(R.id.sendButton);
        edt= findViewById(R.id.chatBoxes);
        messages = new ArrayList<>();

        View frameForTablet = findViewById(R.id.isTablet);



        //lab 7

        if(frameForTablet == null){
            isTablet = false;
            Log.i(ACTIVITY_NAME, "Using phone layout");
        }
        else{
            isTablet = true;
            Log.i(ACTIVITY_NAME, "Using tablet layout");
        }


        //in this case, “this” is the ChatWindow, which is-A Context object
        messageAdapter  = new ChatAdapter( this );
        chatV.setAdapter (messageAdapter);



        //lab7
        final FragmentManager manager = getFragmentManager();


        chatV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                String selectQuery = "SELECT  * FROM " + dbManager.TABLE_NAME;

                Cursor cursor = dbManager.getData(selectQuery);

                //cursor.moveToFirst();
                Log.e("pos",l+"");
                cursor.moveToPosition(i);
                int id = (int) l;
                String message = cursor.getString(cursor.getColumnIndex(dbManager.KEY_MESSAGE));
                //long id = messageAdapter.getItemId((int) l);


                if(isTablet)
                {
                    Log.e("IsTablle","on the tablet");

                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    bundle.putString("message", message);
                    bundle.putBoolean("FLAG",isTablet);
                    MessageFragment mfragment = new MessageFragment();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.isTablet, mfragment,"FragmentOnTablet");
                    mfragment.setArguments(bundle);
                    transaction.commit();

//
//                    messages.remove(message);
//                    messageAdapter.notifyDataSetChanged();
                }
                else {
                    Log.e("IsTablle","on the phone");
                    //Goes to new activity created
                    Intent intent = new Intent(ChatWindow.this, MessageDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    bundle.putString("message", message);
                    bundle.putBoolean("FLAG",isTablet);
                    intent.putExtras(bundle);
                    //add fragment to the phone layout

                    startActivityForResult(intent,REQUEST_CODE);

                }


            }
        });






        // Lab 5: Reading record

         dbManager = new ChatDatabaseHelper(this);

         final SQLiteDatabase  db = dbManager.getReadableDatabase();

        //take all record to current Array<String>
        // Select All Query
        String selectQuery = "SELECT  * FROM " + dbManager.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery,null);

        while (cursor.moveToNext())

        {
            String newMessage = cursor.getString(cursor.getColumnIndex(dbManager.KEY_MESSAGE));
            messages.add(newMessage);
            Log.i(ACTIVITY_NAME, "SQL MESSAGE: " + newMessage);
        }

//
//        while(!cursor.isAfterLast() )
//            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString( cursor.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE)) );

        for (int columnIndex = 0; columnIndex < cursor.getColumnCount(); columnIndex++){
            cursor.getColumnName(columnIndex);
            Log.i(ACTIVITY_NAME, "Cursor's column count = " +cursor.getColumnCount());
        }


        // Lab 5 : Writting record
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = edt.getText().toString();
                messages.add(data);
                messageAdapter.notifyDataSetChanged();
                //Insert the new message into the database, contentValues object will put the new message

                ContentValues contentValues = new ContentValues();
                contentValues.put(dbManager.KEY_MESSAGE,edt.getText().toString());

                long insertCheck = db.insert(dbManager.TABLE_NAME,null,contentValues);
                Log.i("StartChat", "insert data result: " + insertCheck);
                edt.setText("");

            }
        });


    }// end on create


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }

    private class ChatAdapter extends ArrayAdapter<String>{

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }



        @Override
        public int getCount() {
            return messages.size();
        }



        @Override
        public String getItem(int position) {
            return messages.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position,  View convertView,  ViewGroup parent) {

            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result = null ;


            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            //anh xa
            TextView message = result.findViewById(R.id.message);
            message.setText( getItem(position)  ); // get the string at position
            return result;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==6666 && resultCode == RESULT_OK )
        {

            String pos= data.getStringExtra("message");
            messages.remove(pos);
            messageAdapter.notifyDataSetChanged();
            Toast.makeText(this,"this is the Sample",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this,"ERRRORRRR "+requestCode ,Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode,resultCode,data);
    }








}
