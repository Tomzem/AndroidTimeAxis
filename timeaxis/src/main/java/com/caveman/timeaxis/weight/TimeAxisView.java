package com.caveman.timeaxis.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.caveman.timeaxis.R;

/**
 * Created by Administrator on 2018/10/25.
 * <p>
 * Description:时间轴
 */
public class TimeAxisView extends View {
    private static final String TAG = "TimeAxisView";

    public static final int SOLID_CIRCLE = 0;
    public static final int HOLLOW_CIRCLE = 1;
    public static final int CENTER_CIRCLE = 2;

    public static final int GRAVITY_VERTICAL = 1;
    public static final int GRAVITY_HORIZONTAL = -1;

    private Paint mPaint;

    private float mLineWidth = 5; //线宽
    private int mLineColor = 0;     //线得颜色
    private int mCircleImage = 0; //关键点图标
    private float mCircleRadius; //圆圈半径

    // 中心圆类型
    private int CIRCLE_SHAPE = SOLID_CIRCLE;
    // 横轴or竖轴
    private int GRAVITY = GRAVITY_VERTICAL;

    private boolean isHeadPoint = false;
    private boolean isFootPoint = false;

    private float width;
    private float height;
    private float start; //画线轴得起点

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
        Gravity(GRAVITY_VERTICAL);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
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
//        Log.d(TAG, "onDraw: run" + mLineColor);

        mCircleRadius = mLineWidth * 2;
        mPaint.setColor(mLineColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mLineWidth);

        if (!isHeadPoint){
            if (GRAVITY == GRAVITY_VERTICAL){
                canvas.drawLine(start, 0, start, getHeight()/2 -mCircleRadius, mPaint);
            }else{
//                canvas.drawLine(0, start, );
            }
        }
        if (!isFootPoint) {
            if (GRAVITY == GRAVITY_VERTICAL){
                canvas.drawLine(start, getHeight() / 2 + mCircleRadius, start, getHeight(), mPaint);
            }else{

            }
        }

        switch (CIRCLE_SHAPE){
            case SOLID_CIRCLE:
                mPaint.setStyle(Paint.Style.FILL);
                break;
            case HOLLOW_CIRCLE:
                mPaint.setStyle(Paint.Style.STROKE);
                break;
            case CENTER_CIRCLE:
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setStrokeWidth(mLineWidth/2);
                canvas.drawCircle(getWidth()/2-mCircleRadius/4, getHeight()/2, mCircleRadius/2, mPaint);
                mPaint.setStyle(Paint.Style.STROKE);
                break;
        }
        mPaint.setStrokeWidth(mLineWidth/2);
        canvas.drawCircle(getWidth()/2-mCircleRadius/4, getHeight()/2, mCircleRadius, mPaint);
    }

    /**
     * 设置线宽
     * @param width 直线轴宽
     */
    public void setLineWidth(float width){
        this.mLineWidth = width;
        update();
    }

    /**
     * 设置线颜色
     * @param resourceId 直线轴颜色
     */
    public void setLineColor(int resourceId){
        this.mLineColor = getResources().getColor(resourceId);
        update();
    }

    /**
     * 设置圆圈类型
     * @param shape  中心园类型
     */
    public void setCircleShape(int shape){
        this.CIRCLE_SHAPE = shape;
        update();
    }

    /**
     * 头部关键点
     * @param isHead
     */
    public void isHeadView(boolean isHead){
        this.isHeadPoint = isHead;
        update();
    }

    /**
     * 足部关键点
     * @param isFoot
     */
    public void isFootView(boolean isFoot){
        this.isFootPoint = isFoot;
        update();
    }

    /**
     *  横竖展示
     * @param gravity
     */
    public void Gravity(int gravity){
        this.GRAVITY = gravity;
        switch (GRAVITY){
            case GRAVITY_VERTICAL:
                start = (getWidth() - mLineWidth)/2;
                break;
            case GRAVITY_HORIZONTAL:
                start = (getHeight() - mLineWidth)/2;
                break;
        }
        update();
    }



    private void update(){
        this.invalidate();
    }
}
