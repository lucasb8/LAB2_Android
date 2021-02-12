package com.example.flickrapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AsyncFlickrJSONDataForList extends AsyncTask<String, Void, JSONObject> {

    // Creation of the parameters
    private MyAdapter adapter;

    // Constructor
    public AsyncFlickrJSONDataForList(MyAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject result = null;

        try {
            // Retrieve the URL
            URL url = new URL(strings[0]);
            // Create the connection
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String s = readStream(in);
                // Print the string of the InputStream
                Log.i("JFL", s);
                // Creation of the JSONObject
                result = new JSONObject(s);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the JSONObject
        return result;
    }

    protected void onPostExecute (JSONObject result){
        // Retrieve the image thanks to the path in the JSON document
        String urlImage = null;
        try {
            JSONArray array = result.getJSONArray("items");

            for(int i = 0; i < array.length(); i++) {
                // retrieve the image urls
                String imageUrl = array.getJSONObject(i).getJSONObject("media").getString("m");

                // add the url to the adapter
                adapter.add(imageUrl);
            }
            // Notify the modification on the adapter
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(in),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        in.close();
        return sb.toString();
    }
}
