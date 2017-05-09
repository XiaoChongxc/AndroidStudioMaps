package com.hangzhou.xc.test.customview.view.ExplosionView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.view.ExplosionView
 * 日期   :   2017/04/17
 * 时间   ：  17:29
 * 描述   ： 把 bitmap变成像素点
 */
public class Particle extends Explosion {

    public static final int DEFAULT_SIZE = 10;
    private final int offset = 30;


    Point[][] mPoints;

    public Particle() {

    }

    public Particle(float factor) {
        super(factor);
    }

    @Override
    void init(Bitmap bit, Rect mRect) {
        this.bit = bit;
        this.mRect = mRect;
        initParticle(bit);
    }

    @Override
    public void draw(Canvas canvas, Paint mPaint, float factor) {
        //画出像素点
        for (Point[] particles : mPoints) {
            for (Point p : particles) {
//                //根据动画 ， 对像素点做偏移缩小
                float progress = 1 - factor;
                advance(p, progress);
                mPaint.setColor(p.getColor());
//                mPaint.setAlpha((int) (progress * 255));//只是这样设置，透明色会显示为黑色
                int alpha = (int) (Color.alpha(p.getColor()) * progress);
                mPaint.setAlpha(alpha); //这样透明颜色就不是黑色了
                canvas.drawCircle(p.getX() + mRect.left, p.getY() + mRect.top, p.getSize(), mPaint);
            }
        }
    }


    /***
     * 初始化像素点
     */
    private void initParticle(Bitmap bit) {
        int radius = Particle.DEFAULT_SIZE;
        int num_x = bit.getWidth() / radius;
        int num_y = bit.getHeight() / radius;
        mPoints = new Point[num_x][num_y];
        for (int i = 0; i < num_x; i++) {
            for (int j = 0; j < num_y; j++) {
                mPoints[i][j] = new Point();
                int x = i * radius + mPoints[i][j].getSize() / 2;
                int y = j * radius + mPoints[i][j].getSize() / 2;
                mPoints[i][j].setColor(bit.getPixel(x, y));
                mPoints[i][j].setX(x);
                mPoints[i][j].setY(y);
                mPoints[i][j].setmRect(mRect);
            }
        }
    }

    /**
     * 根据进度， 调整 像素点的状态
     *
     * @param factor
     */
    private void advance(Point p, float factor) {
        //调整大小
        p.setSize((int) (DEFAULT_SIZE * factor));
        //左右随机偏移
        p.setX((int) (p.getX() + (0.5f - new Random().nextFloat()) * offset));
        //设置 Y
        p.setY((int) (mRect.bottom * (1 - factor) + p.getY()) + new Random().nextInt(mRect.height() / 2));
    }


    public class Point {
        /**
         * 在父控件的位置
         */
        private Rect mRect;
        /**
         * 像素点的大小
         */
        private int size = DEFAULT_SIZE;
        /**
         * x坐标
         */
        private int x;
        /**
         * Y坐标
         */
        private int y;
        /**
         * 像素点的颜色
         */
        private int color;

        public Rect getmRect() {
            return mRect;
        }

        public void setmRect(Rect mRect) {
            this.mRect = mRect;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

    }

}
