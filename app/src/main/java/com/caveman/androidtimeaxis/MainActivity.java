package com.caveman.androidtimeaxis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.caveman.timeaxis.adapter.TimeAxisAdapter;
import com.caveman.timeaxis.weight.TimeAxisView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRwList;
    private TimeAxisAdapter timeAdapter;
    private List<Map<String, String>> data;

    private TimeAxisView mTavLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        mRwList = findViewById(R.id.rw_list);
        timeAdapter = new TimeAxisAdapter(this, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRwList.setLayoutManager(linearLayoutManager);
        mRwList.setAdapter(timeAdapter);
    }

    private void initData() {
        data = new ArrayList<>();
        String content = "内容";
        for (int i = 0; i < 10; i++){
            Map<String, String> map = new HashMap<>();
            map.put("time", System.currentTimeMillis() + "");
            map.put("content", content + i);
            content = content + content;
            data.add(map);
        }
    }
}
