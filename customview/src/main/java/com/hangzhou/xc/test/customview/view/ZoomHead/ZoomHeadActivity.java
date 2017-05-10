package com.hangzhou.xc.test.customview.view.ZoomHead;

import com.hangzhou.xc.test.customview.BaseListActivity;
import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.adapter.BaseListAdapter;
import com.hangzhou.xc.test.customview.adapter.viewholder.BaseViewHolder;
import com.hangzhou.xc.test.customview.model.ItemModel;

import java.util.List;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.view.ZoomHead
 * 日期   :   2017/5/9
 * 时间   ：  17:03
 * 描述   ：防饿了么 的viewpager 到详情页面
 */

public class ZoomHeadActivity extends BaseListActivity {

    @Override
    public List setData() {
        for (int i = 0; i < 20; i++) {
            ItemModel item = new ItemModel("我是菜品1", "我是菜品", null);
            item.setUri(R.drawable.food_1 + "");
            list.add(item);
        }
        return super.setData();
    }

    @Override
    public void setAdapter() {
        adapter = new BaseListAdapter(list, mContext, BaseViewHolder.ZoomHeadHolder);
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle("饿了么列表去详情");
        toolbar.setNavigationIcon(R.drawable.ic_return);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onInitComplete() {
        super.onInitComplete();
        adapter.setOnItemClickListener(new BaseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }
}
