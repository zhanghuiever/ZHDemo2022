package com.zh.demo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zh.demo.R;

public class FragmentMainActivity extends AppCompatActivity implements View.OnClickListener{

    Button mSwitchOneFragment;
    Button mSwitchTwoFragment;
    Button mSwitchThreeFragment;

    OneFragment mOneFragment;
    TwoFragment mTwoFragment;
    ThreeFragment mThreeFragment;
    private Fragment mCurrentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);

        mSwitchOneFragment = findViewById(R.id.fragment_switch_one);
        mSwitchTwoFragment = findViewById(R.id.fragment_switch_two);
        mSwitchThreeFragment = findViewById(R.id.fragment_switch_three);

        mSwitchOneFragment.setOnClickListener(this);
        mSwitchTwoFragment.setOnClickListener(this);
        mSwitchThreeFragment.setOnClickListener(this);

        if(mOneFragment == null){
            mOneFragment = new OneFragment();
        }
        if(mTwoFragment == null){
            mTwoFragment = new TwoFragment();
        }
        if(mThreeFragment == null){
            mThreeFragment = new ThreeFragment();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_content, mOneFragment);
        fragmentTransaction.commit();
        // 标记当前的fragment
        mCurrentFragment = mOneFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_switch_one:
                showFragment(mOneFragment);
                break;
            case R.id.fragment_switch_two:
                showFragment(mTwoFragment);
                break;
            case R.id.fragment_switch_three:
                showFragment(mThreeFragment);
                break;
        }
    }

    public void showFragment(Fragment nextFragment){
        // 要显示的fragment已经是当前fragment，则不进行操作
        if(nextFragment != null && nextFragment != mCurrentFragment){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // 隐藏当前的fragment
            transaction.hide(mCurrentFragment);
            // 回退栈中没有该fragment实例，则将该fragment添加到回退栈
            if(!nextFragment.isAdded()){
                transaction.add(R.id.fl_content, nextFragment);
                transaction.addToBackStack(null);
            }
            transaction.show(nextFragment).commit();
            // 刷新当前fragment实例
            mCurrentFragment = nextFragment;
        }
    }

}