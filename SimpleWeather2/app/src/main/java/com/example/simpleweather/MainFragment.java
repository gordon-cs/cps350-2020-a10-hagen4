package com.example.simpleweather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

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

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Calling super method
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Binds the XML button object with java object using findViewById function
        reqButton = (Button) view.findViewById(R.id.goButton);

        // Setting on click listener for the button
        reqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Calls the getWeather method when button is clicked
                getWeather();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    private void getWeather() {

        //RequestQueue is initialized
        mRequestQueue = Volley.newRequestQueue(getActivity());

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

                    // Getting the string from the JSON response
                    location = response.getString( "timezone");

                    // Getting string from the JSON object
                    temperature = response.getJSONObject("currently").getString("temperature");

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, ViewWeatherFragment.newInstance(location, temperature, weatherSum), "second")
                            .addToBackStack(null)
                            .commit();

                } catch (JSONException e) {
                    // Catching the exception
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v(TAG, error.toString());
                    }
                }
        );
        mRequestQueue.add(jsonArrRequest);
    }
}


