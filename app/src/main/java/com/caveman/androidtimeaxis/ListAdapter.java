package com.caveman.androidtimeaxis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.caveman.timeaxis.holder.CommonViewHolder;
import com.caveman.timeaxis.adapter.TimeAxisAdapter;
import com.caveman.timeaxis.weight.TimeAxisView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/26.
 * <p>
 * Description:
 */
public class ListAdapter extends TimeAxisAdapter<TimeInfo> {

    public ListAdapter(List<TimeInfo> mDataSource, Context mContext) {
        super(mDataSource, mContext);
    }

    public ListAdapter(List<TimeInfo> mDataSource, Context mContext, int resID) {
        super(mDataSource, mContext, resID);
    }

    @Override
    protected void initView(CommonViewHolder holder, int position) {
        TimeInfo timeInfo = mDataSource.get(position);

        TimeAxisView mTimeAxisView = holder.getView(com.caveman.timeaxis.R.id.tav_line);
        mTimeAxisView.setBigText(timeInfo.getBigText());
        mTimeAxisView.setSmallText(timeInfo.getSmallText());
        //根据状态设置圆圈样式
        if (timeInfo.getState() == 1){
            mTimeAxisView.setCircleShape(TimeAxisView.SOLID_CIRCLE);
        }else if(timeInfo.getState() == 0){
            mTimeAxisView.setCircleShape(TimeAxisView.CENTER_CIRCLE);
        }else{
            mTimeAxisView.setCircleShape(TimeAxisView.HOLLOW_CIRCLE);
        }

        ImageView imageView = holder.getView(R.id.img_content);
        if (timeInfo.getImagePath().isEmpty()){
            imageView.setVisibility(View.GONE);
        }else{
            imageView.setVisibility(View.VISIBLE);
            holder.setImageByUrl(R.id.img_content, timeInfo.getImagePath());
        }




        holder.setText(R.id.tv_content, timeInfo.getMsg());
    }
}
