package com.example.flickrapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AsyncBitmapDownloader extends AsyncTask<String, Void, Bitmap> {
    @SuppressLint("StaticFieldLeak")
    private ImageView iv;

    AsyncBitmapDownloader(ImageView iv){
        this.iv = iv;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        // Initiate the Bitmap and the URL
        Bitmap bm = null;
        URL url = null;

        // Retrieve the URL thanks to the parameter
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // Creation of the connection
        HttpsURLConnection urlConnection = null;
        try {
            assert url != null;
            urlConnection = (HttpsURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert urlConnection != null;
            // Retrieve and decode the input stream
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());
            bm = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }

    protected void onPostExecute(Bitmap bm){
        iv.setImageBitmap(bm);
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
