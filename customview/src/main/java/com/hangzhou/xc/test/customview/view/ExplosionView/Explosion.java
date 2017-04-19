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
 * 时间   ：  11:44
 * 描述   ： ExplosionLayout 作为一个 壳子， 具体的爆炸方式 由实现类去控制
 */
public interface Explosion {

    /***
     * 画出效果
     * @param canvas 画板
     * @param mPaint 画笔
     * @param bit   view 的 bitmap
     * @param factor 当前动画进度
     * @param rect view在屏幕中的位置
     */
    void draw(Canvas canvas, Paint mPaint, Bitmap bit, float factor, Rect rect);
}
