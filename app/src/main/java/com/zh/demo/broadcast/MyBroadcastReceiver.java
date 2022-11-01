package com.zh.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public interface DisplayBroadcastInfo{
        void display(String text);
    }
    private DisplayBroadcastInfo mDisplayBroadcastInfo;

    public void setDisplayBroadcastInfo(DisplayBroadcastInfo mDisplayBroadcastInfo) {
        this.mDisplayBroadcastInfo = mDisplayBroadcastInfo;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(mDisplayBroadcastInfo != null){
            mDisplayBroadcastInfo.display("success");
        }
        Log.i("cydia","onReceiver: success");
    }
}
