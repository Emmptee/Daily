package com.sdf.daily.model.detail;

import android.support.annotation.NonNull;


import com.sdf.daily.api.ZhihuApi;
import com.sdf.daily.contract.detail.ZhihuDetailContract;
import com.sdf.daily.model.zhihu.ZhihuDailyDetailBean;
import com.sdf.sdk.base.BaseModel;
import com.sdf.sdk.helper.RetrofitCreateHelper;
import com.sdf.sdk.helper.RxHelper;

import io.reactivex.Observable;

/**
 * Created by Horrarndoo on 2017/9/13.
 * <p>
 */

public class ZhihuDetailModel extends BaseModel implements ZhihuDetailContract.IZhihuDetailModel {

    @NonNull
    public static ZhihuDetailModel newInstance() {
        return new ZhihuDetailModel();
    }

    @Override
    public Observable<ZhihuDailyDetailBean> getDailyDetail(String id) {
        return RetrofitCreateHelper.createApi(ZhihuApi.class, ZhihuApi.HOST).getZhihuDailyDetail
                (id).compose(RxHelper.<ZhihuDailyDetailBean>rxSchedulerHelper());
    }
}
