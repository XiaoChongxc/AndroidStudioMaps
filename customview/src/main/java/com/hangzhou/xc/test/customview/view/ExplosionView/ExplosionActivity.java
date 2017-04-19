package com.hangzhou.xc.test.customview.view.ExplosionView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.hangzhou.xc.test.customview.BaseActivity;
import com.hangzhou.xc.test.customview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.view.ExplosionView
 * 日期   :   2017/04/17
 * 时间   ：  10:35
 * 描述   ：
 */
public class ExplosionActivity extends BaseActivity {

    @Bind(R.id.img_qq)
    ImageView imgQq;
    @Bind(R.id.img_wx)
    ImageView imgWx;
    @Bind(R.id.img_wb)
    ImageView imgWb;
    @Bind(R.id.img_tb)
    ImageView imgTb;
    @Bind(R.id.img_baidu_map)
    ImageView imgBaiduMap;
    @Bind(R.id.img_gaode_map)
    ImageView imgGaodeMap;
    @Bind(R.id.img_jd)
    ImageView imgJd;
    @Bind(R.id.img_qq_music)
    ImageView imgQqMusic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explosion);
        ButterKnife.bind(this);
        ExplosionLayout explosionLayout =new ExplosionLayout(this);

        explosionLayout.addLinstener(imgQq);
        explosionLayout.addLinstener(imgWx);
        explosionLayout.addLinstener(imgWb);
        explosionLayout.addLinstener(imgTb);
        explosionLayout.addLinstener(imgBaiduMap);
        explosionLayout.addLinstener(imgGaodeMap);
        explosionLayout.addLinstener(imgJd);
        explosionLayout.addLinstener(imgQqMusic);


    }
}
