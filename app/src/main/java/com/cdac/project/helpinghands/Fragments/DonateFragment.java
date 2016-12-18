package com.cdac.project.helpinghands.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cdac.project.helpinghands.Adapters.DonationListAdapter;
import com.cdac.project.helpinghands.MyDonationDetailsActivity;
import com.cdac.project.helpinghands.R;
import com.cdac.project.helpinghands.classes.Donations;
import com.cdac.project.helpinghands.classes.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rajesh on 17/07/16.
 */
public class DonateFragment extends Fragment {
    ListView listViewDonate;
    ArrayList<Donations> listMyDonations;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.donate_fragment_layout, container, false);

        listViewDonate = (ListView) fragmentView.findViewById(R.id.listViewDonate);
        Intent intent = getActivity().getIntent();
        User userObj = (User) intent.getSerializableExtra("user");
        int userId = userObj.getUserID();
        Log.e("userKiId",userId+"");

        String url = "http://192.168.76.174:8080/hhHelpingHands/HHShowDonationsServlet" + "?userid=" + userId;
        new LoadMyDonationTask(url).execute();
        init();
        return fragmentView;
    }

    private void init() {
        listViewDonate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //    Donations donationobj=(Donations)listViewConsume.getItemAtPosition(i);
                Donations donationobj = (Donations) listMyDonations.get(i);
                Log.e("objectAtPosition1", donationobj.toString());
                Intent intent = new Intent(getActivity(), MyDonationDetailsActivity.class);
                intent.putExtra("donation", donationobj);
                startActivity(intent);


            }
        });
    }
    class LoadMyDonationTask extends AsyncTask<String, String, String> {
        String lUrl;

        public LoadMyDonationTask(String url) {
            this.lUrl = url;
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = "";


            try {
                URL url = new URL(lUrl);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setRequestMethod("GET");
                httpCon.connect();
                if (httpCon.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) break;
                        else result = result + line;

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
            if (s != null && s.length() > 0) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    listMyDonations = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        int donateId = object.getInt("donateId");
                        String foodDesc = object.getString("foodDesc");
                        System.out.println("foooood" + foodDesc);
                        String foodValue = object.getString("foodValue");
                        String availDate = object.getString("availDate");
                        String expiryDate = object.getString("expiryDate");
                        String userId = object.getString("userId");
                        String address = object.getString("address");
                        String availTime = object.getString("availTime");
                        String expiryTime = object.getString("expiryTime");
                        String status = object.getString("status");

                        final Donations donationobj = new Donations(donateId, foodDesc, foodValue, availDate, expiryDate, userId, address, availTime, expiryTime, status);
                        Log.e("DataMilaKya", donationobj.toString());
                        listMyDonations.add(donationobj);


                    }

                    DonationListAdapter donationListAdapter = new DonationListAdapter(getActivity(), listMyDonations);
                    listViewDonate.setAdapter(donationListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater inflater1=new MenuInflater(getActivity());
        inflater.inflate(R.menu.mainmenu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.optionLogOut:
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}