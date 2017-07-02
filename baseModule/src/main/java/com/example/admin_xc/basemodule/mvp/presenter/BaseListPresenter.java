package com.example.admin_xc.basemodule.mvp.presenter;

import com.example.admin_xc.basemodule.base.BaseLisenter;
import com.example.admin_xc.basemodule.base.BaseSubscriber;
import com.example.admin_xc.basemodule.mvp.contract.BaseListContract;
import com.example.admin_xc.basemodule.mvp.model.BaseListModel;

import java.util.Map;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp.presenter
 * 日期   :   2017/6/19
 * 时间   ：  17:25
 * 描述   ：
 */

public class BaseListPresenter implements BaseListContract.Presenter {

    BaseListContract.View view;
    BaseListModel baseListModel;

    public BaseListPresenter(BaseListContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.view.setTitle();
        baseListModel = new BaseListModel();
    }

    @Override
    public void getData(String url, Map map) {
        baseListModel.getData(url, map, new BaseSubscriber(new BaseLisenter() {
            @Override
            public void onCompleted(int requestType) {
                view.stopLoading();
            }

            @Override
            public void onError(int requestType, int code, String e) {

            }

            @Override
            public void onNext(int requestType, Object o) {

            }
        }));
    }
}
