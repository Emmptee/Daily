package com.sdf.daily.contract.detail;

import com.sdf.daily.model.bean.wangyi.WangyiNewsDetailBean;
import com.sdf.daily.model.bean.wangyi.WangyiNewsListBean;
import com.sdf.daily.presenter.detail.BaseWebViewLoadPresenter;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface WangyiDetailContract {

    abstract class WangyiDeTailPresenter extends BaseWebViewLoadPresenter<IWangyiDetailModel,IWangyiDetailView>{

        /**
         * 加载新闻详情
         * @param url
         */
        public abstract void loadNewDetailWithUrl(String url);

        public abstract void loadNewsDetailWithId(String id);


    }

    /**
     * 获取新闻详情
     */
    interface IWangyiDetailModel extends BaseWebViewLoadContract.IBaseWebViewLoadModel{
        Observable<ResponseBody> getNewsDetail(String id);
    }

    /**
     *
     */
    interface IWangyiDetailView extends BaseWebViewLoadContract.IBaseWebViewLoadView{
        /**显示新闻详细的内容
         * @param bean
         */
        void showNewsDetail(WangyiNewsDetailBean bean);

        /**重载
         * @param url
         */
        void showNewsDetail(String url);
    }
}
