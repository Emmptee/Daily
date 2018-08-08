package com.sdf.daily.presenter.home;

import android.os.Bundle;

import com.sdf.daily.constant.BundleKeyConstant;
import com.sdf.daily.contract.home.tab.WangYiContract;
import com.sdf.daily.model.bean.wangyi.WangYiNewsItemBean;
import com.sdf.daily.model.bean.wangyi.WangyiNewsListBean;
import com.sdf.daily.model.home.tab.WangYiModel;
import com.sdf.daily.ui.activity.detail.WangyiDailyDetailActivity;
import com.sdf.sdk.utils.StringUtils;

import java.util.ArrayList;
import java.util.logging.Logger;

import io.reactivex.functions.Consumer;

public class WangYiPresenter extends WangYiContract.WangYiPresenter {
    private int mCurrentIndex;
    private boolean isloading;

    public static WangYiPresenter newInstance(){
        return new WangYiPresenter();
    }

    @Override
    public void loadLatestList() {
        mCurrentIndex = 0;
        mRxManager.register(mIModel.getNewsList(mCurrentIndex).subscribe(new Consumer<WangyiNewsListBean>() {
            @Override
            public void accept(WangyiNewsListBean wangyiNewsListBean) throws Exception {
                if (mIView != null) {
                    ArrayList<WangYiNewsItemBean> list = wangyiNewsListBean.getNewsList();
                    for (int i = 0; i < list.size(); i++) {
                        if (StringUtils.isEmpty(list.get(i).getUrl())) {
                            list.remove(i);
                        }
                    }
                    mCurrentIndex += 20;
                    mIView.updateContentList(list);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    if (mIView.isVisiable()) {
                        mIView.showToast("网络错误");
                    }
                    mIView.showNetworkError();
                }
            }
        }));
    }

    @Override
    public void loadMoreList() {
        if (!isloading){
            isloading = true;
            mRxManager.register(mIModel.getNewsList(mCurrentIndex).subscribe(new Consumer<WangyiNewsListBean>() {
                @Override
                public void accept(WangyiNewsListBean wangyiNewsListBean) throws Exception {
                    isloading = false;
                    if (mIView == null) {
                        return;
                    }
                    if (wangyiNewsListBean.getNewsList().size() > 0) {
                        mCurrentIndex += 20;
                        mIView.updateContentList(wangyiNewsListBean.getNewsList());
                    } else {
                        mIView.showNoMoreData();
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    isloading = false;
                    if (mIView == null) {
                        return;
                    }
                    mIView.showLoadMoreError();
                }
            }));


        }
    }

    @Override
    public void onItemClick(final int position, WangYiNewsItemBean item) {
        mRxManager.register(mIModel.recordItemIsRead(item.getDocid()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (mIView == null) {
                    return;
                }
                if (aBoolean) {
                    mIView.itemNotifyChanged(position);
                } else {
                    com.orhanobut.logger.Logger.e("写入点击事件失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        }));
        if (mIView == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_ID, item.getDocid());
        bundle.putString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_URL, item.getUrl());
        bundle.putString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_TITLE, item.getTitle());
        bundle.putString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_IMAGE_URL, item.getImgsrc());
        bundle.putString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_COPYRIGHT, item.getSource());
        mIView.startNewActivity(WangyiDailyDetailActivity.class, bundle);
    }

    @Override
    protected WangYiContract.IWangYiModel getModel() {
        return WangYiModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
