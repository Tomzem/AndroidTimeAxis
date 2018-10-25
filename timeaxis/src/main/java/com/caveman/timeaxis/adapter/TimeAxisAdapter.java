package com.caveman.timeaxis.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caveman.timeaxis.R;
import com.caveman.timeaxis.weight.TimeAxisView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/10/25.
 * <p>
 * Description:
 */
public class TimeAxisAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private  List<Map<String, String>> data;
    private int layout = R.layout.item_list;

    public TimeAxisAdapter(Context mContext, List<Map<String, String>> data){
        this.mContext = mContext;
        this.data = data;
    }

    public TimeAxisAdapter(Context mContext, List<Map<String, String>> data, int layout) {
        this.mContext = mContext;
        this.data = data;
        this.layout = layout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(mContext).inflate(layout, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Holder holder = (Holder)viewHolder;

        holder.mTaLine.setLineWidth(5);
        holder.mTaLine.setLineColor(R.color.colorAccent);

        if (i == 0){
            //首个item
            holder.mTaLine.isHeadView(true);
        }

        if (i ==data.size()-1 ){
            //最后一个item
            holder.mTaLine.isFootView(true);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder  {
        public TimeAxisView mTaLine;
        public TextView mTvTime;
        public TextView mTvText;

        public Holder(View root) {
            super(root);
            mTaLine = root.findViewById(R.id.tav_line);
        }
    }
}
