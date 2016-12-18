package com.cdac.project.helpinghands.classes;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cdac.project.helpinghands.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by parag on 23-07-2016.
 */
public class LoadDonationTask extends AsyncTask<String,String,String> {
    private String lUrl;
    private Context mContext;

    public LoadDonationTask(Context context, String url) {
        this.lUrl = url;
        this.mContext=context;
    }
    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        Log.e("params", lUrl);

        try {
            URL url = new URL(lUrl);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            Log.e("connect ", url + "");
            //httpCon.connect();
            // Log.e("url ", url + "" + httpCon.getResponseCode());
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
        if (s!=null && s.length()>0)
        {
            Log.e("IsDonated","trueYes");
            Toast.makeText(mContext,mContext.getResources().getString(R.string.tx_update),Toast.LENGTH_SHORT).show();
        }
    }
}
