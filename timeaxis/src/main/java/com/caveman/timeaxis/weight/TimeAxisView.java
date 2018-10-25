package com.caveman.timeaxis.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.caveman.timeaxis.R;

/**
 * Created by Administrator on 2018/10/25.
 * <p>
 * Description:时间轴
 */
public class TimeAxisView extends View {

    private Paint mPaint;
    private Canvas mCanvas;

    private float mLineWidth = 5; //线宽
    private int mLineColor = 0;     //线得颜色
    private int mCircleImage = 0; //关键点图标

    private float width;
    private float height;

    public TimeAxisView(Context context) {
        this(context, null);
    }

    public TimeAxisView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeAxisView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TimeAxisView);
        if (ta != null){
            mLineWidth = ta.getDimension(R.styleable.TimeAxisView_line_width, 5);
            mLineColor = ta.getColor(R.styleable.TimeAxisView_line_color, 0);
            mCircleImage = ta.getResourceId(R.styleable.TimeAxisView_circle_image, 0);
        }

        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mLineColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth() / 5;
        height = getMeasuredHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;

        mPaint.setStrokeWidth(mLineWidth);
        float start = (getWidth() - mLineWidth)/2;
        canvas.drawLine(start, 0, start, getHeight(), mPaint);
    }

    public void setLineWidth(float width){
        this.mLineWidth = width;
        initView();
    }

}
