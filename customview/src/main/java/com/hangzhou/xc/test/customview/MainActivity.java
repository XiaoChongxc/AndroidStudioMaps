package com.hangzhou.xc.test.customview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.hangzhou.xc.test.customview.adapter.MainViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle("自定义demo合集");
        setSupportActionBar(toolbar);

        String titles[]={"自定义view","自定义ViewGroup"};
        viewpager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(),titles));

        tablayout.setupWithViewPager(viewpager);



    }
}
