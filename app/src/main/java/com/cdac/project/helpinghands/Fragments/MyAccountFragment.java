package com.cdac.project.helpinghands.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cdac.project.helpinghands.R;
import com.cdac.project.helpinghands.classes.LoadUpdateMyProfileTask;
import com.cdac.project.helpinghands.classes.User;

/**
 * Created by rajesh on 17/07/16.
 */
public class MyAccountFragment extends Fragment {

    TextView     textViewUserName, textViewName,textViewEmail,textViewAddress,textViewMobileNo;
    EditText    editTextMobileNo,editTextAddress,editTextUserEmail,editTextName,editTextUserName;
    int userId=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView=inflater.inflate(R.layout.myaccount_fragment_layout,container,false);

        editTextUserName=(EditText)fragmentView.findViewById(R.id.editTextUserName);
        editTextName=(EditText)fragmentView.findViewById(R.id.editTextName);
        editTextUserEmail=(EditText)fragmentView.findViewById(R.id.editTextUserEmail);
        editTextMobileNo=(EditText)fragmentView.findViewById(R.id.editTextMobileNo);
        editTextAddress=(EditText)fragmentView.findViewById(R.id.editTextAddress);
        editTextAddress.setEnabled(false);
        editTextAddress.setFocusable(true);
        editTextUserName.setEnabled(false);
        editTextName.setEnabled(false);
        editTextUserEmail.setEnabled(false);
        editTextMobileNo.setEnabled(false);

        setHasOptionsMenu(true);
        Intent intent=getActivity().getIntent();

         User userObj=(User) intent.getSerializableExtra("user");
        userId=userObj.getUserID();
        editTextUserName.setText(userObj.getUserName());
        editTextName.setText(userObj.getName());
        editTextUserEmail.setText(userObj.getEmail());
        editTextMobileNo.setText(userObj.getMobile());
        editTextAddress.setText(userObj.getAddress());


        System.out.println(userObj);
        Log.e("user1",userObj.toString());
     //   setHasOptionsMenu(true);

        return fragmentView;
    }





//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.mainmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("itemID",item.getItemId()+"");
        switch (item.getItemId())
        {
            case R.id.optionEdit:
                editTextUserName.setEnabled(true);
                editTextName.setEnabled(true);
                editTextUserEmail.setEnabled(true);
                editTextMobileNo.setEnabled(true);
                editTextAddress.setEnabled(true);
                editTextAddress.setInputType(InputType.TYPE_CLASS_TEXT);


                return true;
            case R.id.optionSave:
                String username=editTextUserName.getText().toString();
                String name=editTextName.getText().toString();
                String email=editTextUserEmail.getText().toString();
                String mobile=editTextMobileNo.getText().toString();
                String address=editTextAddress.getText().toString();
                String url="http://192.168.76.174:8080/hhHelpingHands/HHUpdateDetailsServlet"+"?username="+username+"&name="+name+
                        "&email="+email+"&address="+address+"&mobile="+mobile+"&userid="+userId;
                new LoadUpdateMyProfileTask(getActivity(),url).execute();
                return true;

            case R.id.optionLogOut:
                getActivity().finish();
              return  true;
        }
        return false;
    }
}
