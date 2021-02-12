package com.example.flickrapp;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {

    // Implementation
    private Vector<String> vector;

    // Constructor
    public MyAdapter() {
        this.vector = new Vector<>();
    }


    void add(String url){
        // Store the URL
        vector.add(url);

        // log the URL
        Log.i("JFL", "Adding to adapter url: " + url);
    }

    @Override
    public int getCount() {
        return vector.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflate the layout
        /*
        convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.textviewlayout, parent, false);

        // Retrieve the urlText
        TextView text = convertView.findViewById(R.id.urlText);
        // Add url into the TextView
        String stringURL = vector.get(position);
        text.setText(vector.get(position));

         */


        // Get a RequestQueue
        RequestQueue queue = MySingleton.getInstance(parent.getContext()).getRequestQueue();

        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bitmaplayout, parent, false);

        // Retrieve the url of the image
        String stringURL = vector.get(position);
        // retrieve the image view container
        ImageView image = convertView.findViewById(R.id.imageUrl);


        // create a response listener
        Response.Listener<Bitmap> responseListener = bmp -> {
            image.setImageBitmap(bmp);
        };

        // Create the image request
        ImageRequest request = new ImageRequest(stringURL, responseListener,
                300, 300, ImageView.ScaleType.CENTER,
                Bitmap.Config.RGB_565, null);

        // Add the request to the queue
        queue.add(request);

        return convertView;
    }
}
