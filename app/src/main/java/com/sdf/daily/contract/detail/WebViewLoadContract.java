package com.sdf.daily.contract.detail;

import com.sdf.daily.presenter.detail.BaseWebViewLoadPresenter;

public interface WebViewLoadContract {
    abstract class WebViewLoadPresenter extends BaseWebViewLoadPresenter
            <IWebViewLoadModel,IWebViewLoadView>{
        public abstract void loadUrl(String url);//加载url
    }

    interface IWebViewLoadModel extends BaseWebViewLoadContract.IBaseWebViewLoadModel{
    }

    interface IWebViewLoadView extends BaseWebViewLoadContract.IBaseWebViewLoadView{
        void showUrlDetail(String url);//显示url详情
    }
}
