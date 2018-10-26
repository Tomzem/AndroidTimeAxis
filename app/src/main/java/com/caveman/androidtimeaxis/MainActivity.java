package com.caveman.androidtimeaxis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.caveman.timeaxis.adapter.TimeAxisAdapter;
import com.caveman.timeaxis.weight.TimeAxisView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRwList;
    private ListAdapter timeAdapter;
    private List<TimeInfo> data;

    private TimeAxisView mTavLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        mRwList = findViewById(R.id.my_recycler_view);
        timeAdapter = new ListAdapter(data, this, R.layout.list_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRwList.setLayoutManager(linearLayoutManager);
        mRwList.setAdapter(timeAdapter);
    }

    private void initData() {
        data = new ArrayList<>();
        String content = "内容";
        for (int i = 0; i < 10; i++){
            TimeInfo timeInfo = new TimeInfo();
            content = content + "内容" + i ;
            timeInfo.setMsg(content);
            timeInfo.setBigText("大字"+i);
            timeInfo.setSmallText("小字体哦" + i);
            data.add(timeInfo);
        }
    }
}
