package com.cdac.project.helpinghands.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cdac.project.helpinghands.AllDonationsDetailsActivity;
import com.cdac.project.helpinghands.R;
import com.cdac.project.helpinghands.classes.Donations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.cdac.project.helpinghands.Adapters.DonationListAdapter;

/**
 * Created by rajesh on 17/07/16.
 */
public class ConsumeFragment extends Fragment {
    ListView listViewConsume;

    ArrayList<Donations> listofAllDonations;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.consume_fragment_layout, container, false);

        listViewConsume = (ListView) fragmentView.findViewById(R.id.listViewAllDonations);
        String url = "http://192.168.76.174:8080/hhHelpingHands/HHDonationAvailable";
        new LoadAllDonationsTask(url).execute();
        init();
        return fragmentView;
    }

    private void init() {
        listViewConsume.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                //    Donations donationobj=(Donations)listViewConsume.getItemAtPosition(i);
                Donations donationobj = (Donations) listofAllDonations.get(i);
                Log.e("objectAtPosition1", donationobj.toString());
                Intent intent = new Intent(getActivity(), AllDonationsDetailsActivity.class);
                intent.putExtra("donation", donationobj);
                startActivity(intent);


            }
        });
    }


    class LoadAllDonationsTask extends AsyncTask<String, String, String> {
        String lUrl;

        public LoadAllDonationsTask(String url) {
            this.lUrl = url;
        }

        private ProgressDialog dialog;

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            Log.e("params", lUrl);

            try {
                URL url = new URL(lUrl);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setRequestMethod("GET");
                Log.e("connect ", url + "");
                //httpCon.connect();
                Log.e("url ", url + "" + httpCon.getResponseCode());
                //  if (httpCon.getResponseCode()==200 || true)
                // {
                Log.e("in try after connect", httpCon.getResponseCode() + "");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) break;
                    else result = result + line;

                }
                bufferedReader.close();
                httpCon.disconnect();
                // }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("result", result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s != null && s.length() > 0) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    listofAllDonations = new ArrayList<>();
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


                        //   String donation=donateId+"\n"+foodDesc+"\n"+foodValue;

                        final Donations donationobj = new Donations(donateId, foodDesc, foodValue, availDate, expiryDate, userId, address, availTime, expiryTime, status);
                        Log.e("DataMilaKya", donationobj.toString());
                        listofAllDonations.add(donationobj);


                    }

                    DonationListAdapter donationListAdapter = new DonationListAdapter(getActivity(), listofAllDonations);
                    //adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listofAllDonations);
                    listViewConsume.setAdapter(donationListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }//eof Task class

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
            case R.id.optionEdit:
                String url="http://192.168.76.174:8080/hhHelpingHands/HHDonateServlet";

                break;
            case R.id.optionSave:

            case R.id.optionLogOut:
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}


