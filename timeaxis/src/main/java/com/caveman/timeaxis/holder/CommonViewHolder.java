package com.caveman.timeaxis.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/10/26.
 * <p>
 * Description:
 */
public class CommonViewHolder extends RecyclerView.ViewHolder{

    private View mContentView;

    private SparseArray<View> mSparseArray = new SparseArray<>();

    private Context mContext;

    public CommonViewHolder(View itemView) {
        super(itemView);
        this.mContentView = itemView;
    }

    public View getContentView() {
        return mContentView;
    }

//    public static CommonViewHolder getHolder(Context context, View convertView, int resID) {
//        CommonViewHolder holder = null;
//        if(convertView == null){
//            holder = new CommonViewHolder(context, resID);
//        } else {
//            holder = (CommonViewHolder) convertView.getTag();
//        }
//        return holder;
//    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入mSparseArray
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int id) {
        View view = mSparseArray.get(id);
        if (view == null) {
            view = mContentView.findViewById(id);
            mSparseArray.append(id, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置数据
     *
     * @param id
     * @param text
     * @return
     */
    public CommonViewHolder setText(int id, String text) {
        TextView view = getView(id);
        view.setText(Html.fromHtml(text));
        return this;
    }

//    public CommonViewHolder setImageByUrl(int id, String url) {
//        ImageView view = getView(id);
//        Glide.with(mContext).load(url).into(view);
//        return this;
//    }

    public CommonViewHolder setImageResource(int id, int resId) {
        ImageView view = getView(id);
        view.setImageResource(resId);
        return this;
    }

    public CommonViewHolder setImageBitmap(int id, Bitmap bitmap) {
        ImageView view = getView(id);
        view.setImageBitmap(bitmap);
        return this;
    }
}