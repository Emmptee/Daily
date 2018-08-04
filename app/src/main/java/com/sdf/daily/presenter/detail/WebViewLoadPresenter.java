package com.sdf.daily.presenter.detail;

import com.sdf.daily.contract.detail.WebViewLoadContract;
import com.sdf.daily.model.detail.WebViewLoadModel;

public class WebViewLoadPresenter  extends WebViewLoadContract.WebViewLoadPresenter{

    public static WebViewLoadPresenter newInstance(){
        return new WebViewLoadPresenter();
    }

    @Override
    public void loadUrl(String url) {
        if (mIView == null) {
            return;
        }

        try {
            mIView.showUrlDetail(url);
        }catch (Exception e){
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }


    @Override
    protected WebViewLoadContract.IWebViewLoadModel getModel() {
        return WebViewLoadModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
