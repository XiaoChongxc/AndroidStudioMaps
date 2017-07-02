package com.hangzhou.xc.test.customview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.adapter
 * 日期   :   2017/5/11
 * 时间   ：  17:57
 * 描述   ：
 */

public class BaseViewpagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    String[] titles;

    public BaseViewpagerAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    public BaseViewpagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles == null || titles.length == 0) return "";
        return titles[position];
    }
}
