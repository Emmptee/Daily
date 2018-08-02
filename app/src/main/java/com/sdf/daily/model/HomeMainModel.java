package com.sdf.daily.model;

import com.sdf.daily.contract.HomeMainContract;
import com.sdf.sdk.base.BaseModel;

public class HomeMainModel extends BaseModel implements HomeMainContract.IHomeMainModel{
    public static HomeMainModel newInstance() {
        return new HomeMainModel();
    }

    @Override
    public String[] getTabs() {
        return new String[]{"知乎日报","新闻热点","微信精选"};
    }
}
