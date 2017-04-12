package com.hangzhou.xc.test.customview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview
 * 日期   :   2017/04/07
 * 时间   ：  16:06
 * 描述   ：
 */

public class BaseActivity extends AppCompatActivity {

    public Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.R.id.home) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
