package com.example.simpleweather;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Declaring private variables
    private Button reqButton;
    private TextView textData;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://api.darksky.net/forecast/7711c2819f294564cb912e166a5bb983/42.589611,-70.819806";
    private String TAG = "weather";
    private TextView textData2;
    private TextView textData3;
    private String location;
    private String weatherSum;
    private String temperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Calling super method
        super.onCreate(savedInstanceState);
        // Setting the layout XML
        setContentView(R.layout.activity_main);

        // Binds the XML imageView object with java object using findViewById function
        ImageView imageView = (ImageView) findViewById(R.id.image);

        // Binds the XML textView object with java object using findViewById function
        textData = (TextView) findViewById(R.id.getSummary);

        // Binds the XML textView object with java object using findViewById function
        textData2 = (TextView) findViewById(R.id.sendRequest);

        // Binds the XML textView object with java object using findViewById function
        textData3 = (TextView) findViewById(R.id.getTemp);

        // Binds the XML button object with java object using findViewById function
        reqButton = (Button) findViewById(R.id.go);


        // Setting on click listener for the button
        reqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calls the getWeather method when button is clicked
                getWeather();
            }
        });

    }


    private void getWeather() {

        //RequestQueue is initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request is initialized
        JsonObjectRequest jsonArrRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Displays the first 500 characters of the response string.
                Log.i(TAG, response.toString());
                try {
                    // Parsing JSON response and getting summary from currently object
                    weatherSum = response.getJSONObject("currently").getString("summary");

                    // Setting the text on textView using setText method
                    textData.setText(String.valueOf((weatherSum)));

                    // Getting the string from the JSON response
                    location = response.getString( "timezone");

                    // Setting text on the textView using setText method
                    textData2.setText(String.valueOf(location));

                    // Getting string from the JSON object
                    temperature = response.getJSONObject("currently").getString("temperature");

                    // Setting string on textView using setText method
                    textData3.setText(String.valueOf((temperature)));

                } catch (JSONException e) {
                    // Catching the exception
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Listening for any errors during the process of making the HTTP request
                        Log.v(TAG, error.toString());

                    }
                }
        );


        mRequestQueue.add(jsonArrRequest);
    }
}


