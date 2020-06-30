package com.robin.testdemo.activityjump;


import android.app.Activity;
import android.os.Bundle;

import com.robin.testdemo.R;

public class ActivityB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

    }
}
