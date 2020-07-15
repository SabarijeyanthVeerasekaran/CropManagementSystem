package com.example.cropmanagement.soilfragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cropmanagement.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RedSoil extends Fragment {


    public RedSoil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_red_soil, container, false);
    }

}
