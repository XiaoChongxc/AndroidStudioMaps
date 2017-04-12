package com.hangzhou.xc.test.customview.view.CustomVideoView;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.hangzhou.xc.test.customview.BaseActivity;
import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.util.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.view.CustomVideoView
 * 日期   :   2017/04/12
 * 时间   ：  15:12
 * 描述   ： 欢迎页面 播放一个视频
 * 参考：https://github.com/linglongxin24/WelcomeVideoPager
 */
public class VideoWelcomeActivity extends BaseActivity {

    @Bind(R.id.videoview)
    CustomVideoView videoview;
    @Bind(R.id.btn_entry)
    Button btnEntry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_welcome);
        ButterKnife.bind(this);
        loadVideo();
    }

    public void loadVideo() {

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vdo_splash);
        videoview.playVideo(uri, false);
        //播放结束了， 显示按钮
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//                AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
//                alphaAnimation.setDuration(500);
//                alphaAnimation.setFillAfter(true);
//                btnEntry.setAnimation(alphaAnimation);
                btnEntry.setVisibility(View.VISIBLE);
//                btnEntry.startAnimation(alphaAnimation);

            }
        });
    }


    @OnClick(R.id.btn_entry)
    void entry() {
        T.showShort(mContext, "立即体验");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoview != null) {
            videoview.stopPlayback();
        }
    }
}
