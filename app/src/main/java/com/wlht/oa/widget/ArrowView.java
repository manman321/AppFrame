package com.wlht.oa.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class ArrowView extends View {


    public static class Arrow{

        public static int FINISH_COLOR = Color.parseColor("#4FBA4F");
        public static int WAITE_COLOR = Color.parseColor("#FF9001");
        public static int UNREACHABLE_COLOR = Color.parseColor("#7E7E7F");
        public int color = UNREACHABLE_COLOR;

        public Arrow(int sx, int sy, int ex, int ey) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }

        public Arrow(int sx, int sy, int ex, int ey,int color) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
            this.color = color;
        }

        public int sx,sy,ex,ey;

    }

    private Canvas mCanvas;
    private Paint mPaint =new Paint();

    private ArrayList<Arrow> mLists = new ArrayList<>();


    public ArrowView(Context context) {
        super(context);
        setPaintDefaultStyle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas =canvas;
        for (int i= 0; i < mLists.size();i++)
        {
            Arrow arrow = mLists.get(i);
            mPaint.setColor(arrow.color);
            drawAL(arrow.sx, arrow.sy, arrow.ex, arrow.ey);
        }

    }

//    public void init(int width,int height)
//    {
//        setLayoutParams(new FrameLayout.LayoutParams(width,height));
//    }

    public void addLine(Arrow arrow)
    {
        mLists.add(arrow);
    }

    public void addLine(int sx,int sy,int ex,int ey)
    {
        mLists.add(new Arrow(sx,sy,ex,ey));
    }

    public void addLine(int sx,int sy,int ex,int ey,int color)
    {
        mLists.add(new Arrow(sx,sy,ex,ey,color));
    }




    /**
     * 设置画笔默认样式
     */
    public void setPaintDefaultStyle(){
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
    }


    /**
     * 画圆
     * @param x x坐标
     * @param y	y坐标
     * @param radius	圆的半径
     */
    public void drawCircle(float x,float y,float radius){
        mCanvas.drawCircle(x, y, radius, mPaint);
        invalidate();
    }

    /**
     * 画一条直线
     * @param fromX 起点x坐标
     * @param fromY	起点Y坐标
     * @param toX	终点X坐标
     * @param toY	终点Y坐标
     */
    public void drawLine(float fromX,float fromY,float toX,float toY){
        Path linePath=new Path();
        linePath.moveTo(fromX, fromY);
        linePath.lineTo(toX, toY);
        linePath.close();
        mCanvas.drawPath(linePath, mPaint);
        invalidate();
    }


    /**
     * 画箭头
     * @param sx
     * @param sy
     * @param ex
     * @param ey
     */
    public void drawAL(int sx, int sy, int ex, int ey)
    {

        int scale = 3;
        double H = 8 * scale; // 箭头高度
        double L = 3.5 * scale; // 底边的一半
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // 箭头角度   
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度   
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点   
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点   
        double y_4 = ey - arrXY_2[1];
        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        // 画线   
        mCanvas.drawLine(sx, sy, ex, ey, mPaint);
        mCanvas.drawLine(ex, ey, x3, y3, mPaint);
        mCanvas.drawLine(ex, ey, x4, y4, mPaint);

        float angle = (int)Math.atan((ey-ey)/(ex-sx));



//        angle = 45;
//        mCanvas.rotate(angle, sx, sy);
//        mPaint.setTextSize(30);
//        mPaint.setAntiAlias(true);
//        mCanvas.drawText("wahaha", sx, sy, mPaint);
//        mCanvas.rotate(-angle,sx,sy);





//        mCanvas.drawCircle(sx,sy,16,mPaint);
//        Path triangle = new Path();
//        triangle.moveTo(ex, ey);
//        triangle.lineTo(x3, y3);
//        triangle.lineTo(x4, y4);
//        triangle.close();
//        mCanvas.drawPath(triangle,mPaint);

    }
    // 计算   
    public double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen)
    {
        double mathstr[] = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度   
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }


}