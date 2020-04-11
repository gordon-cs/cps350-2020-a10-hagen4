package com.example.simpleweather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewWeatherFragment extends Fragment {

    // Declaring private variables
    private String city;
    private String temperature;
    private String summary;

    public ViewWeatherFragment() {
        // Required empty public constructor
    }

    public ViewWeatherFragment(String city, String temperature, String summary) {
        //Constructors
        this.city = city;
        this.temperature = temperature;
        this.summary = summary;
    }

    public static ViewWeatherFragment newInstance(String city, String temperature, String summary) {
        ViewWeatherFragment fragment = new ViewWeatherFragment(city, temperature, summary);
        //Bundles can hold all types of values and pass them to new activities
        Bundle bundle = new Bundle();
        bundle.putString("city", city);
        bundle.putString("temperature",temperature);
        bundle.putString("summary", summary);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = getArguments().getString("city");
            temperature = getArguments().getString("temperature");
            summary = getArguments().getString("summary");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView t = view.findViewById(R.id.getSummary);
        t.setText(summary);

        TextView t2 = view.findViewById(R.id.getTemp);
        t2.setText(temperature);

        TextView t3 = view.findViewById(R.id.getLocation);
        t3.setText(city);

    }
}
