package com.zh.demo.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zh.demo.R;

public class ServiceMainActivity extends AppCompatActivity {

    private Context mContext;
    private Button mStartIntentServiceBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);

        mContext = this;
        mStartIntentServiceBtn = findViewById(R.id.service_start_intentService);
        mStartIntentServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyIntentService.class);
                mContext.startService(intent);
            }
        });
    }
}