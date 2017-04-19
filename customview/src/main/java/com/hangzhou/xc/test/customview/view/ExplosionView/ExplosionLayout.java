package com.hangzhou.xc.test.customview.view.ExplosionView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.hangzhou.xc.test.customview.util.DensityUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.view
 * 日期   :   2017/04/07
 * 时间   ：  14:09
 * 描述   ： 执行动画效果的壳子，  给 view 添加点击事件，然后在view被点击的时候获取到view的bitmap，进而对bitmap做一些动画操作
 */

public class ExplosionLayout extends View {
    final String TAG = "ExplosionView";

    /***
     * 刷新频率
     */
    private int refresh_time = 100;
    /***
     * 动画时长
     */
    private int animation_time = 2000;

    Map<View, ValueAnimator> explostionSet;
    Paint mPaint;
    Iterator it;

    public ExplosionLayout(Context context) {
        super(context);
        init();
    }

    public ExplosionLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExplosionLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        attach2Activity((Activity) getContext());
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        explostionSet = new HashMap<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        it = explostionSet.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            View view = (View) entry.getKey();
            ValueAnimator value = (ValueAnimator) entry.getValue();
            play(canvas, view, value);
        }
    }

    public void addLinstener(final View view) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                explosion(view);
            }
        });
    }


    /***
     * 爆炸效果
     *
     * @param view
     */
    private void explosion(final View view) {
        //获取到传进来view 的位置信息
        final ValueAnimator explosionAnimation = ValueAnimator.ofFloat(0, 1);
        explosionAnimation.setDuration(animation_time);
        //不能重复添加
        if (explostionSet.containsKey(view)) {
            return;
        }
        explostionSet.put(view, explosionAnimation);
        explosionAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始的时候 需要隐藏原来的view
                view.animate().alpha(0).setDuration(150).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束   显示view  并且 移除动画
                view.animate().alpha(1f).setDuration(150).start();
                explostionSet.remove(view);
            }
        });
        explosionAnimation.start();
        startthread();
    }

    Thread mThread;
    boolean isStop = false;

    public void startthread() {
        if (mThread == null) {
            isStop = false;
            mThread = new Thread() {
                @Override
                public void run() {
                    while (!isStop) {
                        if (explostionSet.size() != 0) {
                            try {
                                sleep(refresh_time);
                                postInvalidate();
                            } catch (Exception e) {
                                Log.d(TAG, "run: Exception" + e.toString());
                            }
                        } else {
                            isStop = true;
                            mThread = null;
                        }
                    }
                }
            };
            mThread.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isStop = true;
        mThread = null;
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


    public void play(Canvas canvas, View view, ValueAnimator animation) {
        if (!animation.isStarted()) { //动画结束时停止
            return;
        }
        //这里 作为可变参数 设置， 默认是 粒子状 ，后续添加 其他类别
        Particle p = new Particle();
        Rect mRect = new Rect();
        view.getGlobalVisibleRect(mRect);
        //因为状态栏的高度问题， 这里要进行一个高度的偏移
        mRect.offset(0, -DensityUtils.dp2px(view.getContext(), 21));
        Bitmap bit = createBitmapFromView(view);
        p.draw(canvas, mPaint, bit, (Float) animation.getAnimatedValue(), mRect);
        startthread();
    }


    /**
     * 获取view 的bitmap图像
     */
    private Bitmap createBitmapFromView(View view) {
        //获取view 的bitmap  方法1
        view.setDrawingCacheEnabled(true);
        Bitmap bit = view.getDrawingCache();
        return bit;
        //获取view的bitmap 方法2
//        Bitmap bitmap=Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas=new Canvas(bitmap);
//        view.draw(canvas);
//        return bitmap;
    }
}
