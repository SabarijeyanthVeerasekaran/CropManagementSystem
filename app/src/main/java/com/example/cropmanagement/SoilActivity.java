package com.example.cropmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SoilActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem redsoil,blacksoil,lateritesoil,coastalsoil,checksoil;
    ViewPager viewPager;
    PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        redsoil=(TabItem)findViewById(R.id.redsoil);
        blacksoil=(TabItem)findViewById(R.id.balcksoil);
        lateritesoil=(TabItem)findViewById(R.id.lateritesoil);
        coastalsoil=(TabItem)findViewById(R.id.coastalsoil);
        checksoil=(TabItem)findViewById(R.id.checksoil);
        //tab2=(TabItem)findViewById(R.id.tabItem2);
        //tab3=(TabItem)findViewById(R.id.tabItem3);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        adapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
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





    }
}
