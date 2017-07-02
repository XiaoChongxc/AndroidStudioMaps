package com.hangzhou.xc.test.customview.activity;

import com.hangzhou.xc.test.customview.BaseListActivity;
import com.hangzhou.xc.test.customview.adapter.OnItemClickListener;
import com.hangzhou.xc.test.customview.model.ItemModel;

import java.util.List;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.activity
 * 日期   :   2017/5/5
 * 时间   ：  15:52
 * 描述   ：播放音效的 activity
 */

public class PlaySoundActivity extends BaseListActivity {

    @Override
    public void initToolbar() {
        super.initToolbar();
    }

    @Override
    public void onInitComplete() {
        super.onInitComplete();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                
            }
        });
    }

    private void playSound(ItemModel model) {
//        getResources().openRawResource(R.raw.vdo_splash);

    }

    @Override
    public List setData() {


        return super.setData();
    }


}
