package com.hangzhou.xc.test.customview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hangzhou.xc.test.customview.ViewFragment;
import com.hangzhou.xc.test.customview.ViewGroupFragment;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.Adapter
 * 日期   :   2017/04/07
 * 时间   ：  16:18
 * 描述   ：
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public MainViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                new ViewFragment();
                break;
            case 1:
                new ViewGroupFragment();
                break;
        }
        return     new ViewFragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
