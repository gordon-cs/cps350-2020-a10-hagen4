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

    private String city;
    private String temperature;
    private TextView textData;

    public ViewWeatherFragment() {
        // Required empty public constructor
    }

    public ViewWeatherFragment(String city, String temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    public static ViewWeatherFragment newInstance(String city, String temperature) {
        ViewWeatherFragment fragment = new ViewWeatherFragment(city, temperature);
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
        // Binds the XML button object with java object using findViewById function
        textData = (TextView) view.findViewById(R.id.getTemp);
        textData.setText(city);
    }
}
