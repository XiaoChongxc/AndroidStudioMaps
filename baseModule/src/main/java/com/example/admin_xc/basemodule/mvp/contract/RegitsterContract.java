package com.example.admin_xc.basemodule.mvp.contract;

import com.example.admin_xc.basemodule.mvp.BasePresenter;
import com.example.admin_xc.basemodule.mvp.BaseView;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp.contract
 * 日期   :   2017/6/6
 * 时间   ：  16:23
 * 描述   ：
 */

public interface RegitsterContract {
    interface Presenter extends BasePresenter {
        void onRegister();

        void onSendSms();

        void varifySms();
    }

    interface View extends BaseView<Presenter> {
        String getSmsCode();

        String getUserName();

        String getPwd();

        void setSendText();

        void setSurButton();

    }

}
