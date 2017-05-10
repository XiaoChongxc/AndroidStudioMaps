package com.hangzhou.xc.test.customview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hangzhou.xc.test.customview.adapter.viewholder.BaseViewHolder;

import java.util.List;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.adapter
 * 日期   :   2017/5/5
 * 时间   ：  16:02
 * 描述   ：
 */

public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List list;
    private Context ctx;
    private int viewType;

    /**
     * viewholder 的资源id
     */

    public BaseListAdapter(List list, Context ctx, int viewType) {
        this.list = list;
        this.ctx = ctx;
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder.setViewType(viewType);
        View view = LayoutInflater.from(ctx).inflate(BaseViewHolder.getLayoutRes(), parent, false);
        final BaseViewHolder holder = BaseViewHolder.getViewHolder(view);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        holder.setData(list.get(position));
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
