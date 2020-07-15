package com.example.cropmanagement;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cropmanagement.soilfragments.BlackSoil;
import com.example.cropmanagement.soilfragments.CheckSoil;
import com.example.cropmanagement.soilfragments.CoastalSoil;
import com.example.cropmanagement.soilfragments.LateriteSoil;
import com.example.cropmanagement.soilfragments.RedSoil;

public class PagerAdapter extends FragmentPagerAdapter {
    int NumberofTabs;
    public PagerAdapter(@NonNull FragmentManager fm,int NumberofTabs) {
        super(fm);
        this.NumberofTabs=NumberofTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                RedSoil redSoil=new RedSoil();
                return redSoil;
            case 1:
                BlackSoil blackSoil=new BlackSoil();
                return blackSoil;
            case 2:
                LateriteSoil lateriteSoil=new LateriteSoil();
                return lateriteSoil;
            case 3:CoastalSoil coastalSoil=new CoastalSoil();
                return coastalSoil;
            case 4:CheckSoil checkSoil=new CheckSoil();
                return  checkSoil;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return NumberofTabs;
    }
}
