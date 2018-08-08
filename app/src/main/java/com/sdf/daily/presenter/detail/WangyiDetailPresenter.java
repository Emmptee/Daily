package com.sdf.daily.presenter.detail;

import com.sdf.daily.contract.detail.WangyiDetailContract;
import com.sdf.daily.helper.JsonHelper;
import com.sdf.daily.model.bean.wangyi.WangyiNewsDetailBean;
import com.sdf.daily.model.detail.WangyiDetailModel;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class WangyiDetailPresenter extends WangyiDetailContract.WangyiDeTailPresenter{

    public static WangyiDetailPresenter newInstance(){
        return new WangyiDetailPresenter();
    }

    @Override
    public void loadNewDetailWithUrl(String url) {
        if (mIView == null) {
            return;
        }
        try {
            mIView.showNewsDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public void loadNewsDetailWithId(final String id) {
        mRxManager.register(mIModel.getNewsDetail(id).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                if (mIView == null) {
                    return;
                }
                WangyiNewsDetailBean bean = JsonHelper.getNewsDetailBeans(responseBody.string(), id);
                mIView.showNewsDetail(bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null) {
                    return;
                }
                mIView.showNetworkError();
            }
        }));
    }

    @Override
    protected WangyiDetailContract.IWangyiDetailModel getModel() {
        return WangyiDetailModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
