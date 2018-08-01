package com.sdf.daily.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sdf.daily.R;
import com.sdf.sdk.base.fragment.BaseCompatFragment;

@SuppressLint("ValidFragment")
public class HomeRootFragment extends BaseCompatFragment {

    public static HomeRootFragment newInstance() {
        Bundle args = new Bundle();
        HomeRootFragment homeRootFragment = new HomeRootFragment();
        homeRootFragment.setArguments(args);
        return homeRootFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(HomeFragment.class) == null){
            loadRootFragment(R.id.fl_container,HomeFragment.newInstance());
        }
    }


}
