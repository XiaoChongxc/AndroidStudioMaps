package com.hangzhou.xc.test.customview.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.adapter.viewholder
 * 日期   :   2017/5/5
 * 时间   ：  16:06
 * 描述   ：
 */

public class BaseViewholder extends RecyclerView.ViewHolder {

    private View itemView;

    public BaseViewholder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    /**
     * 子类重写方法， 设置数据
     *
     * @param data
     */
    public void setData(Object data) {

    }

    public View getItemView() {
        return itemView;
    }

}
