package com.sdf.daily.ui.activity.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.bumptech.glide.Glide;
import com.sdf.daily.constant.BundleKeyConstant;
import com.sdf.daily.contract.detail.WangyiDetailContract;
import com.sdf.daily.model.bean.wangyi.WangyiNewsDetailBean;
import com.sdf.daily.model.bean.wangyi.WangyiNewsListBean;
import com.sdf.daily.presenter.detail.WangyiDetailPresenter;
import com.sdf.sdk.base.BasePresenter;
import com.sdf.sdk.utils.HtmlUtils;
import com.sdf.sdk.utils.ResourcesUtils;

public class WangyiDailyDetailActivity extends BaseWebViewLoadActivity<WangyiDetailContract.WangyiDeTailPresenter>
        implements WangyiDetailContract.IWangyiDetailView{

    private String mTitle, mUrl, mId, mImageUrl, mCopyright;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mId = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_ID);
            mUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_URL);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_TITLE);
            mImageUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_IMAGE_URL);
            mCopyright = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_COPYRIGHT);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvDetailTitle.setText(mTitle);
        tvDetailcopyright.setText(mCopyright);
        Glide.with(mContext).load(mImageUrl).crossFade().into(ivDetail);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadNewDetailWithUrl(mUrl);
    }

    @Override
    protected String getToolbarTitle() {
        return ResourcesUtils.getString(com.sdf.sdk.R.string.wangyi_detail_title);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return WangyiDetailPresenter.newInstance();
    }

    @Override
    public void showNewsDetail(WangyiNewsDetailBean bean) {
        flNetView.setVisibility(View.GONE);
        nswvDetailContent.loadData(bean.getBody(), HtmlUtils.MIME_TYPE,HtmlUtils.ENCODING);
    }

    @Override
    public void showNewsDetail(String url) {
        flNetView.setVisibility(View.GONE);
        nswvDetailContent.loadUrl(url);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
