package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAuthenticate(View v) {
        // Retrieve the login enter by the user
        final EditText login = findViewById(R.id.login);
        // Retrieve the password enter by the user
        final EditText password = findViewById(R.id.password);

        // Creation of the thread
        new Thread() {
            // Override the run method
            @Override
            public void run() {
                URL url;
                try {
                    // Creation of the url to access to the website
                    url = new URL("https://httpbin.org/basic-auth/bob/sympa");
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    // Create the authentication with encoding login and password
                    String basicAuth = "Basic " + Base64.encodeToString("bob:sympa".getBytes(), Base64.NO_WRAP);
                    urlConnection.setRequestProperty ("Authorization", basicAuth);
                    // Open the connection
                    try {
                        // Implementation of the buffer to read element
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        // Stock the data in a string
                        String s = readStream(in);
                        // Print the data
                        Log.i("JFL", s);
                    } finally {
                        // Close the connection at the end of the usage
                        urlConnection.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    // Provide access to the typewritten data on an input stream
    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        // Browser of the input bufferedReader
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        // Return the data in string type
        return sb.toString();
    }
}
