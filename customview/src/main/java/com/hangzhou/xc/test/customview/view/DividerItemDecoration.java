package com.hangzhou.xc.test.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @ProjectName: Demotwo
 * @PackageName: wenjie.winfo.com.demotwo
 * @FileName: wenjie.winfo.com.demotwo.DividerItemDecoration.java
 * @Author: wenjie
 * @Date: 2017-06-27 09:58
 * @Description:
 * @Version:
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int attrs[] = {android.R.attr.listDivider};

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable drawable;

    private int mOrientation;

    public DividerItemDecoration(Context context, int mOrientation) {
        final TypedArray ta = context.obtainStyledAttributes(attrs);
        drawable = ta.getDrawable(0);
        ta.recycle();
        if (mOrientation != HORIZONTAL_LIST && mOrientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        this.mOrientation = mOrientation;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View chidView = parent.getChildAt(i);
            RecyclerView rv = new RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) chidView.getLayoutParams();
            final int top = chidView.getBottom() + params.bottomMargin;
            final int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childConunt = parent.getChildCount();
        for (int i = 0; i < childConunt; i++) {
            View viewChild = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) viewChild.getLayoutParams();
            int left = viewChild.getRight() + params.rightMargin;
            int right = left + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
            c.drawColor(Color.parseColor("#006E6C"));
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST) {
            outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        } else {
            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
        }
    }
}
