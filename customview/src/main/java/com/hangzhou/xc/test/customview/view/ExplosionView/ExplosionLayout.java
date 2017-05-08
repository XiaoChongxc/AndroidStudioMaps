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
import com.hangzhou.xc.test.customview.util.L;

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
    final String TAG = "ExplosionLayout";

    /***
     * 刷新频率
     */
    private int refresh_time = 100;
    /***
     * 动画时长
     */
    private int animation_time = 2000;
    /**
     * 当前动画效果的实现类
     */

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

    /**
     * 给view 一个监听，当被点击的时候，执行动画效果
     *
     * @param view
     */
    public void addLinstener(final View view) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                explosion(view);
            }
        });
    }

    private Explosion explosionType;

    /**
     * 设置一个动画效果
     *
     * @param explosion 动画效果的实现类
     */
    public void setExplosion(Explosion explosion) {
        this.explosionType = explosion;
        postInvalidate();
    }

    /**
     * 设置刷新频率
     *
     * @param refresh_time
     */
    public void setRefreshTime(int refresh_time) {
        this.refresh_time = refresh_time;
    }

    /**
     * 设置动画持续时间
     *
     * @param animation_time
     */
    public void setAnimationTime(int animation_time) {
        this.animation_time = animation_time;
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
            play(canvas, value);
        }
    }


    /***
     * 给 view 添加动画效果
     *
     * @param view
     */
    private void explosion(final View view) {
        //获取到传进来view 的位置信息

//        final ValueAnimator explosionAnimation = ValueAnimator.ofFloat(0, 1);
        //这里 作为可变参数 设置， 默认是 粒子状 ，后续添加 其他类别
        Rect mRect = new Rect();
        view.getGlobalVisibleRect(mRect);
        //因为状态栏的高度问题， 这里要进行一个高度的偏移
        mRect.offset(0, -DensityUtils.dp2px(view.getContext(), 21));
        Bitmap bit = createBitmapFromView(view);
        Explosion explosion;
        if (explosionType == null) {
            explosion = new Particle();
        }else{
            explosion = explosionType;
        }

        final ValueAnimator explosionAnimation = ValueAnimator.ofObject(new ExplosionEvaluator(explosion, bit, mRect),
                new Shredder(0), new Shredder(1));

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
        startThread();
    }

    private int type = 0;
    public static final int TYPE_PARTICLE = 0;
    public static final int TYPE_SHREDDER = 1;


    Thread mThread;
    boolean isStop = false;

    /***
     * 开启一个线程， 用来刷新当前view
     */
    private void startThread() {
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

    /***
     * 播放动画， 动态改变view 的状态
     *
     * @param canvas    画板
     * @param animation 动画
     */
    private void play(Canvas canvas, ValueAnimator animation) {
        if (!animation.isStarted()) { //动画结束时停止
            return;
        }
        L.e("play-----------------------------");
        Explosion explosion = (Explosion) animation.getAnimatedValue();
        explosion.draw(canvas, mPaint, explosion.getFactor());
        startThread();
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
