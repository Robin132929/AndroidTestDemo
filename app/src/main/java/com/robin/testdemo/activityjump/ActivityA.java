package com.robin.testdemo.activityjump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.robin.testdemo.MainActivity;
import com.robin.testdemo.R;

/**
 * a跳转到b 生命周期 onpasue -->oncreate -->onstart -->onresume -->onstop --> onSaveInstanceState
 *                    a          b           b          b           a                a
 *
 *  此处还有一个地方需要注意 onSaveInstanceState的调用时机 ：
 *                                             1、 api < 11，onSaveInstance在onPause之前执行
 *                                             2、11 <= api < 28，onSaveInstance在onPause之后，onStop之前执行
 *                                             3、api >= 28，onSaveInstance在onStop之后执行
 *
 */
public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        findViewById(R.id.bt_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityA.this, ActivityB.class);
                startActivity(intent);
            }
        });
    }
}
