package com.robin.testdemo.flexlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ToggleButton;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.robin.testdemo.R;

public class FlexLayoutActivity extends AppCompatActivity {
   FlexboxLayoutManager flexboxLayoutManager;
   RecyclerView recyclerView;
   ToggleButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_layout);
        recyclerView=findViewById(R.id.flex);
        button=findViewById(R.id.tb);
        flexboxLayoutManager=new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.NOWRAP);
        recyclerView.setLayoutManager(flexboxLayoutManager);
    }
}
