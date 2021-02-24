package com.example.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PreferenceActivity extends AppCompatActivity {

    private EditText preferenceInput = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        // retrieve the preference information
        Bundle extras = getIntent().getExtras();
        String preference = extras.getString("preference");

        // retrieve and set the input text
        preferenceInput = findViewById(R.id.inputText);
        preferenceInput.setText(preference);
    }

    // Action when the button is activate
    public void onUpdate(View v) {
        // Print the update in the main activity
        Intent intent = new Intent();
        intent.putExtra("preference", "" + preferenceInput.getText());
        setResult(RESULT_OK, intent);
        finish();
    }
}
