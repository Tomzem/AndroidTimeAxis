package com.caveman.timeaxis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.caveman.timeaxis.R;
import com.caveman.timeaxis.holder.CommonViewHolder;
import com.caveman.timeaxis.weight.TimeAxisView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/25.
 * <p>
 * Description:
 */
public abstract class TimeAxisAdapter<T> extends RecyclerView.Adapter{

    protected List<T> mDataSource;
    private Context mContext;
    private int resID = R.layout.list_item;

    public TimeAxisAdapter(List<T> mDataSource, Context mContext) {
        this.mDataSource = mDataSource;
        this.mContext = mContext;
    }

    public TimeAxisAdapter(List<T> mDataSource, Context mContext, int resID) {
        this.mDataSource = mDataSource;
        this.mContext = mContext;
        this.resID = resID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CommonViewHolder(LayoutInflater.from(mContext).inflate(resID, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CommonViewHolder holder = (CommonViewHolder)viewHolder;
        if (resID == R.layout.list_item){
            TimeAxisView mTimeAxisView = holder.getView(R.id.tav_line);
            if (position == 0){
                mTimeAxisView.isHeadView(true);
            }else{
                mTimeAxisView.isHeadView(false);
            }
            if (position == mDataSource.size()-1){
                mTimeAxisView.isFootView(true);
            }else{
                mTimeAxisView.isFootView(false);
            }
        }
        initView(holder, position);
    }

    protected abstract void initView(CommonViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mDataSource == null ? 0 : mDataSource.size();
    }

    public void refresh(List<T> dataSource) {
        mDataSource = dataSource;
        this.notifyDataSetChanged();
    }

    public void deleteList(int position) {
        mDataSource.remove(position);
        this.notifyDataSetChanged();
    }

}
