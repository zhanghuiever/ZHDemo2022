package com.zh.demo.activity_jump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zh.demo.R;

public class MySchemeActivity extends AppCompatActivity {

    private Context mContext;
    private Button mGetUriInfoBtn;
    private TextView mDisplayInfoTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scheme);

        mContext = this;

        Uri uri = getIntent().getData();
        StringBuilder sb = new StringBuilder();
        if(uri != null){
            sb.append("scheme: ").append(uri.getScheme()).append("\n")
                    .append("host: ").append(uri.getHost()).append("\n")
                    .append("port: ").append(uri.getPort()).append("\n")
                    .append("=====path=====\n")
                    .append("path_str: ").append(uri.getPath()).append("\n")
                    .append("path_list: ").append(uri.getPathSegments().toString()).append("\n")
                    .append("=====query=====\n")
                    .append("name: ").append(uri.getQueryParameters("name")).append("\n")
                    .append("id: ").append(uri.getQueryParameters("id")).append("\n")
                    .append("not_exist_key: ").append(uri.getQueryParameters("not_exist_key")).append("\n");

        }

        mGetUriInfoBtn = findViewById(R.id.scheme_get_uri_info);
        mDisplayInfoTv = findViewById(R.id.display_uri_info);
        mGetUriInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDisplayInfoTv.setText(sb.toString());
            }
        });
    }
}