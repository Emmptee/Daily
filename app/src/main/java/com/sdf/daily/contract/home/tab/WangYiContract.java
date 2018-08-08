package com.sdf.daily.contract.home.tab;

import com.sdf.daily.contract.home.BaseTabsContract;
import com.sdf.daily.model.bean.wangyi.WangYiNewsItemBean;
import com.sdf.daily.model.bean.wangyi.WangyiNewsListBean;

import io.reactivex.Observable;

public interface WangYiContract {
    abstract class WangYiPresenter extends BaseTabsContract.BaseTabsPresenter<IWangYiModel,
            IWangYiView,WangYiNewsItemBean>{

    }


    interface IWangYiModel extends BaseTabsContract.IBaseTabsModel{
        Observable<WangyiNewsListBean> getNewsList(int id);
    }

    interface IWangYiView extends BaseTabsContract.IBaseTabsView<WangYiNewsItemBean>{
    }
}
