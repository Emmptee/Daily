package com.sdf.daily.ui.activity.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.bumptech.glide.Glide;
import com.sdf.daily.R;
import com.sdf.daily.constant.BundleKeyConstant;
import com.sdf.daily.contract.detail.ZhihuDetailContract;
import com.sdf.daily.model.zhihu.ZhihuDailyDetailBean;
import com.sdf.daily.presenter.detail.ZhihuDetailPresenter;
import com.sdf.sdk.base.BasePresenter;
import com.sdf.sdk.utils.HtmlUtils;
import com.sdf.sdk.utils.ResourcesUtils;

public class ZhihuDailyDetailActivity extends BaseWebViewLoadActivity<ZhihuDetailContract
        .ZhihuDetailPresenter> implements ZhihuDetailContract.IZhihuDetailView {

    private String mId, mTitle;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mId = bundle.getString(BundleKeyConstant.ARG_KEY_ZHIHU_DETAIL_ID);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_ZHIHU_DETAIL_TITLE);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvDetailTitle.setText(mTitle);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ZhihuDetailPresenter.newInstance();
    }

    @Override
    public void showDailyDetail(ZhihuDailyDetailBean bean) {
        flNetView.setVisibility(View.GONE);
        Glide.with(mContext).load(bean.getImage()).crossFade().into(ivDetail);
        tvDetailTitle.setText(bean.getTitle());
        tvDetailcopyright.setText(bean.getImage_source());
        String htmlData = HtmlUtils.createHtmlData(bean.getBody(), bean.getCss(), bean.getJs());
        nswvDetailContent.loadData(htmlData, HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadDailyDetail(mId);
    }

    @Override
    protected String getToolbarTitle() {
        return ResourcesUtils.getString(R.string.zhihu_detail_title);
    }
}
