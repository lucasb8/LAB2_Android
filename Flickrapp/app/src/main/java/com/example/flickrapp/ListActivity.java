package com.example.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Retrieve the preference
        Bundle extras = getIntent().getExtras();
        String preference = extras.getString("preference");

        adapter = new MyAdapter();

        // Retrieve the list view and link withe the adapter
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        // Creation of the asyncTask to retrieve images
        new AsyncFlickrJSONDataForList(adapter).execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=" + preference + "&format=json&nojsoncallback=?");
    }
}
