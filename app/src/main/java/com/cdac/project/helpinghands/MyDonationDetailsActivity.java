package com.cdac.project.helpinghands;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowId;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdac.project.helpinghands.classes.Donations;
import com.cdac.project.helpinghands.classes.LoadUpdateMyDonationDetails;

public class MyDonationDetailsActivity extends AppCompatActivity {

    EditText editTextFoodDesc,editTextFoodValue,editTextExpDate,editTextAvlDate,
    editTextId,editTextAdr,editTextAvlTime,editTextExpTime,editTextStatus,editTextDonateId;
    Button donateItem,buttonConfirm;
    int donateId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_donation_details);




        Intent in=getIntent();
        Donations donationObject=(Donations)in.getSerializableExtra("donation");
        Log.e("mydonation",donationObject.toString());
        donateId=donationObject.getDonateId();

        //editTextDonateId=(EditText)findViewById(R.id.editTextDonate);
       // editTextDonateId.setText(donationObject.getDonateId()+"");
      // editTextDonateId.setEnabled(false);
        editTextFoodDesc=(EditText)findViewById(R.id.editTextFoodDesc);
        editTextFoodDesc.setText(donationObject.getFoodDesc());
        editTextFoodDesc.setEnabled(false);

        editTextFoodValue=(EditText)findViewById(R.id.editTextFoodValue);
        editTextFoodValue.setText(donationObject.getFoodValue());
        editTextFoodValue.setEnabled(false);

        editTextAvlDate=(EditText)findViewById(R.id.editTextAvailDate);
        editTextAvlDate.setText(donationObject.getAvailDate());
        editTextAvlDate.setEnabled(false);

        editTextExpDate=(EditText)findViewById(R.id.editTextExpiryDate);
        editTextExpDate.setText(donationObject.getExpiryDate());
       editTextExpDate.setEnabled(false);

//        editTextId=(EditText)findViewById(R.id.editTextUserID);
//        editTextId.setText(donationObject.getUserId());
//        editTextId.setEnabled(false);

        editTextAdr=(EditText)findViewById(R.id.editTextAddress);
        editTextAdr.setText(donationObject.getAddress());
       editTextAdr.setEnabled(false);

        editTextAvlTime=(EditText)findViewById(R.id.editTextAvailableTime);
        editTextAvlTime.setText(donationObject.getAvailTime());
      editTextAvlTime.setEnabled(false);

        editTextExpTime=(EditText)findViewById(R.id.editTextExpiryTime);
        editTextExpTime.setText(donationObject.getExpiryTime());
        editTextExpTime.setEnabled(false);

//        editTextStatus=(EditText)findViewById(R.id.editTextStatus);
//        editTextStatus.setText(donationObject.getStatus());
//        editTextStatus.setEnabled(false);

        buttonConfirm=(Button)findViewById(R.id.buttonDonate);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=new MenuInflater(MyDonationDetailsActivity.this);
        menuInflater.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("itemID",item.getItemId()+"");
        switch (item.getItemId())
        {
            case R.id.optionEdit:

                editTextFoodDesc.setEnabled(true);
                editTextFoodValue.setEnabled(true);
                editTextAdr.setEnabled(true);
                editTextAvlDate.setEnabled(true);
                editTextAvlTime.setEnabled(true);
                editTextExpDate.setEnabled(true);
                editTextExpTime.setEnabled(true);

                return true;
            case R.id.optionSave:
                String foodDesc=editTextFoodDesc.getText().toString();
                String foodValue=editTextFoodValue.getText().toString();
                String address=editTextAdr.getText().toString();
                String availDate=editTextAvlDate.getText().toString();
                String availTime=editTextAvlTime.getText().toString();
                String expTime=editTextExpTime.getText().toString();
                String expDate=editTextExpDate.getText().toString();

                int userId=editTextId.getId();



                Log.e("FoodDesc",foodDesc);
                String url="http://192.168.76.174:8080/hhHelpingHands/HHDonateUpdateServlet"+"?donateid="+donateId+"&fooddesc="+foodDesc+
                        "&foodvalue="+foodValue+"&availdate="+availDate+"&expirydate="+expDate+"&userid="+userId+"&address="+address+
                        "&availtime="+availTime+"&expirytime="+expTime;
               // Toast.makeText(MyDonationDetailsActivity.this,"Donation updated",Toast.LENGTH_LONG).show();
                new LoadUpdateMyDonationDetails(MyDonationDetailsActivity.this,url).execute();
                return true;

            case R.id.optionLogOut:
                finish();

                return  true;



        }
        return false;
    }




    }

