package com.cdac.project.helpinghands;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cdac.project.helpinghands.Fragments.MyAccountFragment;
import com.cdac.project.helpinghands.classes.ConfirmConsumeTask;
import com.cdac.project.helpinghands.classes.Donations;

import java.util.ArrayList;

public class
AllDonationsDetailsActivity extends AppCompatActivity {
    EditText textViewDId,textViewFoodName,textViewFoodQuant,
            textViewAvailDate,textViewExpiryDate,textViewUserID,
            textViewAddress,textViewAvailTime,textViewExpiryTime,textViewStatus;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_donation_details);
      //  textViewDId=(EditText)findViewById(R.id.editTextDonate);
        textViewFoodName=(EditText)findViewById(R.id.editTextFoodDesc);
        textViewFoodQuant=(EditText)findViewById(R.id.editTextFoodValue);
        textViewAvailDate=(EditText)findViewById(R.id.editTextAvailDate);
        textViewExpiryDate=(EditText)findViewById(R.id.editTextExpiryDate);
      // textViewUserID=(EditText)findViewById(R.id.editTextUserID);
        textViewAddress=(EditText)findViewById(R.id.editTextAddress);
        textViewAvailTime=(EditText)findViewById(R.id.editTextAvailableTime);
        textViewExpiryTime=(EditText)findViewById(R.id.editTextExpiryTime);
       // textViewStatus=(EditText)findViewById(R.id.editTextStatus);
        confirm=(Button)findViewById(R.id.buttonDonate);
        confirm.setEnabled(true);
        confirm.setText("Confirm");


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String donateId=textViewDId.getText().toString();
                String url="http://192.168.76.174:8080/hhHelpingHands/HHSetStatus"+"?donateid="+donateId;

                new ConfirmConsumeTask(AllDonationsDetailsActivity.this,url).execute();
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(AllDonationsDetailsActivity.this)
                                .setSmallIcon(R.drawable.ic_menu_gallery)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(AllDonationsDetailsActivity.this, MyAccountFragment.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(AllDonationsDetailsActivity.this);
// Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(AllDonationsDetailsActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
                int mId=0;
                mNotificationManager.notify(mId, mBuilder.build());


            }
        });

        Intent in=getIntent();
        Donations donationObject=(Donations)in.getSerializableExtra("donation");
        Log.e("DonationObj001",donationObject.toString());
//        textViewDId.setText(donationObject.getDonateId()+"");
        textViewFoodName.setText(donationObject.getFoodDesc());
        textViewFoodQuant.setText(donationObject.getFoodValue());
        textViewAvailDate.setText(donationObject.getAvailDate());
        textViewExpiryDate.setText(donationObject.getExpiryDate());
//        textViewUserID.setText(donationObject.getUserId());
        textViewAddress.setText(donationObject.getAddress());
        textViewAvailTime.setText(donationObject.getAvailTime());
        textViewExpiryTime.setText(donationObject.getExpiryTime());
//        textViewStatus.setText(donationObject.getStatus());
    }
}
