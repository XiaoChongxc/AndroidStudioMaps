package com.example.admin_xc.basemodule.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.customview
 * 日期   :   2017/6/16
 * 时间   ：  15:01
 * 描述   ：
 */

public class MyScrollView extends ScrollView {

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("TAG", "MyScrollView onTouchEvent: " + Const.hanlderAction(ev.getAction()) + view.isChildTouch);
//        if (view.isChildTouch) return false;
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("TAG", "MyScrollView dispatchTouchEvent: " + Const.hanlderAction(ev.getAction()) + view.isChildTouch);
//        if (view.isChildTouch)
//            return true;

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("TAG", "MyScrollView onInterceptTouchEvent: " + Const.hanlderAction(ev.getAction()));
        if (view.isChildTouch) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    LinePathView view;

    public void setView(LinePathView view) {
        this.view = view;
    }
}
