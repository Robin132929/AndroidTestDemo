package com.robin.testdemo.fragmentlazyload.addshow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.robin.testdemo.FragmentLifeCallback;
import com.robin.testdemo.R;
import com.robin.testdemo.fragmentlazyload.FragmentA;
import com.robin.testdemo.fragmentlazyload.FragmentB;
import com.robin.testdemo.fragmentlazyload.FragmentC;
import com.robin.testdemo.fragmentlazyload.FragmentD;

public class FragmentAddLazyLoadActivity extends AppCompatActivity {

   Fragment curr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_add_lazy_load);
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentLifeCallback(),true);

        final FragmentA fragmentA=new FragmentA();
        final FragmentB fragmentB=new FragmentB();
//        FragmentC fragmentC=new FragmentC();
//        FragmentD fragmentD=new FragmentD();
       FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
       ft.add(R.id.fl_layout,fragmentA);
        ft.add(R.id.fl_layout,fragmentB);
//        ft.add(R.id.fl_layout,fragmentC);
//        ft.add(R.id.fl_layout,fragmentD);
        ft.hide(fragmentB);
//        ft.hide(fragmentC);
//        ft.hide(fragmentD);
        ft.show(fragmentA);
        curr=fragmentA;
        ft.commit();
        findViewById(R.id.bt_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
                if (curr instanceof FragmentA){
                    curr=fragmentB;
                    ft.hide(fragmentA);
                    ft.show(fragmentB);
                }else {
                    ft.hide(fragmentB);
                    ft.show(fragmentA);
                }
                 ft.commit();
            }
        });
    }
}
