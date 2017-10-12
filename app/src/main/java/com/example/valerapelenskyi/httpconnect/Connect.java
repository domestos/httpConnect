package com.example.valerapelenskyi.httpconnect;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SyncFailedException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by valera.pelenskyi on 12.10.17.
 */

public class Connect extends AsyncTask<String, Void,String>{

    private Context context;
    private String url_select_php;


    public Connect(String url_select_php, Context context) {
        this.url_select_php = url_select_php;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        URL url = null;


        try {
            url = new URL(url_select_php);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "ERROR "+e.getMessage();
        }

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(250);
            httpURLConnection.setReadTimeout(252);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            StringBuilder sb = new StringBuilder();

            if(HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));

                String line;
                while ((line = in.readLine()) != null){
                    sb.append(line);
                    sb.append("\n");
                }
               return  sb.toString();
            } else {
                return "fail "+httpURLConnection.getResponseCode() + " " + httpURLConnection.getResponseMessage();

            }


        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR 2r"+e.getMessage();
        }




    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
