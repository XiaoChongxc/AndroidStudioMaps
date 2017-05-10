package com.hangzhou.xc.test.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hangzhou.xc.test.customview.BaseActivity;
import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.util.T;
import com.hangzhou.xc.test.customview.view.MyLetterListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.activity
 * 日期   :   2017/5/9
 * 时间   ：  14:55
 * 描述   ：
 */

public class TextActivity extends BaseActivity {
    @Bind(R.id.letter)
    MyLetterListView letter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);
        letter.setOnTouchingLetterChangedListener(new MyLetterListView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                T.showShort(mContext, s);
            }
        });

    }
}
