package com.hangzhou.xc.test.customview.activity.elm;

import android.content.Intent;

import com.hangzhou.xc.test.customview.BaseListActivity;
import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.adapter.BaseRecyclerAdapter;
import com.hangzhou.xc.test.customview.adapter.OnItemClickListener;
import com.hangzhou.xc.test.customview.adapter.viewholder.BaseViewHolder;
import com.hangzhou.xc.test.customview.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.activity
 * 日期   :   2017/5/11
 * 时间   ：  17:36
 * 描述   ：
 */

public class ElmListActivity extends BaseListActivity {

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle("饿了么列表页面");
        toolbar.setNavigationIcon(R.drawable.ic_return);
        setSupportActionBar(toolbar);
    }

    @Override
    public List setData() {

        List<ItemModel> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ItemModel itemModel = new ItemModel("测试title", "测试描述测试描述测试描述测试描述测试描述测试描述测试描述测试描述", null, R.drawable.food_1 + "");
            data.add(itemModel);
        }
        return data;
    }

    @Override
    public void setAdapter() {
        adapter = new BaseRecyclerAdapter<ItemModel>(list, mContext, R.layout.item_list_image) {
            @Override
            public void convert(BaseViewHolder holder, ItemModel o) {
                holder.setText(R.id.tv_title, o.getTitle());
                holder.setText(R.id.tv_description, o.getDescription());
                holder.setImageResource(R.id.img_head, Integer.parseInt(o.getUri()));
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                intent = new Intent(mContext, ElmDetailActivity.class);
                startActivity(intent);
            }
        });
    }

}
