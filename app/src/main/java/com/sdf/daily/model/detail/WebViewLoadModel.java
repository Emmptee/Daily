package com.sdf.daily.model.detail;

import com.sdf.sdk.base.BaseModel;
import com.sdf.daily.contract.detail.WebViewLoadContract;

public class WebViewLoadModel extends BaseModel implements WebViewLoadContract.IWebViewLoadModel {

    public static WebViewLoadModel newInstance(){
        return new WebViewLoadModel();
    }
}
