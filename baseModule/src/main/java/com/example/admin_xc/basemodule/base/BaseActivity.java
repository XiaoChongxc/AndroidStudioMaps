package com.example.admin_xc.basemodule.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.admin_xc.basemodule.R;
import com.example.admin_xc.basemodule.event.BaseEvent;
import com.example.admin_xc.basemodule.util.T;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.base
 * 日期   :   2017/6/1
 * 时间   ：  17:51
 * 描述   ：
 */

public class BaseActivity extends AppCompatActivity {
    /***
     * 上下文对象
     */
    public Context mContext;

    /**
     * 加载进度框
     */
    public SweetAlertDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    /**
     * 显示请求返回值 提示
     *
     * @param error
     */
    public void showError(String error) {
        T.showShort(mContext, error);
    }

    /***
     * 显示网络连接问题 提示
     */
    public void showNetworkError() {
        showError(mContext.getString(R.string.no_network_error));
    }

    /**
     * 判断网络是否可用
     *
     * @return
     */
    public boolean isNetworkAvailable() {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 显示加载对话框
     */
    public void showLoading() {
        if (!isFinishing()) {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.cancel();
            }
            loadingDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("Loading");
            loadingDialog.setCancelable(true);
            loadingDialog.show();
        }
    }

    /**
     * 关闭加载对话框
     */
    public void stopLoading() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
    }

    /**
     * 加载失败，重试对话框
     *
     * @param msg
     * @param listener
     */
    public void loadError(String msg, SweetAlertDialog.OnSweetClickListener listener) {
        if (loadingDialog != null) {
            loadingDialog.setTitleText(msg)
                    .setConfirmText("OK")
                    .setConfirmClickListener(listener)
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
            if (listener != null) {
                loadingDialog.setConfirmClickListener(listener).setConfirmText("重试");
            }
        }
    }


    /**
     * 登陆失效 ，去登陆页面
     */
    public void toLogin() {

    }

    /**
     * activity 点击返回事件处理
     *
     * @param v
     */
    public void back(View v) {
        finish();
    }

    /**
     * 设置text
     */
    public void setText(TextView tv, String text) {
        if (text == null)
            tv.setText("");
        else
            tv.setText(text);
    }

    public void getData() {
    }


    @Subscribe
    public void OnEvent(BaseEvent event) {
        if (BaseEvent.REFRESH_SELF.equals(event.getEventType())) {
            getData();
        } else if (BaseEvent.FINISH_SELF.equals(event.getEventType())) {   //关闭所有
            finish();
        }
    }

    /**
     * 初始化toolbar
     *
     * @param toolbar
     * @param title
     */
    public void initToolbar(Toolbar toolbar, String title) {
        initToolbar(toolbar, title, true);
    }

    /**
     * 初始化toolbar
     *
     * @param toolbar
     * @param title
     * @param hasReturn
     */
    public void initToolbar(Toolbar toolbar, String title, boolean hasReturn) {
        toolbar.setTitle(title);
        if (hasReturn)
            toolbar.setNavigationIcon(R.drawable.ic_return);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.ACTION_DOWN == android.R.id.home) {
            Log.d("TAG", "onKeyDown: 点击了返回按钮");
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
