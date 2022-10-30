package com.zh.demo.activity_launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zh.demo.R;

public class SecondActivity extends AppCompatActivity {

    private final static String TAG = "cydia";
    private final static String CLASS_NAME = SecondActivity.class.getSimpleName();
    private Context mContext;
    private Button mEnterMainBtn;
    private Button mEnterSelfBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i(TAG, CLASS_NAME+ " onCreate");

        mContext = this;
        mEnterMainBtn = findViewById(R.id.second_enter_main);
        mEnterMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LaunchModeMainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mEnterSelfBtn = findViewById(R.id.second_enter_self);
        mEnterSelfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SecondActivity.class);
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