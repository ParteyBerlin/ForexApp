package com.example.forexapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.example.forexapp.MainActivity.curr_str;

public class JsonTask extends AsyncTask<Void, Void, JSONObject>
{
   @Override
   protected JSONObject doInBackground(Void... params)
   {
       URLConnection urlConn = null;
       BufferedReader bufferedReader = null;
       try
       {
           URL url = new URL(curr_str);  // ZIEL-URL
           urlConn = url.openConnection();
           bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
           StringBuffer stringBuffer = new StringBuffer();
           String line;
           while ((line = bufferedReader.readLine()) != null)
           {
               stringBuffer.append(line);
           }
           return new JSONObject(stringBuffer.toString());
       }
       catch(Exception ex)
       {
           Log.e("App", "yourDataTask", ex);
           return null;
       }
       finally
       {
           if(bufferedReader != null)
           {
               try {
                   bufferedReader.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
   }

   @Override
   protected void onPostExecute(JSONObject response)
   {
       if(response != null)
       {
           try {
               Log.e("App", "Success: " + response.getString("Realtime Currency Exchange Rate") );

           } catch (JSONException ex) {
               Log.e("App", "Failure", ex);
           }
       }
   }
}