package com.zh.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.zh.demo.activity_launchmode.LaunchModeMainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DemoListAdapter.OnDemoListItemClickListener{

    private ListView mDemoList;
    private List<Map<String, Object>> mDemoListDates = new ArrayList<Map<String, Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDemoList = findViewById(R.id.demo_list);

        mDemoListDates = new ArrayList<Map<String, Object>>();
        String[] titles = getResources().getStringArray(R.array.demo_list);
        for(String title : titles){
            Map<String, Object> itemDates = new HashMap<String, Object>();
            itemDates.put(DemoListAdapter.DEMO_LIST_ITEM_TITLE, title);
            mDemoListDates.add(itemDates);
        }
        DemoListAdapter demoListAdapter = new DemoListAdapter(this, mDemoListDates);

        demoListAdapter.setOnDemoListItemClickListener(this);

        mDemoList.setAdapter(demoListAdapter);
    }


    @Override
    public void onItemClick(int position) {
        switch (position){
            case 0:
                Intent intent = new Intent(this, LaunchModeMainActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
            default:
        }
    }
}