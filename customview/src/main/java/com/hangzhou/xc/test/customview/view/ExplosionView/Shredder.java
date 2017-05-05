package com.hangzhou.xc.test.customview.view.ExplosionView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.view.ExplosionView
 * 日期   :   2017/04/19
 * 时间   ：  13:41
 * 描述   ： 碎纸机  样式
 * 把bitmap变成横向的一小段 ，从上往下掉落
 */
public class Shredder implements Explosion {
    private final int DEFAULT_HEIGHT = 20;
    private final int slice_height = DEFAULT_HEIGHT;
    Slice[] slices;
    int speed = 10;

    @Override
    public void draw(Canvas canvas, Paint mPaint, Bitmap bit, float factor, Rect rect) {
        mPaint.setAlpha(255);
        initSlices(bit);
//        for (int i = 0; i < slices.length; i++) {
//            Slice slice = slices[i];
//            canvas.drawBitmap(slice.getBitmap(), rect.left + slice.getX(), rect.top + slice.getY() + factor * speed * (i+1) * slice_height, mPaint);
//        }

        /**
         * 依次 下落， 自由落体式， 加速度
         */
        for (int i = 0; i < slices.length; i++) {
            Slice slice = slices[i];
            int y = (int) (factor * speed * slice_height * (i + 1) ) - (slices.length - i - 1) * slice_height;
            if (y <= 0) y = 0;
            canvas.drawBitmap(slice.getBitmap(), rect.left + slice.getX(), rect.top + slice.getY() + y, mPaint);
        }


    }


    private void initSlices(Bitmap bit) {
        int size = (int) Math.ceil(bit.getHeight() * 1f / slice_height);
        slices = new Slice[size];
        for (int i = 0; i < size; i++) {
            slices[i] = new Slice();
            int y = i * slice_height;
            if (y > bit.getHeight() - slice_height) y = bit.getHeight() - slice_height;
            slices[i].setBitmap(Bitmap.createBitmap(bit, 0, y, bit.getWidth(), slice_height));
            slices[i].setX(0);
            slices[i].setY(y);
        }
    }


    public class Slice {
        private Bitmap bitmap;
        private int x;
        private int y;

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
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
    }
}
