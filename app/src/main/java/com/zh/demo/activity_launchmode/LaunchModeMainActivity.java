package com.zh.demo.activity_launchmode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zh.demo.R;

public class LaunchModeMainActivity extends AppCompatActivity {

    private final static String TAG = "cydia";
    private final static String CLASS_NAME = LaunchModeMainActivity.class.getSimpleName();
    private Context mContext;
    private Button mEnterSecondActivityBtn;
    private Button mEnterThirdActivityBtn;
    private Button mEnterFourthActivityBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode_main);
        Log.i(TAG, CLASS_NAME+ " onCreate");

        mContext = this;

        mEnterSecondActivityBtn = findViewById(R.id.enter_second);
        mEnterSecondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SecondActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        mEnterThirdActivityBtn = findViewById(R.id.enter_third);
        mEnterThirdActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ThirdActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mEnterFourthActivityBtn = findViewById(R.id.enter_fourth);
        mEnterFourthActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FourthActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, CLASS_NAME+ " onStart");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, CLASS_NAME+ " onConfigurationChanged");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, CLASS_NAME+ " onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, CLASS_NAME+ " onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, CLASS_NAME+ " onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, CLASS_NAME+ " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, CLASS_NAME+ " onDestroy");
    }
}