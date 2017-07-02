package com.hangzhou.xc.test.customview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hangzhou.xc.test.customview.util.L;

import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.activity.lazyLoad
 * 日期   :   2017/5/25
 * 时间   ：  11:10
 * 描述   ：
 */

public abstract class BaseLazyLoadFragment extends Fragment {

    /**
     * 视图初始化
     */
    private boolean isViewInitiated;
    /**
     * 用户可见
     */
    private boolean isUserVisible;
    /**
     * 数据初始化
     */
    private boolean isDataInitiated;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setContentView(), container, false);
        L.e("onCreateView:=======has init data:" + isDataInitiated);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        initView();
        prepareFetchData();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        int type = getArguments().getInt("type", 0);
        L.e("setUserVisibleHint:======" + type + "======" + isVisibleToUser);
        this.isUserVisible = isVisibleToUser;
        prepareFetchData();
    }

    /**
     * 子类重写
     * 设置fragment的view
     *
     * @return
     */
    public abstract int setContentView();

    /***
     * 子类重写
     * 初始化页面元素
     */
    public abstract void initView();

    /***
     * 是否 强制 更新数据
     */
    public boolean prepareFetchData(boolean forceUpdate) {
        if (isViewInitiated && isUserVisible && (!isDataInitiated || forceUpdate)) {
            loadData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }


    private void prepareFetchData() {
        prepareFetchData(false);
    }

    /**
     * 子类重写
     * fragment 加载数据
     */
    public abstract void loadData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
