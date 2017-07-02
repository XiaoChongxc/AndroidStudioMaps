package com.example.admin_xc.basemodule.mvp.contract;

import com.example.admin_xc.basemodule.mvp.BasePresenter;
import com.example.admin_xc.basemodule.mvp.BaseView;

import java.util.Map;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp.contract
 * 日期   :   2017/6/19
 * 时间   ：  17:10
 * 描述   ：
 */

public interface BaseListContract {

    interface Presenter extends BasePresenter {
        void getData(String url, Map map);

    }

    interface View extends BaseView<Presenter> {

        void showError(String error, int type);

    }
}
