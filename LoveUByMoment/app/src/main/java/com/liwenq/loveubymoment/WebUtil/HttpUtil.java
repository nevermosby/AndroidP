package com.liwenq.loveubymoment.WebUtil;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by liwenq on 9/1/2014.
 */
public class HttpUtil {
    private static HttpClient httpClient = null;
    public static String Get(String url){

        if(httpClient==null){
            httpClient = new DefaultHttpClient();
        }

        String ret =null;

        // String url = "https://hellonodemongo-davidlovezoe.rhcloud.com/momentnote";
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(entity.getContent()));
                StringBuffer result = new StringBuffer();
                String line ="";
                while((line = reader.readLine()) != null){
                    result.append(line);
                }

                ret =  result.toString();
                Log.d("HTTP", "GET result: " + ret +", via url: " + url);
                //JsonObject o = new JsonObject(result.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }

        return ret;
    }
}
