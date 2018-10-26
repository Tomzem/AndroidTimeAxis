package com.caveman.androidtimeaxis;

import android.content.Context;

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

        if (position<3){
            mTimeAxisView.setCircleShape(TimeAxisView.SOLID_CIRCLE);
        }else if(position == 3){
            mTimeAxisView.setCircleShape(TimeAxisView.CENTER_CIRCLE);
        }else{
            mTimeAxisView.setCircleShape(TimeAxisView.HOLLOW_CIRCLE);
        }


        holder.setText(R.id.tv_content, timeInfo.getMsg());
    }
}
