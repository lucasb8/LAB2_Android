package com.example.flickrapp;

import android.view.View;
import android.widget.ImageView;

public class GetImageOnClickListener implements View.OnClickListener {
    // Keep the ImageView as parameter of the class
    private ImageView iv;

    // Constructor of the class
    public GetImageOnClickListener(ImageView iv) {
        this.iv = iv;
    }

    @Override
    public void onClick(View v) {
        // Start a new asynctask thanks to the AsyncBitmapDownloader class
        new AsyncFlickrJSONData(new AsyncBitmapDownloader(iv)).execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json&nojsoncallback=?");
    }
}