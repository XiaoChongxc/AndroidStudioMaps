package com.hangzhou.xc.test.customview.view.CustomVideoView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.view.CustomVideoView
 * 日期   :   2017/5/14
 * 时间   ：  10:50
 * 描述   ：
 */

public class NoScrollGridView  extends GridView{

    public NoScrollGridView(Context context) {
        super(context);
    }

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }
}
