package com.example.admin_xc.basemodule.mvp.model;

import com.example.admin_xc.basemodule.base.BaseSubscriber;
import com.example.admin_xc.basemodule.http.HttpAPI;
import com.example.admin_xc.basemodule.mvp.BaseModel;

import java.util.Map;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp
 * 日期   :   2017/6/19
 * 时间   ：  17:35
 * 描述   ：
 */

public class BaseListModel extends BaseModel {

    public void getData(String url, Map params, BaseSubscriber subscriber) {
        subscription = HttpAPI.getService().getProductList(url, params)
                .compose(Rx_Transformer())
                .subscribe(subscriber);
    }
}
