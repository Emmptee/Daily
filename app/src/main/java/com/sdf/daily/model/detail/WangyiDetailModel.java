package com.sdf.daily.model.detail;

import com.sdf.daily.api.WangyiApi;
import com.sdf.daily.contract.detail.WangyiDetailContract;
import com.sdf.sdk.base.BaseModel;
import com.sdf.sdk.helper.RetrofitCreateHelper;
import com.sdf.sdk.helper.RxHelper;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class WangyiDetailModel extends BaseModel implements WangyiDetailContract.IWangyiDetailModel{
    public static WangyiDetailModel newInstance(){
        return new WangyiDetailModel();
    }
    @Override
    public Observable<ResponseBody> getNewsDetail(String id) {
        return RetrofitCreateHelper.createApi(WangyiApi.class,WangyiApi.HOST).getNewsDetail(id)
                .compose(RxHelper.<ResponseBody>rxSchedulerHelper());
    }
}
