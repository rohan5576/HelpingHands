package com.cdac.project.helpinghands;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextUsername,editTextName,editTextEmail,editTextAddress,
            editTextMobileNo,editTextPassword;
    Button buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextUsername=(EditText)findViewById(R.id.editUsername);
        editTextName=(EditText)findViewById(R.id.editName);
        editTextAddress=(EditText)findViewById(R.id.editAddress);
        editTextMobileNo=(EditText)findViewById(R.id.editMobileNo);
        editTextPassword=(EditText)findViewById(R.id.editPassword);
        editTextEmail=(EditText)findViewById(R.id.editEmail);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=editTextUsername.getText().toString();
                String name=editTextName.getText().toString();
                String address=editTextAddress.getText().toString();
                String mobile=editTextMobileNo.getText().toString();
                String password=editTextPassword.getText().toString();
                String email=editTextEmail.getText().toString();

                if (username.equals("") && name.equals("") && address.equals("") && mobile.equals("")
                        && password.equals("") && email.equals(""))
                {
                    Toast.makeText(RegistrationActivity.this,"Enter complete credentials",Toast.LENGTH_LONG).show();
                }


                else
                {
                    String url = "http://192.168.76.174:8080/hhHelpingHands/HHUserRegistration"+
                            "?username="+username+"&name="+name+"&email="+email+"&address="+address+"&mobile="+mobile+"&password="+password;
                    new LoadRegisterTask(url).execute();

                }
            }
        });
    }//eof onCreate


    class LoadRegisterTask extends AsyncTask<String,String,String>
    {
        String lUrl;
        public LoadRegisterTask(String url) {this.lUrl=url;}


        @Override
        protected String doInBackground(String... strings) {
            String result="";


            try {
                URL url=new URL(lUrl);
                HttpURLConnection httpCon=(HttpURLConnection)url.openConnection();
                httpCon.setRequestMethod("GET");
                httpCon.connect();
                if (httpCon.getResponseCode()==200)
                {
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                    while(true)
                    {
                        String line=bufferedReader.readLine();
                        if (line==null)break;
                        else result=result+line;

                    }
                    bufferedReader.close();
                    httpCon.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }//eof doIn

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s!=null && s.length()>0)
            {
                Toast.makeText(RegistrationActivity.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
            }
        }
    }//eof class task

}
