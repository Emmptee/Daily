package com.sdf.daily.presenter.detail;

import android.support.annotation.NonNull;


import com.sdf.daily.contract.detail.ZhihuDetailContract;
import com.sdf.daily.model.detail.ZhihuDetailModel;
import com.sdf.daily.model.zhihu.ZhihuDailyDetailBean;

import io.reactivex.functions.Consumer;

/**
 * <p>
 */

public class ZhihuDetailPresenter extends ZhihuDetailContract.ZhihuDetailPresenter {

    @NonNull
    public static ZhihuDetailPresenter newInstance() {
        return new ZhihuDetailPresenter();
    }

    @Override
    public void loadDailyDetail(String id) {
        if (mIModel == null)
            return;
        mRxManager.register(mIModel.getDailyDetail(id).subscribe(new Consumer<ZhihuDailyDetailBean>() {
            @Override
            public void accept(ZhihuDailyDetailBean zhihuDailyDetailBean) throws Exception {
                if (mIView != null)
                    mIView.showDailyDetail(zhihuDailyDetailBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    mIView.showToast("网络异常");
                    mIView.showNetworkError();
                }
            }
        }));
    }

    @Override
    protected ZhihuDetailContract.IZhihuDetailModel getModel() {
        return ZhihuDetailModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
