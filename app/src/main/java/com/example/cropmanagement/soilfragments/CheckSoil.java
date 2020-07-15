package com.example.cropmanagement.soilfragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.cropmanagement.MainFragment;
import com.example.cropmanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckSoil extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FrameLayout soilcontainerfragment;

    public CheckSoil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_check_soil, container, false);
        //soilcontainerfragment=view.findViewById(R.id.soilcontainerFragment);
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.soilcontainerFragment,new RedSoil());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();






        return view;
    }

}
