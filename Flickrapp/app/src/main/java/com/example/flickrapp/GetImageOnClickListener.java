package com.example.flickrapp;

import android.view.View;
import android.widget.ImageView;

public class GetImageOnClickListener implements View.OnClickListener {

    // Stock the main activity
    private MainActivity activity;
    // Keep the ImageView as parameter of the class
    private ImageView iv;

    // Constructor of the class
    public GetImageOnClickListener(MainActivity activity, ImageView iv) {
        this.activity = activity;
        this.iv = iv;
    }

    @Override
    public void onClick(View v) {
        // Start a new asynctask thanks to the AsyncBitmapDownloader class
        new AsyncFlickrJSONData(new AsyncBitmapDownloader(iv)).execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=" + activity.getPreference() + "&format=json&nojsoncallback=?");
    }
}