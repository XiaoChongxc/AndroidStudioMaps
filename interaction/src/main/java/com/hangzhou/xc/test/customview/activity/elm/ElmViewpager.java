package com.hangzhou.xc.test.customview.activity.elm;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.hangzhou.xc.test.customview.util.T;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.activity.elm
 * 日期   :   2017/5/15
 * 时间   ：  11:02
 * 描述   ：
 */

public class ElmViewpager extends ViewPager {


    private int maxWidth;
    private int maxHeight;
    private int minWidth;
    private int minHeight;

    private boolean firstGetLayout = false;


    public ElmViewpager(Context context) {
        super(context);
    }

    public ElmViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    RelativeLayout.LayoutParams params;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = getWidth();
        int h = getHeight();
        if (w != 0 && h != 0 && !firstGetLayout) {
            minHeight = h;
            minWidth = w;
            params = (RelativeLayout.LayoutParams) getLayoutParams();
            firstGetLayout = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    private float startX;
    private float startY;
    private boolean hasGetOrientation = false;
    private final int x_limit = 40;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                float x = ev.getX();
                float y = ev.getY();
                float xDiff = Math.abs(startX - x);
                float yDiff = Math.abs(startY - y);
                if (hasGetOrientation || xDiff < yDiff && xDiff < x_limit) {  //水平移动小于 竖直移动
                    hasGetOrientation = true;
                    if (startY - y > 0) {  //上滑变大
                        params.width += (startY - y) / 3;
                        params.height += (startY - y) / 2;
                        if (params.width < minWidth) params.width = minWidth;
                        if (params.width > maxWidth) params.width = maxWidth;
                        if (params.width < minHeight) params.height = minHeight;
                        if (params.width > maxHeight) params.height = maxHeight;


                    } else {  //下滑 一定距离  关闭
                        if (yDiff > 100) {
                            T.showShort(getContext(), "下滑关闭");
                        }
                    }
                    return false;
                }
                break;

            case MotionEvent.ACTION_UP:
                hasGetOrientation = false;
                break;
        }


        return super.onTouchEvent(ev);
    }


    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }
}
