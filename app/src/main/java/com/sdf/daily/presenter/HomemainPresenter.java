package com.sdf.daily.presenter;

import com.sdf.daily.contract.HomeMainContract;
import com.sdf.daily.model.HomeMainModel;
import com.sdf.sdk.base.BasePresenter;

public class HomemainPresenter extends HomeMainContract.HomeMainPresenter {

    public static HomemainPresenter newInstance() {
        return new HomemainPresenter();
    }

    @Override
    public void getTabList() {
        if (mIModel == null || mIView == null) {
            return;
        }
        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    protected HomeMainContract.IHomeMainModel getModel() {
        return HomeMainModel.newInstance();
    }

    @Override
    public void onStart() {

    }

}
