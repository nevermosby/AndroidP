package com.liwenq.loveubymoment.myLib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liwenq on 12/31/2014.
 */
public class UtilHelper {

    public static boolean IsInternetAvailable(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork!=null && activeNetwork.isConnected()) {
            // new CheckNetworkTask().execute("http://www.baidu.com");
            return true;
        }
        else{
            return false;
        }
    }

    private static class CheckNetworkTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestProperty("User-Agent", "test");
                httpURLConnection.setRequestProperty("Connection", "close");
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    return "1";
                } else {
                    throw new Exception("error code is not 200" + httpURLConnection.getResponseCode());
                }
            } catch (Exception e) {
                Log.d("warning", "Error checking internet connection", e);
                return "0";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("Test", s);

        }
    }
}
