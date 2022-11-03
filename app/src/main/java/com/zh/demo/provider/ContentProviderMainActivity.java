package com.zh.demo.provider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zh.demo.R;

import java.lang.ref.WeakReference;

public class ContentProviderMainActivity extends AppCompatActivity {

    public Context mContext;
    private Button mInsertIntoProviderBtn;
    private Button mDeleteFromProviderBtn;
    private Button mUpdateProviderBtn;
    private Button mSelectFromProviderBtn;
    public static TextView mDisplayTableInfoTv = null;

    private static final int PROVIDER_HANDLER_MESSAGE_DISPLAY = 0;
    private static final int PROVIDER_HANDLER_MESSAGE_INSERT = 1;
    private static final int PROVIDER_HANDLER_MESSAGE_DELETE = 2;
    private static final int PROVIDER_HANDLER_MESSAGE_UPDATE = 3;
    private static final int PROVIDER_HANDLER_MESSAGE_QUERY = 4;
    private MyProviderHandler myProviderHandler;

    private static class MyProviderHandler extends Handler {

        private WeakReference<Activity> reference;

        public MyProviderHandler(Activity activity) {
            this.reference = new WeakReference(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {

            Uri uri_employee = Uri.parse("content://com.zh.demo.provider/employee");
            ContentResolver resolver = reference.get().getContentResolver();
            ContentValues values = new ContentValues();
            Cursor cursor = null;
            switch (msg.what) {
                case PROVIDER_HANDLER_MESSAGE_DISPLAY:
                    if(msg.obj == null){
                        //展示全部info
                        cursor = resolver.query(uri_employee, null,
                                null, null,
                                null);
                    } else {
                        //展示特定条件的info
                        cursor = (Cursor) msg.obj;
                    }
                    if(cursor == null) break;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Table: employee\n");
                    while( cursor.moveToNext()){
                        sb.append("id=" +cursor.getInt(cursor.getColumnIndex("id")) + "\t");
                        sb.append("name=" +cursor.getString(cursor.getColumnIndex("name")) + "\t");
                        sb.append("age=" +cursor.getString(cursor.getColumnIndex("age")) + "\t");
                        sb.append("address=" +cursor.getString(cursor.getColumnIndex("address")) + "\t");
                        sb.append("salary=" +cursor.getString(cursor.getColumnIndex("salary")) + "\n");
                    }
                    cursor.close();

                    if(mDisplayTableInfoTv != null){
                        mDisplayTableInfoTv.setText(sb.toString());
                    }
                    break;
                case PROVIDER_HANDLER_MESSAGE_INSERT:
                    // 准备好要插入的data
                    values.clear();
                    values.put("name", "new" + (int) (Math.random() * 100));
                    values.put("age", (int) (Math.random() * 50));//生成0-50的随机数
                    values.put("address", "new Earth");
                    values.put("salary", Math.random() * 20000);

                    // 将data插入表中
                    resolver.insert(uri_employee, values);

                    //展示此时provider的info
                    this.sendEmptyMessage(PROVIDER_HANDLER_MESSAGE_DISPLAY);
                    break;
                case PROVIDER_HANDLER_MESSAGE_DELETE:

                    // 删除id=5的记录
                    resolver.delete(uri_employee, "name like ?", new String[]{"new__"});

                    //展示此时provider的info
                    this.sendEmptyMessage(PROVIDER_HANDLER_MESSAGE_DISPLAY);
                    break;
                case PROVIDER_HANDLER_MESSAGE_UPDATE:

                    // 更改: 将name中开头是new的记录，更改他的address为updated Earth
                    values.clear();
                    values.put("address", "updated Earth");
                    resolver.update(uri_employee, values, "name like ?", new String[]{"new%"});

                    //展示此时provider的info
                    this.sendEmptyMessage(PROVIDER_HANDLER_MESSAGE_DISPLAY);

                    break;
                case PROVIDER_HANDLER_MESSAGE_QUERY:
                    cursor = resolver.query(uri_employee, null,
                            null, null,
                            null);
                    Message message = Message.obtain();
                    message.what = PROVIDER_HANDLER_MESSAGE_DISPLAY;
                    message.obj = cursor;
                    this.sendMessage(message);
                    break;
                default:
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_main);

        mContext = this;
        myProviderHandler = new MyProviderHandler(this);
        mDisplayTableInfoTv = findViewById(R.id.provider_display_employee_info);

        // 插入data到provider中
        mInsertIntoProviderBtn = findViewById(R.id.provider_insert_data);
        mInsertIntoProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = PROVIDER_HANDLER_MESSAGE_INSERT;
                myProviderHandler.sendMessage(message);
            }
        });

        // 从provider中删除data
        mDeleteFromProviderBtn = findViewById(R.id.provider_delete_data);
        mDeleteFromProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = PROVIDER_HANDLER_MESSAGE_DELETE;
                myProviderHandler.sendMessage(message);
            }
        });

        // 更新provider中的data
        mUpdateProviderBtn = findViewById(R.id.provider_update_data);
        mUpdateProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = PROVIDER_HANDLER_MESSAGE_UPDATE;
                myProviderHandler.sendMessage(message);
            }
        });

        // 查找provider中的
        mSelectFromProviderBtn = findViewById(R.id.provider_query_data);
        mSelectFromProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = PROVIDER_HANDLER_MESSAGE_QUERY;
                myProviderHandler.sendMessage(message);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myProviderHandler.removeCallbacksAndMessages(null);
        mDisplayTableInfoTv = null;
    }
}