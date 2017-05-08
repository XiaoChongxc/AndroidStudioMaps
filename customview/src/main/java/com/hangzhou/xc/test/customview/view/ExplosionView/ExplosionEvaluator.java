package com.hangzhou.xc.test.customview.view.ExplosionView;

import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Rect;

import com.hangzhou.xc.test.customview.util.L;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.view.ExplosionView
 * 日期   :   2017/05/08
 * 时间   ：  13:36
 * 描述   ：
 */
public class ExplosionEvaluator implements TypeEvaluator {

    Explosion explosion;


    public ExplosionEvaluator(Explosion explosion, Bitmap bit, Rect mRect) {
        this.explosion = explosion;
        explosion.init(bit, mRect);
    }

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        explosion.setFactor((((Explosion) endValue).getFactor() - ((Explosion) startValue).getFactor()) * fraction);
        L.e(explosion.getFactor() + "===============");
        return explosion;
    }
}
