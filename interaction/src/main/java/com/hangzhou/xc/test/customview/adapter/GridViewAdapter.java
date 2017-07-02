package com.hangzhou.xc.test.customview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.model.ItemModel;

import java.util.List;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.adapter
 * 日期   :   2017/5/14
 * 时间   ：  10:57
 * 描述   ：
 */

public class GridViewAdapter extends BaseAdapter {

    private List<ItemModel> data;
    private Context ctx;

    public GridViewAdapter(List<ItemModel> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ItemModel getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_grid, viewGroup, false);
        ImageView img = (ImageView) v.findViewById(R.id.img_food);
        TextView tvName = (TextView) v.findViewById(R.id.tv_name);
        ItemModel item = getItem(i);
        img.setImageResource(Integer.parseInt(item.getUri()));
        tvName.setText(item.getTitle());
        return v;
    }
}
