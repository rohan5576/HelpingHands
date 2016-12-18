package com.cdac.project.helpinghands;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdac.project.helpinghands.Fragments.HomeFragmentActivity;
import com.cdac.project.helpinghands.classes.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {


    TextView textViewUserName,textViewPassword;
    EditText editTextUserName,editTextPassword;
    Button buttonLogin,buttonNewUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textViewUserName=(TextView)findViewById(R.id.textViewUsername);
        textViewPassword=(TextView)findViewById(R.id.textViewPassword);
        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        buttonLogin=(Button)findViewById(R.id.buttonLogin);
        buttonNewUser=(Button)findViewById(R.id.buttonNewUser);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=editTextUserName.getText().toString();

                String password=editTextPassword.getText().toString();
                if (  ( username.equals("")) && ( !password.equals("")) )
                {
                    String url="http://192.168.76.174:8080/hhHelpingHands/HHLoginServlet"+"?username="+username+"&password="+password;
                    new LoadHomePageTask(url).execute();
                }
                else if ( ( !username.equals("")) )
                {
                    Toast.makeText(getApplicationContext(),
                            "Password empty", Toast.LENGTH_SHORT).show();
                }
                else if ( ( !password.equals("")) )
                {

                    Toast.makeText(getApplicationContext(),
                            "Username field empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Username and Password field are empty", Toast.LENGTH_SHORT).show();
                }




            }
        });




        buttonNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent1);
            }
        });

    }//eof onCreate

    class LoadHomePageTask extends AsyncTask<String,String,String>
    {

        String lUrl;
        public LoadHomePageTask(String url){
            this.lUrl=url;
        }

        @Override
        protected String doInBackground(String...        params) {

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
        }//eof doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null && s.length()>0)
            {
                Toast.makeText(LoginActivity.this,s,Toast.LENGTH_LONG).show();

                try{

                    JSONObject jsonObject=new JSONObject(s);

                     Log.e("sdfsgf",jsonObject.toString());

                     JSONObject jsonData=jsonObject.getJSONObject("data");
                        int userid=jsonData.getInt("huserid");

                        String userName=jsonData.getString("husername");
                       Log.e("mobile1",userName.toString());
                        String name=jsonData.getString("hname");
                        String email=jsonData.getString("hemail");

                        String address=jsonData.getString("haddress");
                        String mobile=jsonData.getString("hmobile");
                        String password=jsonData.getString("hpassword");
                        Log.e("mobile",mobile);
                        User user=new User(userid,userName,name,email,address,mobile,password);

                        Log.e("userData",user.toString());

                        Intent intent1=new Intent(LoginActivity.this,HomeFragmentActivity.class);

                        intent1.putExtra("user",user);
                       Log.e("chheckdata",user.toString());
                        startActivity(intent1);


                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

        }
        //endofpost



    }//endofclass


}
