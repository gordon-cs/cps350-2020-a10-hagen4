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
    private TextView textData;
    private TextView textData2;
    private TextView textData3;

    public ViewWeatherFragment() {
        // Required empty public constructor
    }

    public ViewWeatherFragment(String city, String temperature) {
        //Constructors
        this.city = city;
        this.temperature = temperature;
    }

    public static ViewWeatherFragment newInstance(String city, String temperature) {
        ViewWeatherFragment fragment = new ViewWeatherFragment(city, temperature);
        //Bundles can hold all types of values and pass them to new activities
        Bundle bundle = new Bundle();
        bundle.putString("city", city);
        bundle.putString("temperature",temperature);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = getArguments().getString("city");
            temperature = getArguments().getString("temperature");
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
        TextView t = view.findViewById(R.id.getLocation);
        t.setText(city);

        TextView t2 = view.findViewById(R.id.getTemp);
        t2.setText(temperature);

    }
}
