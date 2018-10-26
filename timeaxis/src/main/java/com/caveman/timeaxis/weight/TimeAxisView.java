package com.caveman.timeaxis.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

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
    private Context mContext;

    private float mLineWidth = 5; //线宽
    private int mLineColor = 0;     //线得颜色
    private int mCircleImage = 0; //关键点图标
    private float mCircleRadius = 0; //圆圈半径

    // 中心圆类型
    private int CIRCLE_SHAPE = SOLID_CIRCLE;
    // 横轴or竖轴
    private int GRAVITY = GRAVITY_VERTICAL;

    private boolean isHeadPoint = false;
    private boolean isFootPoint = false;

    private float width;
    private float height;
    private float start; //画线轴得起点
    private String mBigText = "";
    private String mSmallText = "";
    private int defaultSize = 100;

    public TimeAxisView(Context context) {
        this(context, null);
    }

    public TimeAxisView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeAxisView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getProperSize(widthMeasureSpec), getProperSize(heightMeasureSpec));
    }

    private int getProperSize( int measureSpec){
        int properSize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                // 没有指定大小，设置为默认大小
                properSize = defaultSize;
                break;
            case MeasureSpec.EXACTLY:
                // 固定大小，无需更改其大小
                properSize = size;
                break;
            case MeasureSpec.AT_MOST:
                // 此处该值可以取小于等于最大值的任意值，此处取最大值的1/4
                properSize = size / 4;
        }

        return properSize;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCircleRadius = mLineWidth * 2;
        Gravity(GRAVITY);
        mPaint.setColor(mLineColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mLineWidth);

        if (!isHeadPoint){
            if (GRAVITY == GRAVITY_VERTICAL){
                canvas.drawLine(start, 0, start, getHeight()/2-mCircleRadius, mPaint);
            }else{
                canvas.drawLine(0, start, getWidth()/2 - mCircleRadius, start, mPaint);
            }
        }
        if (!isFootPoint) {
            if (GRAVITY == GRAVITY_VERTICAL){
                canvas.drawLine(start, getHeight() / 2+mCircleRadius, start, getHeight(), mPaint);
            }else{
                canvas.drawLine(getWidth()/2 + mCircleRadius, start, getWidth(), start,  mPaint);
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
                canvas.drawCircle(start, getHeight()/2, mCircleRadius/2, mPaint);
                mPaint.setStyle(Paint.Style.STROKE);
                break;
        }
        mPaint.setStrokeWidth(mLineWidth/2);
        canvas.drawCircle(start, getHeight()/2, mCircleRadius, mPaint);
        drawLeftText(canvas);
    }



    private void drawLeftText(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        if (!mSmallText.isEmpty()){
            mPaint.setTextSize(40);
            Rect bounds = new Rect();
            mPaint.getTextBounds(mSmallText, 0, mSmallText.length(), bounds);
            canvas.drawText(mSmallText, start - bounds.width() - mCircleRadius*2 , getHeight()/2 + bounds.height(), mPaint);
        }
        if (!mBigText.isEmpty()){
            mPaint.setTextSize(60);
            Rect bounds = new Rect();
            mPaint.getTextBounds(mBigText, 0, mBigText.length(), bounds);
            canvas.drawText(mBigText, start - bounds.width() - mCircleRadius*2 , getHeight()/2, mPaint);
        }
    }

    private void Gravity(int gravity){
        switch (GRAVITY){
            case GRAVITY_VERTICAL:
                start = getWidth() - mLineWidth -mCircleRadius ;
                break;
            case GRAVITY_HORIZONTAL:
                start = (getHeight() - mLineWidth)/2 + mCircleRadius/4;
                break;
        }
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
     * @param gravity （暂不开放）
     */
    private void setGravity(int gravity){
        this.GRAVITY = gravity;
    }

    /**
     *  设置小字显示
     * @param time
     */
    public void setSmallText(String text){
        this.mSmallText = text;
        update();
    }

    /**
     *  设置大字显示
     * @param time
     */
    public void setBigText(String text){
        this.mBigText = text;
        update();
    }

    private void update(){
        this.invalidate();
    }
}
