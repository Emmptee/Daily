package com.sdf.daily.model.bean.wangyi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class WangyiNewsListBean {

    @SerializedName("T1348647909107")
    ArrayList<WangYiNewsItemBean> newsList;

    public ArrayList<WangYiNewsItemBean> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<WangYiNewsItemBean> newsList) {
        this.newsList = newsList;
    }
}
