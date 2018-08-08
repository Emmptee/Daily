package com.sdf.daily.ui.fragment.home.child.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sdf.daily.R;
import com.sdf.daily.contract.home.tab.WangYiContract;
import com.sdf.daily.model.bean.wangyi.WangYiNewsItemBean;
import com.sdf.daily.adapter.WangYiAdapter;
import com.sdf.daily.presenter.home.WangYiPresenter;
import com.sdf.sdk.base.BasePresenter;
import com.sdf.sdk.base.fragment.BaseRecycleFragment;

import java.util.List;

import butterknife.BindView;

public class WangYiFragment extends BaseRecycleFragment<WangYiContract.WangYiPresenter>
        implements WangYiContract.IWangYiView, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_wangyi)
    RecyclerView rvWangyi;
    private WangYiAdapter mWangYiAdapter;

    public static WangYiFragment newInstance() {
        Bundle bundle = new Bundle();
        WangYiFragment wangYiFragment = new WangYiFragment();
        wangYiFragment.setArguments(bundle);
        return wangYiFragment;
    }

    @Override
    protected void onErrorViewClick(View view) {

    }

    @Override
    protected void showLoading() {

    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return WangYiPresenter.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_wangyi;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mWangYiAdapter = new WangYiAdapter(R.layout.item_recycle_home);
        rvWangyi.setAdapter(mWangYiAdapter);
        rvWangyi.setLayoutManager(new LinearLayoutManager(mActivity));

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLatestList();//第一次显示时请求最新的list
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void updateContentList(List<WangYiNewsItemBean> list) {
        if (mWangYiAdapter.getData().size() == 0) {
            initRecycleView(list);
        }else {
            mWangYiAdapter.addData(list);
        }
    }

    private void initRecycleView(List<WangYiNewsItemBean> list) {
        mWangYiAdapter = new WangYiAdapter(R.layout.item_recycle_home, list);
        mWangYiAdapter.setOnLoadMoreListener(this,rvWangyi);
        mWangYiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onItemClick(position, (WangYiNewsItemBean) adapter.getItem(position));
            }
        });
        rvWangyi.setAdapter(mWangYiAdapter);
    }

    @Override
    public void itemNotifyChanged(int position) {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showLoadMoreError() {

    }

    @Override
    public void showNoMoreData() {

    }
}
