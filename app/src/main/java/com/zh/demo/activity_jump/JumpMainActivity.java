package com.zh.demo.activity_jump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zh.demo.R;

public class JumpMainActivity extends AppCompatActivity {

    private Context mContext;
    private Button mJumpSchemeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_main);

        mContext = this;
        mJumpSchemeBtn = findViewById(R.id.jump_enter_scheme);
        mJumpSchemeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("zh://com.zh.demo:8888/testScheme?name=xiaoming&id=3");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });
    }
}