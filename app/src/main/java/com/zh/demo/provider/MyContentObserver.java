package com.zh.demo.provider;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Message;

/**
 * 用来监听MyContentProvider数据变化
 * @author zh
 * @date 2022/11/03
 */
public class MyContentObserver extends ContentObserver {

    private Context mContext;
    private Handler mHandler;


    public MyContentObserver(Context mContext, Handler mHandler) {
        super(mHandler);
        this.mContext = mContext;
        this.mHandler = mHandler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Message message = Message.obtain();
        message.what = ContentProviderMainActivity.PROVIDER_HANDLER_MESSAGE_OBSERVER;
        message.obj = "my provider changes right now!";
        mHandler.sendMessage(message);
    }
}
