package com.sdf.daily.ui.activity.detail;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.sdf.daily.constant.BundleKeyConstant;
import com.sdf.daily.contract.detail.WebViewLoadContract;
import com.sdf.daily.presenter.detail.WebViewLoadPresenter;
import com.sdf.sdk.base.BasePresenter;
import com.sdf.sdk.utils.DisplayUtils;
import com.sdf.sdk.utils.StatusBarUtils;

public class WebViewLoadActivity extends BaseWebViewLoadActivity<WebViewLoadContract.WebViewLoadPresenter>
        implements WebViewLoadContract.IWebViewLoadView{

    private String mTitle;
    private String mUrl;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) appBar.getChildAt(0).getLayoutParams();
        params.height = DisplayUtils.dp2px(56) + StatusBarUtils.getStatusBarHeight(mContext);
    }

    @Override
    public void showUrlDetail(String url) {
        flNetView.setVisibility(View.GONE);
        nswvDetailContent.loadUrl(url);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadUrl(mUrl);
    }

    @Override
    protected String getToolbarTitle() {
        return mTitle;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return WebViewLoadPresenter.newInstance();
    }
}
