package com.robin.testdemo.fragmentlazyload.viewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robin.testdemo.FragmentLifeCallback;
import com.robin.testdemo.R;
import com.robin.testdemo.fragmentlazyload.VpAdapter;

public class FragmentLazyLoadActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_lazy_load);
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentLifeCallback(),true);

        tabLayout=findViewById(R.id.tl_layout);
        viewPager=findViewById(R.id.vp_layout);
        viewPager.setAdapter(new VpAdapter(getSupportFragmentManager()));
//        viewPager.setOffscreenPageLimit(1);

        tabLayout.setupWithViewPager(viewPager);
    }
}
