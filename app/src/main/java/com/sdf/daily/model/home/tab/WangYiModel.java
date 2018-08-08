package com.sdf.daily.model.home.tab;

import com.sdf.daily.api.WangyiApi;
import com.sdf.daily.contract.home.tab.WangYiContract;
import com.sdf.daily.model.bean.wangyi.WangyiNewsListBean;
import com.sdf.sdk.config.DBConfig;
import com.sdf.sdk.config.ItemState;
import com.sdf.sdk.helper.RetrofitCreateHelper;
import com.sdf.sdk.helper.RxHelper;
import com.sdf.sdk.utils.AppUtils;
import com.sdf.sdk.utils.DBUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class WangYiModel implements WangYiContract.IWangYiModel{

    public static WangYiModel newInstance(){
        return new WangYiModel();
    }

    @Override
    public Observable<WangyiNewsListBean> getNewsList(int id) {
        return RetrofitCreateHelper.createApi(WangyiApi.class,WangyiApi.HOST).getNewsList(id)
                .compose(RxHelper.<WangyiNewsListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<Boolean> recordItemIsRead(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                e.onNext(DBUtils.getDB(AppUtils.getContext()).insertRead(DBConfig.TABLE_WANGYI,key,
                        ItemState.STATE_IS_READ));
                e.onComplete();
            }
        }).compose(RxHelper.<Boolean>rxSchedulerHelper());
    }
}
