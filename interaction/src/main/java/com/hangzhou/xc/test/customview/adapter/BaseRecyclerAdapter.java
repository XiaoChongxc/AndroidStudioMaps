package com.hangzhou.xc.test.customview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hangzhou.xc.test.customview.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.adapter
 * 日期   :   2017/5/11
 * 时间   ：  11:20
 * 描述   ： 通用的 Adapter
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> datas;
    private Context mContext;
    private int mLayoutId;

    public BaseRecyclerAdapter(List datas, Context mContext, int mLayoutId) {
        this.datas = datas;
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseViewHolder holder = BaseViewHolder.get(mContext, parent, mLayoutId);
        if (listener != null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(holder.getAdapterPosition());
                }
            });
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, datas.get(position));
    }

    public abstract void convert(BaseViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
