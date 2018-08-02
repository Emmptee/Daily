package com.sdf.daily.contract;

import com.sdf.sdk.base.BasePresenter;
import com.sdf.sdk.base.IBaseFragment;
import com.sdf.sdk.base.IBaseModel;
import com.sdf.sdk.base.IBaseView;

public interface HomeMainContract {
    abstract class HomeMainPresenter extends BasePresenter<IHomeMainModel,IHomeMainView>{
        public abstract void getTabList();
    }

    interface IHomeMainView extends IBaseView{
        void showTabList(String[] tabs);
    }

    interface IHomeMainModel extends IBaseModel {
        String[] getTabs();
    }
}
