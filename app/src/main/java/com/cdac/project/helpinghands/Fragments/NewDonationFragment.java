package com.cdac.project.helpinghands.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.cdac.project.helpinghands.R;
import com.cdac.project.helpinghands.classes.DatePicker;
import com.cdac.project.helpinghands.classes.LoadDonationTask;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by parag on 23-07-2016.
 */
public class NewDonationFragment extends Fragment {
    EditText editTextFoodDesc, editTextFoodValue, editTextExpDate, editTextAvlDate,
            editTextId, editTextAdr, editTextAvlTime, editTextExpTime, editTextStatus, editTextDonateId;
    Button buttonDonate;

    final Calendar dateAndTime=Calendar.getInstance();
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay,
                              int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.activity_my_donation_details, container, false);
       // editTextDonateId = (EditText) fragmentView.findViewById(R.id.editTextDonate);
        editTextFoodDesc = (EditText) fragmentView.findViewById(R.id.editTextFoodDesc);
        editTextFoodValue = (EditText) fragmentView.findViewById(
                R.id.editTextFoodValue);
        editTextAvlDate = (EditText) fragmentView.findViewById(R.id.editTextAvailDate);
        editTextExpDate = (EditText) fragmentView.findViewById(R.id.editTextExpiryDate);
        //editTextId = (EditText) fragmentView.findViewById(R.id.editTextUserID);
        editTextAdr = (EditText) fragmentView.findViewById(R.id.editTextAddress);
        editTextAvlTime = (EditText) fragmentView.findViewById(R.id.editTextAvailableTime);
        editTextExpTime = (EditText) fragmentView.findViewById(R.id.editTextExpiryTime);
        //editTextStatus = (EditText) fragmentView.findViewById(R.id.editTextStatus);
        //editTextStatus.setEnabled(false);
        buttonDonate=(Button)fragmentView.findViewById(R.id.buttonDonate);
        buttonDonate.setEnabled(true);
        buttonDonate.setText("Donate");

        DateFormat fmtDateAndTime=DateFormat.getDateTimeInstance();


        buttonDonate = (Button) fragmentView.findViewById(R.id.buttonDonate);
        buttonDonate.setEnabled(true);
        editTextAvlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
                        String date = i2 + "/" + i1 + "/" + i;
                        editTextAvlDate.setText(date);

                    }
                }, 2016, 6, 12);
                dialog.show();
            }
        });

        editTextExpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
                        String date = i2 + "/" + i1 + "/" + i;
                        editTextExpDate.setText(date);

                    }
                }, 2016, 6, 12);
                dialog.show();
            }
        });
//        editTextAvlTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new TimePickerDialog(getActivity(),t,dateAndTime.get(Calendar.HOUR_OF_DAY),
//                        dateAndTime.get(Calendar.MINUTE),true).show();
//                editTextAvlTime.setText( dateAndTime+"");
//            }
//        });

//        editTextAvlTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar mcurrentTime = Calendar.getInstance();
//                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = mcurrentTime.get(Calendar.MINUTE);
//                TimePickerDialog mTimePicker;
//                mTimePicker = new TimePickerDialog(NewDonationFragment.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        editTextAvlTime.setText( selectedHour + ":" + selectedMinute);
//                    }
//                }, hour, minute);
//                mTimePicker.setTitle("Select Time");
//                mTimePicker.show();
//            }
//        });

        editTextExpTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(getActivity(),t,dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE),true).show();
              //  editTextExpDate.setText(dateAndTime);

            }
        });




        buttonDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fooddesc = editTextFoodDesc.getText().toString();
                String foodvalue = editTextFoodValue.getText().toString();
                String availdate = editTextAvlDate.getText().toString();
                String expirydate = editTextExpDate.getText().toString();
                String userid = editTextId.getText().toString();
                String address = editTextAdr.getText().toString();
                String availtime = editTextAvlTime.getText().toString();
                String expirytime = editTextExpTime.getText().toString();
                String url = "http://192.168.76.174:8080/hhHelpingHands/HHDonateServlet" +
                        "?fooddesc=" + fooddesc + "&foodvalue=" + foodvalue + "&availdate=" + availdate + "&expirydate=" + expirydate + "&userid=" +
                        userid + "&address=" + address + "&availltime=" + availtime + "&expirytime" + expirytime;

                new LoadDonationTask(getActivity(), url).execute();

            }
        });


        return fragmentView;
    }

//    private TimePickerDialog.OnTimeSetListener mTimeListener =
//            new TimePickerDialog.OnTimeSetListener() {
//                public void onTimeSet(TimePicker view, int hour, int minute) {
//                    updateTime(hour, minute);
//                }
//            };
//
//    public void updateTime(int hour, int minute) {
//        String timeString = new StringBuilder().append(String.valueOf(hour))
//                .append(":").append(String.valueOf(minute)).toString();
//        editTextAvlTime.setText(timeString);
//
//
//    }
}
