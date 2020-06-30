package com.robin.testdemo.fragmentlazyload;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class VpAdapter extends FragmentPagerAdapter {
    public VpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FragmentA();
            case 1: return new FragmentB();
            case 2: return new FragmentC();
            case 3: return new FragmentD();
            default:break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
