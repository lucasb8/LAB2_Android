package com.example.flickrapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.security.AccessController;
import java.util.Vector;

public class MyAdapter extends BaseAdapter {

    // Implementation
    private Vector<String> vector;

    // Constructor
    public MyAdapter() {
        this.vector = new Vector<>();
    }


    public void add(String url){
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

        // Get a RequestQueue
        RequestQueue queue = MySingleton.getInstance(parent.getContext()).getRequestQueue();

        // Inflate the layout
        convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.textviewlayout, parent, false);
        // Retrieve the urlText
        TextView text = convertView.findViewById(R.id.urlText);
        // Add url into the TextView
        text.setText(vector.get(position));

        return convertView;
    }
}
