package com.hzq.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.materialdesigndemo.R;

/**
 * Created by hezhiqiang on 16/8/30.
 */
public class CircleProgressBar extends View {
    private String prentStr = "%";
    private String descStr = "完成度";
    private int main_text_color;
    private int desc_text_color;
    private int progress_color;
    private int progress_down_color;
    private int maxProgress = 100;
    private int progress = 0;
    private int curProgress = 0;
    private int progressStrokeWidth = 20;
    private int number_text_size = 20;
    private int desc_text_size = 10;
    private boolean isDrawCircle = true;
    //画圆所在的距形区域
    RectF oval;
    Paint numberPaint,txtPaint;
    Paint circlePaint;

    public CircleProgressBar(Context context) {
        this(context,null,0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView, defStyleAttr, 0);
        prentStr = t.getString(R.styleable.CircleProgressView_perent);
        descStr = t.getString(R.styleable.CircleProgressView_desc);
        main_text_color = t.getColor(R.styleable.CircleProgressView_main_text_color,Color.WHITE);
        desc_text_color = t.getColor(R.styleable.CircleProgressView_desc_text_color,Color.WHITE);
        progress_color = t.getColor(R.styleable.CircleProgressView_progress_color,Color.GREEN);
        progress_down_color = t.getColor(R.styleable.CircleProgressView_progress_down_color,Color.BLACK);
        progressStrokeWidth = t.getDimensionPixelOffset(R.styleable.CircleProgressView_progress_stroke_width,10);
        number_text_size = t.getDimensionPixelOffset(R.styleable.CircleProgressView_number_text_size,10);
        desc_text_size = t.getDimensionPixelOffset(R.styleable.CircleProgressView_desc_text_size,10);
        isDrawCircle = t.getBoolean(R.styleable.CircleProgressView_is_draw_circle,true);
        t.recycle();
        oval = new RectF();
        numberPaint = new Paint();
        txtPaint = new Paint();
        circlePaint = new Paint();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        if(width!=height)
        {
            int min=Math.min(width, height);
            width=min;
            height=min;
        }
        if(isDrawCircle)
            drawCircleProgress(canvas,width,height);
        drawCusText(canvas,width,height);
    }

    private void drawCusText(Canvas canvas,int width,int height){
        numberPaint.setAntiAlias(true);
        numberPaint.setStrokeWidth(1);
        numberPaint.setStyle(Paint.Style.FILL);
        numberPaint.setColor(main_text_color);
        String number = String.valueOf(curProgress);
//        int textHeight = height / 3;
        numberPaint.setTextSize(number_text_size);
        int textWidth = (int) numberPaint.measureText(number,0,number.length());

        txtPaint.setAntiAlias(true); // 设置画笔为抗锯齿
        txtPaint.setStrokeWidth(1);
        txtPaint.setStyle(Paint.Style.FILL);
        txtPaint.setColor(desc_text_color);
//        int pernetHeight = textHeight/2;
        txtPaint.setTextSize(desc_text_size);
        int pernetWidth = (int) txtPaint.measureText(prentStr,0, prentStr.length());

        int txtWidth = width / 2 - textWidth / 2 - pernetWidth / 2;
        canvas.drawText(number,txtWidth , height / 3 +number_text_size/2, numberPaint);
        canvas.drawText(prentStr,txtWidth+textWidth,height / 3 +desc_text_size,txtPaint);

        int strWidth = (int) txtPaint.measureText(descStr, 0, descStr.length());
        canvas.drawText(descStr,width/2 - strWidth/2, height/2 + number_text_size/2 + desc_text_size/2,txtPaint);
    }

    private void drawCircleProgress(Canvas canvas,int width,int height){
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(progressStrokeWidth);
        circlePaint.setStyle(Paint.Style.STROKE);

        oval.left = progressStrokeWidth / 2; // 左上角x
        oval.top = progressStrokeWidth / 2; // 左上角y
        oval.right = width - progressStrokeWidth / 2; // 左下角x
        oval.bottom = height - progressStrokeWidth / 2; // 右下角y

        circlePaint.setColor(progress_down_color);
        canvas.drawArc(oval, -90, 360, false, circlePaint); // 绘制白色圆圈，即进度条背景
        circlePaint.setColor(progress_color);
        canvas.drawArc(oval, -90, ((float) curProgress / maxProgress) * 360, false, circlePaint); // 绘制进度圆弧，这里是蓝色
    }

    public void initProgress(){
        new Thread()
        {
            public void run()
            {
                int i=0;
                while(i<=progress)
                {
                    setProgressNotInUiThread(i);
                    i++;
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setIsShowCircle(boolean isShow){
        this.isDrawCircle = isShow;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        this.invalidate();
    }

    /**
     * 非ＵＩ线程调用
     */
    public void setProgressNotInUiThread(int progress) {
        this.curProgress = progress;
        this.postInvalidate();
    }
}
