package com.zh.demo.activity_jump;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zh.demo.R;

public class JumpAnimationActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_animation_b);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_no,R.anim.anim_bottom_out);
    }
}