package com.hangzhou.xc.test.customview.activity.elm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.hangzhou.xc.test.customview.BaseActivity;
import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.adapter.BaseViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.activity.elm
 * 日期   :   2017/5/11
 * 时间   ：  17:42
 * 描述   ：
 */

public class ElmDetailActivity extends BaseActivity {
    @Bind(R.id.viewpager)
    ElmViewpager viewpager;
    List<Fragment> fragments;
    @Bind(R.id.tv_tips)
    TextView tvTips;
    @Bind(R.id.rl_layout)
    RelativeLayout rlLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new ElmDetailFragment());
        fragments.add(new ElmDetailFragment());
        fragments.add(new ElmDetailFragment());
        fragments.add(new ElmDetailFragment());
        fragments.add(new ElmDetailFragment());
        fragments.add(new ElmDetailFragment());
        fragments.add(new ElmDetailFragment());
        viewpager.setAdapter(new BaseViewpagerAdapter(getSupportFragmentManager(), fragments));
        viewpager.setPageTransformer(false, new ScaleInOutTransformer());
        viewpager.setOffscreenPageLimit(3);

        viewpager.setMaxHeight(rlLayout.getHeight());
        viewpager.setMaxWidth(rlLayout.getWidth());
    }


}
