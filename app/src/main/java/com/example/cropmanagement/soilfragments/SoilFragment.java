package com.example.cropmanagement.soilfragments;


import android.app.ActionBar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cropmanagement.PagerAdapter;
import com.example.cropmanagement.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SoilFragment extends Fragment {

    private TabLayout tabLayout;
    private TabItem redsoil,blacksoil,lateritesoil,coastalsoil,checksoil;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private ActionBar soilaction;
    public SoilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_soil, container, false);

        tabLayout=(TabLayout)view.findViewById(R.id.tablayout);
        redsoil=(TabItem)view.findViewById(R.id.redsoil);
        blacksoil=(TabItem)view.findViewById(R.id.balcksoil);
        lateritesoil=(TabItem)view.findViewById(R.id.lateritesoil);
        coastalsoil=(TabItem)view.findViewById(R.id.coastalsoil);
        checksoil=(TabItem)view.findViewById(R.id.checksoil);
        //tab2=(TabItem)findViewById(R.id.tabItem2);
        //tab3=(TabItem)findViewById(R.id.tabItem3);

        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        adapter=new PagerAdapter(getFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));




        return view;
    }
    public void onResume() {
        super.onResume();
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        androidx.appcompat.app.ActionBar actionBar=appCompatActivity.getSupportActionBar();
        actionBar.setTitle(R.string.soilqualitytitle);
    }

}
