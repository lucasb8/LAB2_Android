package com.example.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    // Create the preference in attribute
    private String preference = "Trees";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve the ImageView
        ImageView image = findViewById(R.id.image);
        // Retrieve the button get image
        Button getImage = findViewById(R.id.getImage);
        // Action when the user press the button
        getImage.setOnClickListener(new GetImageOnClickListener(this, image));
    }

    // Open the new list activity on click
    public void onGoList(View v) {
        // Launch the new activity
        Intent list = new Intent(getApplicationContext(), ListActivity.class);
        list.putExtra("preference", preference);
        startActivity(list);
    }

    // Manage the preference of the user
    public void onPreferenceButton(View v) {
        Intent preferenceIntent = new Intent(getApplicationContext(), PreferenceActivity.class);
        preferenceIntent.putExtra("preference", preference);
        startActivityForResult(preferenceIntent, 1);
    }

    // Modify the preference of the user
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            // Check if the result is right
            if(resultCode == RESULT_OK) {
                // Modify the preference with the new value
                preference = data.getStringExtra("preference");
                try {
                    // Encode the new preference
                    preference = URLEncoder.encode(preference, StandardCharsets.UTF_8.toString());
                } catch (UnsupportedEncodingException e) {
                    // Retrieve the initial value if error
                    preference = "Trees";
                    e.printStackTrace();
                }
            }
        }
    }

    // Retrieve the preference of the main activity
    public String getPreference() {
        return preference;
    }
}
