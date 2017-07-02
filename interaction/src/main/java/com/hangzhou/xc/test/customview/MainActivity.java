package com.hangzhou.xc.test.customview;

import android.content.Intent;

import com.hangzhou.xc.test.customview.activity.elm.ElmListActivity;
import com.hangzhou.xc.test.customview.activity.lazyLoad.LazyLoadActivity;
import com.hangzhou.xc.test.customview.adapter.BaseRecyclerAdapter;
import com.hangzhou.xc.test.customview.adapter.OnItemClickListener;
import com.hangzhou.xc.test.customview.adapter.viewholder.BaseViewHolder;
import com.hangzhou.xc.test.customview.model.ItemModel;
import com.hangzhou.xc.test.customview.util.L;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseListActivity {

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle("炫酷交互demo合集");
        setSupportActionBar(toolbar);
    }

    @Override
    public List setData() {
        ItemModel itemModel;
        List<ItemModel> data = new ArrayList<>();
        itemModel = new ItemModel("仿饿了么列表到详情页面的交互", "仿饿了么列表到详情页面的交互，切换比较炫酷，", ElmListActivity.class);
        data.add(itemModel);
        itemModel = new ItemModel("viewpager+fragment懒加载","Viewpager+Fragment懒加载，用户页面可见的时候再去刷新请求，节约性能", LazyLoadActivity.class);
        data.add(itemModel);
        return data;
    }

    @Override
    public void setAdapter() {
        L.e("=====setAdapter=========" + list.size());
        adapter = new BaseRecyclerAdapter<ItemModel>(list, mContext, R.layout.item_list_view) {
            @Override
            public void convert(BaseViewHolder holder, ItemModel o) {
                holder.setText(R.id.tv_title, o.getTitle());
                holder.setText(R.id.tv_description, o.getDescription());
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                ItemModel itemModel = (ItemModel) list.get(position);
                Intent intent = new Intent(mContext, itemModel.getClazz());
                startActivity(intent);
            }
        });
    }


}
