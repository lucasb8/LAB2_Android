package com.example.flickrapp;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {

    // Implementation
    private Vector<String> vector;

    // Constructor
    public MyAdapter(Vector<String> vector) {
        this.vector = vector;
    }

    public void dd(String url){
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
        return null;
    }
}
