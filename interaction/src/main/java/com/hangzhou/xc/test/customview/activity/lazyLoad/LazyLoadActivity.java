package com.hangzhou.xc.test.customview.activity.lazyLoad;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.hangzhou.xc.test.customview.BaseActivity;
import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.adapter.BaseViewpagerAdapter;
import com.hangzhou.xc.test.customview.fragment.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.activity.lazyLoad
 * 日期   :   2017/5/25
 * 时间   ：  11:10
 * 描述   ：
 */

public class LazyLoadActivity extends BaseActivity {

    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("viewpager 懒加载 实现类");
//        toolbar.setNavigationIcon(R.drawable.ic_return);
        setSupportActionBar(toolbar);
        String titles[] = {"懒加载fragment 1", "懒加载fragment 2", "懒加载fragment 3"};
        list = new ArrayList();
        Bundle b = new Bundle();
        b.putInt("type", 1);
        LazyLoadFragment lazyLoadFragment;
        lazyLoadFragment = new LazyLoadFragment();
        lazyLoadFragment.setArguments(b);
        list.add(lazyLoadFragment);

        b = new Bundle();
        b.putInt("type", 2);
        lazyLoadFragment = new LazyLoadFragment();
        lazyLoadFragment.setArguments(b);
        list.add(lazyLoadFragment);

        b = new Bundle();
        b.putInt("type", 3);
        lazyLoadFragment = new LazyLoadFragment();
        lazyLoadFragment.setArguments(b);
        list.add(lazyLoadFragment);

        viewpager.setAdapter(new BaseViewpagerAdapter(getSupportFragmentManager(), list, titles));
        tablayout.setupWithViewPager(viewpager);
    }
}
