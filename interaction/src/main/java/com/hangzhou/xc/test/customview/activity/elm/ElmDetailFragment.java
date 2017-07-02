package com.hangzhou.xc.test.customview.activity.elm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.adapter.GridViewAdapter;
import com.hangzhou.xc.test.customview.model.ItemModel;
import com.hangzhou.xc.test.customview.view.CustomVideoView.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.activity.elm
 * 日期   :   2017/5/11
 * 时间   ：  17:50
 * 描述   ：
 */

public class ElmDetailFragment extends Fragment {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_description)
    TextView tvDescription;
    @Bind(R.id.gridview)
    NoScrollGridView gridview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elmdetail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridview.setAdapter(new GridViewAdapter(getlist(), getContext()));
    }


    private List getlist() {
        List<ItemModel> list = new ArrayList<>();
        ItemModel itemModel = new ItemModel("食物11111", "测试描述测试描述测试描述测试描述测试描述测试描述测试描述测试描述", null, R.drawable.food_1 + "");
        list.add(itemModel);
        itemModel = new ItemModel("食物11111", "测试描述测试描述测试描述测试描述测试描述测试描述测试描述测试描述", null, R.drawable.food_2 + "");
        list.add(itemModel);
        itemModel = new ItemModel("食物11111", "测试描述测试描述测试描述测试描述测试描述测试描述测试描述测试描述", null, R.drawable.food_3 + "");
        list.add(itemModel);
        itemModel = new ItemModel("食物11111", "测试描述测试描述测试描述测试描述测试描述测试描述测试描述测试描述", null, R.drawable.food_4 + "");
        list.add(itemModel);
        itemModel = new ItemModel("食物11111", "测试描述测试描述测试描述测试描述测试描述测试描述测试描述测试描述", null, R.drawable.food_5 + "");
        list.add(itemModel);
        itemModel = new ItemModel("食物11111", "测试描述测试描述测试描述测试描述测试描述测试描述测试描述测试描述", null, R.drawable.food_3 + "");
        list.add(itemModel);
        itemModel = new ItemModel("食物11111", "测试描述测试描述测试描述测试描述测试描述测试描述测试描述测试描述", null, R.drawable.food_1 + "");
        list.add(itemModel);
        itemModel = new ItemModel("食物11111", "测试描述测试描述测试描述测试描述测试描述测试描述测试描述测试描述", null, R.drawable.food_2 + "");
        list.add(itemModel);
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
