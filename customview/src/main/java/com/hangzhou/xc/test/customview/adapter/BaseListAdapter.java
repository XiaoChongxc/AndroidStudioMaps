package com.hangzhou.xc.test.customview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hangzhou.xc.test.customview.adapter.viewholder.BaseViewholder;

import java.util.List;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.adapter
 * 日期   :   2017/5/5
 * 时间   ：  16:02
 * 描述   ：
 */

public class BaseListAdapter extends RecyclerView.Adapter<BaseViewholder> {
    private List list;
    private Context ctx;
    /**
     * viewholder 的资源id
     */
    private int viewholderRES;

    public BaseListAdapter(List list, Context ctx, int viewholderRES) {
        this.list = list;
        this.ctx = ctx;
        this.viewholderRES = viewholderRES;
    }

    @Override
    public BaseViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(viewholderRES, parent, false);
        return new BaseViewholder(view);
    }

    @Override
    public void onBindViewHolder(final BaseViewholder holder, int position) {
        holder.setData(list.get(position));
        if (listener != null) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
