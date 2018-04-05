package com.example.johnnytunguyen.androidlabs;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

import static android.app.Activity.RESULT_OK;

/**
 * Created by johnnytunguyen on 2018-04-01.
 */

public class MessageFragment extends Fragment {

    TextView tv1 ;
    TextView tv2 ;
    Button btnFragment;

    ListView listViewChat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_layout,null);
         tv1 = view.findViewById(R.id.textView1);
         tv2 = view.findViewById( R.id.textView2);
         btnFragment = view.findViewById(R.id.BtndeleteFragment);

         listViewChat = getActivity().findViewById(R.id.chatView);


        final Bundle bundle = getArguments();
        final int currentId = bundle.getInt("id");
        final boolean isTablet = bundle.getBoolean("FLAG");
        final String item = bundle.getString("message");

        if (bundle != null )

        {
            tv1.setText(bundle.getString("message"));
            tv2.setText(String.valueOf(bundle.getInt("id")));
        }
        //delete button
        btnFragment.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                // to delete the message
                if (!isTablet) {
                    Toast.makeText(getActivity(),"Have fun with on phone mode",Toast.LENGTH_LONG).show();
                    Intent temp = new Intent();
                    temp.putExtra("message", tv1.getText().toString());
                    getActivity().setResult(RESULT_OK, temp);
                    getActivity().finish();
                }else{//if is on the


                FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                MessageFragment messageFragment = (MessageFragment) getFragmentManager().findFragmentByTag("FragmentOnTablet");
                EditText edtMain = getActivity().findViewById(R.id.chatBoxes);
                ChatWindow.messages.remove(item);
                fragmentTransaction.remove(messageFragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(),"Have fun with tablet mode",Toast.LENGTH_LONG).show();}
            }
        });
        return view;
    }



}
