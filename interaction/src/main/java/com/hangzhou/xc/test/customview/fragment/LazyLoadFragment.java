package com.hangzhou.xc.test.customview.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.util.L;
import com.hangzhou.xc.test.customview.util.T;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.fragment
 * 日期   :   2017/5/25
 * 时间   ：  16:03
 * 描述   ：
 */

public class LazyLoadFragment extends BaseLazyLoadFragment {


    @Bind(R.id.load_progress)
    ProgressBar loadProgress;
    @Bind(R.id.btn_refresh)
    Button btnRefresh;
    int type;


    @Override
    public int setContentView() {
        type = getArguments().getInt("type", 0);
        L.e("setContentView=============="+type);
        return R.layout.fragment_lazy_load;
    }

    @Override
    public void initView() {
        L.e("onActivityCreated=============="+type);
    }

    @OnClick(R.id.btn_refresh)
    void refresh() {
        prepareFetchData(true);
    }

    @Override
    public void loadData() {
        L.e("loadData=============="+type);
        loadProgress.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadProgress.setVisibility(View.INVISIBLE);
                T.showShort(getContext(), "数据加载完成！");
            }
        }, 1000);
    }

}
