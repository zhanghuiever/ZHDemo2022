package com.zh.demo.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zh.demo.R;

public class BroadcastMainActivity extends AppCompatActivity implements MyBroadcastReceiver.DisplayBroadcastInfo{

    private Context mContext;
    private Button mSendBroadcastBtn;
    private Button mAskforSendingBroadcastBtn;

    private MyBroadcastReceiver myBroadcastReceiver;

    private SecondBroadcastReceiver mSecondBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_main);

        mContext = this;
        //谁有权接受我的广播
        mSendBroadcastBtn = findViewById(R.id.broadcast_send);
        mSendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("ddd");
                mContext.sendBroadcast(intent, "com.zh.demo.receive_broadcast");
//                Toast.makeText(mContext,"send permission broadcast success!!!", Toast.LENGTH_SHORT).show();
            }
        });
        myBroadcastReceiver = new MyBroadcastReceiver();
        myBroadcastReceiver.setDisplayBroadcastInfo(this);

        //谁有权给我发送广播
        mAskforSendingBroadcastBtn = findViewById(R.id.broadcast_send_use_permission);
        mAskforSendingBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("second");
                mContext.sendBroadcast(intent);
            }
        });
        mSecondBroadcastReceiver = new SecondBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //谁有权接受我的广播
        IntentFilter intentFilter =new IntentFilter("ddd");
        registerReceiver(myBroadcastReceiver, intentFilter, "com.zh.demo.receive_broadcast", null);

        //谁有权给我发送广播
        IntentFilter filter =new IntentFilter("second");
        registerReceiver(mSecondBroadcastReceiver, filter, "com.zh.demo.send_broadcast", null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastReceiver);
        unregisterReceiver(mSecondBroadcastReceiver);
    }

    @Override
    public void display(String text) {
        Toast.makeText(mContext,text, Toast.LENGTH_SHORT).show();
    }
}