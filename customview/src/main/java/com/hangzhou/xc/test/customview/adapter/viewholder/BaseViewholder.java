package com.hangzhou.xc.test.customview.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.adapter.viewholder
 * 日期   :   2017/5/10
 * 时间   ：  10:30
 * 描述   ：
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(Object data);


    private static int viewType;

    public static int ZoomHeadHolder = 1;


    public static void setViewType(int viewType) {
        BaseViewHolder.viewType = viewType;
    }

    public static BaseViewHolder getViewHolder(View view) {
        switch (viewType) {
            case 1:
                return new ZoomHeadHolder(view);
            default:
                return new ZoomHeadHolder(view);
        }
    }

    public static int getLayoutRes() {
        switch (viewType) {
            case 1:
                return 1;
            default:
                return 1;
        }
    }


}
