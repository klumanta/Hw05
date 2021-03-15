package com.example.kenanlumantas_simple.utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkUtils {

    public static String getResponseFromUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput) return scanner.next();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }
        return null;
    }

    public static URL buildUrl(String urlString){
        URL url = null;
        try{
            url = new URL(urlString);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return url;

    }

    public static ArrayList<String> parseMeteorsJson(String responseText){
        ArrayList<String> meteors = new ArrayList<String>();

        try {
            Log.d("debugging", "ResponseText from url: " + responseText);
            JSONArray jsonArr = new JSONArray(responseText);
            Log.d("debugging", "JSONArray: " + jsonArr);
            for(int i =0; i< jsonArr.length(); i++) {
                JSONObject jsonMeteor = (JSONObject) jsonArr.get(i);
                Log.d("debugging", "JSONObject: " + jsonMeteor);
                String meteorName = (String) jsonMeteor.get("name");
                Log.d("debugging", "Meteor name: " + meteorName);
                meteors.add(meteorName);
            }
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

        return meteors;
    }

}