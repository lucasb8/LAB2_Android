package com.example.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve the ImageView
        ImageView image = findViewById(R.id.image);
        // Retrieve the button get image
        Button getImage = findViewById(R.id.getImage);
        // Action when the user press the button
        getImage.setOnClickListener(new GetImageOnClickListener(image));
    }
}
