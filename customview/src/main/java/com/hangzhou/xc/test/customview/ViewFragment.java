package com.hangzhou.xc.test.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hangzhou.xc.test.customview.adapter.ViewFragmentAdapter;
import com.hangzhou.xc.test.customview.model.ItemModel;
import com.hangzhou.xc.test.customview.view.ExplosionView.ExplosionActivity;
import com.hangzhou.xc.test.customview.view.ZoomHead.ZoomHeadActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview
 * 日期   :   2017/04/07
 * 时间   ：  16:20
 * 描述   ：
 */

public class ViewFragment extends Fragment {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<ItemModel> list = new ArrayList<>();
        list.add(new ItemModel("粒子爆炸view", "让任何view ，点击的时候变成像素粒子，碎裂到屏幕底部，动画结束后重新展示", ExplosionActivity.class));
        list.add(new ItemModel("仿饿了么交互","饿了么  列表到 viewpager 再到详情的交互， 比较炫酷" ,ZoomHeadActivity.class));

        ViewFragmentAdapter adapter = new ViewFragmentAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickLinstener(new ViewFragmentAdapter.OnItemClickLinstener() {
            @Override
            public void onClick(List data, int position) {
                ItemModel item = (ItemModel) data.get(position);
                Intent intent = new Intent(getActivity(), item.getClazz());
                startActivity(intent);
            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
