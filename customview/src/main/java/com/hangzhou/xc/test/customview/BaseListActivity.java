package com.hangzhou.xc.test.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hangzhou.xc.test.customview.adapter.BaseListAdapter;
import com.hangzhou.xc.test.customview.model.ItemModel;
import com.hangzhou.xc.test.customview.view.ExplosionView.ExplosionActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview
 * 日期   :   2017/5/5
 * 时间   ：  15:53
 * 描述   ：
 */

public abstract class BaseListActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;

    public BaseListAdapter adapter;
    public List list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        ButterKnife.bind(this);

        initToolbar();

        list = setData();
        adapter = new BaseListAdapter(list, mContext, R.layout.item_list_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        oninitComplete();
    }


    public void oninitComplete() {

    }

    /**
     * 设置数据
     *
     * @return
     */
    public List setData() {
        List<ItemModel> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new ItemModel("粒子爆炸view", "让任何view ，点击的时候变成像素粒子，碎裂到屏幕底部，动画结束后重新展示", ExplosionActivity.class));
        }
        return data;
    }

    public void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }


    /**
     * 设置layoutmanager
     *
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.postInvalidate();
    }


}
