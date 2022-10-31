package com.zh.demo.activity_jump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zh.demo.R;
/*
* 实现Activity切换动画的方式
* 一、利用overridePendingTransition(int enterAnim, int exitAnim)实现
* 二、利用自定义style的方式为某个activity或者app所有的activity设计切换动画*/
public class JumpAnimationActivity extends AppCompatActivity {

    private Context mContext;
    private Button mJumpAnimEnterBBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_animation);
        mContext = this;

        mJumpAnimEnterBBtn = findViewById(R.id.jump_anim_enter_b);
        mJumpAnimEnterBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, JumpAnimationActivityB.class);
                mContext.startActivity(intent);
                overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_no);
            }
        });
    }
}