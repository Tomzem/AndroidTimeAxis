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
        data.add(new TimeInfo("订单提交成功","2018/10/27","13:05","",1));
        data.add(new TimeInfo("订单已支付","2018/10/27","13:05","",1));
        data.add(new TimeInfo("商家已接单","2018/10/27","13:05","",1));
        data.add(new TimeInfo("骑手已接单","2018/10/27","13:11","",1));
        data.add(new TimeInfo("骑手已到店","2018/10/27","13:16","",1));
        data.add(new TimeInfo("骑手已取货","2018/10/27","13:22","",1));
        data.add(new TimeInfo("骑手正在送货","","","https://avatars3.githubusercontent.com/u/32257815?s=64&v=4",0));
        data.add(new TimeInfo("商品已送达","","","",-1));
    }
}
