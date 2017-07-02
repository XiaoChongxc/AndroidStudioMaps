package com.example.admin_xc.basemodule.mvp.activity;

import android.support.v7.widget.RecyclerView;

import com.example.admin_xc.basemodule.base.BaseListActivity;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp.activity
 * 日期   :   2017/6/19
 * 时间   ：  16:50
 * 描述   ：
 */

public class ProductListActivity extends BaseListActivity{
//    https://dev.52wzb.com/wzb2/app3/product/v3.1.2/pagelist


    @Override
    public String setListTitle() {
        return "我的产品";
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return null;
    }

    @Override
    public void getData() {
    }

}
