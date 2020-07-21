package com.robin.testdemo.activityjump;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.robin.testdemo.R;

public class ActivityB extends Activity {
    private static final String TAG = "ActivityB";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Intent intent= getIntent();
        if (intent != null) {
            Log.i(TAG, "onCreate: data "+intent.getData());
            Log.i(TAG, "onCreate: parse data :");
            Log.i(TAG, "onCreate: Scheme "+intent.getData().getScheme());
            Log.i(TAG, "onCreate: host "+intent.getData().getHost());
            Log.i(TAG, "onCreate: Authority "+intent.getData().getAuthority());
            Log.i(TAG, "onCreate:Path "+intent.getData().getPath());
            Log.i(TAG, "onCreate: Query "+intent.getData().getQuery());
            Log.i(TAG, "onCreate: "+intent.getData().getQueryParameter("from"));
            for (String queryParameterName : intent.getData().getQueryParameterNames()) {
                Log.i(TAG, "onCreate: qq "+queryParameterName);
            }
        }
findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
    }
}
