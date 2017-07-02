package com.example.admin_xc.basemodule.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.admin_xc.basemodule.R;
import com.example.admin_xc.basemodule.customview.EmptyLayout;
import com.example.admin_xc.basemodule.mvp.contract.BaseListContract;
import com.example.admin_xc.basemodule.mvp.presenter.BaseListPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.base
 * 日期   :   2017/6/6
 * 时间   ：  15:35
 * 描述   ：
 */

public abstract class BaseListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseListContract.View {

    public RecyclerView.Adapter adapter;
    @Bind(R.id.recyclerview)
    public RecyclerView recyclerview;
    @Bind(R.id.swipe_refresh)
    public SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.empty)
    public EmptyLayout empty;
    public int pageNo = 1;


    BaseListContract.Presenter presenter;
    public List list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewRes());
        ButterKnife.bind(this);
        new BaseListPresenter(this);

        //设置布局类型
        recyclerview.setLayoutManager(getLayoutManager());
        //设置item动画
        recyclerview.setItemAnimator(getItemAnimator());
        //是否设置间隔
        recyclerview.addItemDecoration(getItemDecoration());
        //下拉刷新
        if (needRefresh())
            swipeRefresh.setOnRefreshListener(this);
        adapter = getAdapter();
        //设置empty
        empty.setErrorType(EmptyLayout.NETWORK_LOADING);
        empty.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty.setErrorType(EmptyLayout.NETWORK_LOADING);
                getData();
            }
        });
        getData();
    }


    /**
     * 设置 recyclerView的展示方式
     *
     * @return
     */
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    /**
     * 设置item动画
     *
     * @return
     */
    public RecyclerView.ItemAnimator getItemAnimator() {
        return new ScaleInAnimator();
    }

    /**
     * 子类重写来控制不同的子页面
     *
     * @return
     */
    public int getContentViewRes() {
        return R.layout.activity_base_list;
    }


    /**
     * 是否需要 刷新
     *
     * @return
     */
    public boolean needRefresh() {
        return true;
    }

    /**
     * 是否需要 加载更多
     *
     * @return
     */
    public boolean needLoadMore() {
        return false;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
//        return new SpaceDecoration(Color.parseColor("#ededed"), 1, DensityUtils.dp2px(mContext, 8), DensityUtils.dp2px(mContext, 8));
        return null;
    }

    @Override
    public void setTitle() {
        initToolbar(toolbar, setListTitle());
    }

    @Override
    public void setPresenter(BaseListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 设置标题
     *
     * @return
     */
    public abstract String setListTitle();

    /**
     * 创建 adapter
     *
     * @return
     */
    public abstract RecyclerView.Adapter getAdapter();

    /**
     * 刷新数据操作
     */
    public void onRefresh() {
        pageNo = 1;
        getData();
    }

    /**
     * 加载更多
     */
    public void onLoadMore() {
        pageNo++;
        getData();
    }

    /**
     * 加载数据
     */
    public void getData() {
        String url = "app3/product/v3.1.2/pagelist";
        Map map = new HashMap();
        map.put("pageNo", pageNo);
        presenter.getData(url, map);
    }


    /**
     * 重写显示进度条方法
     */
    @Override
    public void showLoading() {
        if (list == null || list.size() == 0) {
            empty.setErrorType(EmptyLayout.NETWORK_LOADING);
        }
    }

    /**
     * 重写关闭进度条方法
     */
    @Override
    public void stopLoading() {
        empty.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error, int type) {
        empty.setErrorType(type);
        empty.setErrorMessage(error);
    }
}
