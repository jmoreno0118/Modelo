package com.ejemplo.myapplication3.app;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jesus Moreno on 29/04/2014.
 */
public class InternetData {
    private JSONArray datos;

    InternetData(){}

    InternetData(String url){
        int responseCode = -1;
        String resultado = "";
        try {
            URL apiURL = new URL((String)url);
            HttpURLConnection httpConnection = (HttpURLConnection) apiURL.openConnection();
            httpConnection.connect();

            responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = httpConnection.getInputStream();
                BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sBuilder = new StringBuilder();

                String line = null;
                while ((line = bReader.readLine()) != null) {
                    sBuilder.append(line + "\n");
                }
                inputStream.close();
                resultado = sBuilder.toString();

                datos = new JSONArray(resultado);

            }
        }catch(Exception e){}
    }

    public JSONArray getDatos(){
        return datos;
    }
}
