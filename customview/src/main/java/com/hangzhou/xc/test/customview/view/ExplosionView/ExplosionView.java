package com.hangzhou.xc.test.customview.view.ExplosionView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.view
 * 日期   :   2017/04/07
 * 时间   ：  14:09
 * 描述   ： 粒子爆碎效果
 */

public class ExplosionView extends View {

    public ExplosionView(Context context) {
        super(context);
    }

    public ExplosionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExplosionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Bitmap mBitmap;
    Rect mRect;
    Paint mPaint;

    private void init() {
        attach2Activity((Activity) getContext());
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int left = mRect.left;
        int top = mRect.top;

        canvas.drawBitmap(mBitmap, left, top, mPaint);

    }



    public void addLinstener(final View view){

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                explosion(view);
            }
        });

    }



    /***
     * 爆炸效果
     * @param view
     */
    private void explosion(View view) {
        //获取到传进来view 的位置信息
        mRect = new Rect();
        view.getGlobalVisibleRect(mRect);

        mBitmap = createBitmapFromView(view);
    }
    /**
     * 获取view 的bitmap图像
     */
    private Bitmap createBitmapFromView(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bit = view.getDrawingCache();

        return bit;
    }


    /**
     * 给Activity加上全屏覆盖的ExplosionField
     */
    private void attach2Activity(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this, lp);
    }

}
