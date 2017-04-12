package com.hangzhou.xc.test.customview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hangzhou.xc.test.customview.R;
import com.hangzhou.xc.test.customview.model.ItemModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.Adapter
 * 日期   :   2017/04/07
 * 时间   ：  16:33
 * 描述   ：
 */

public class ViewFragmentAdapter extends RecyclerView.Adapter<ViewFragmentAdapter.ViewHolder> {
    private Context ctx;
    private List<ItemModel> list;

    private OnItemClickLinstener linstener;

    public ViewFragmentAdapter(Context ctx, List<ItemModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemModel item = list.get(position);
        holder.tvTitle.setText(item.getTitle() + "");
        holder.tvDescription.setText(item.getDescription() + "");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_description)
        TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if (linstener != null)
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linstener.onClick(list,getLayoutPosition());
                    }
                });
        }
    }

    public void setOnItemClickLinstener(OnItemClickLinstener linstener) {
        this.linstener = linstener;
    }

    public interface OnItemClickLinstener {
        void onClick(List list, int position);
    }

}
