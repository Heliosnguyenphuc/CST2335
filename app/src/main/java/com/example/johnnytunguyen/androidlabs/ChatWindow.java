package com.example.johnnytunguyen.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {

    ListView chatV;
    Button btn;
    EditText edt;
    ArrayList<String> messages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        //and xa
        chatV = findViewById(R.id.chatView);
        btn = findViewById(R.id.sendButton);
        edt= findViewById(R.id.chatBoxes);
        messages = new ArrayList<>();



        // logic there



        //in this case, “this” is the ChatWindow, which is-A Context object
         final ChatAdapter messageAdapter = new ChatAdapter( this );
        chatV.setAdapter (messageAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = edt.getText().toString();
                messages.add(data);
                messageAdapter.notifyDataSetChanged();
                edt.setText("");

            }
        });


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
            return super.getItemId(position);
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
            message.setText(   getItem(position)  ); // get the string at position


            return result;

        }
    }
}
