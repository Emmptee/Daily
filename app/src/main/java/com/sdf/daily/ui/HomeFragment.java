package com.sdf.daily.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.sdf.sdk.base.BasePresenter;
import com.sdf.sdk.base.fragment.BaseMVPCompatFragment;

public class HomeFragment extends BaseMVPCompatFragment{

    public static HomeFragment newInstance(){
        Bundle args = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }
    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }
}
