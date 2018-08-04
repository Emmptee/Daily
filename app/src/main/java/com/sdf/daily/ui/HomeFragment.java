package com.sdf.daily.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.sdf.daily.R;
import com.sdf.daily.constant.TabFragmentIndex;
import com.sdf.daily.contract.HomeMainContract;
import com.sdf.daily.presenter.HomemainPresenter;
import com.sdf.daily.ui.fragment.ZhihuFragment;
import com.sdf.sdk.adapter.FragmentAdapter;
import com.sdf.sdk.anim.ToolbarAnimManager;
import com.sdf.sdk.base.BasePresenter;
import com.sdf.sdk.base.activity.BaseCompatActivity;
import com.sdf.sdk.base.fragment.BaseMVPCompatFragment;
import com.sdf.sdk.utils.SpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseMVPCompatFragment<HomeMainContract.HomeMainPresenter> implements
        HomeMainContract.IHomeMainView {

    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.fab_download)
    FloatingActionButton fabDownload;


    protected OnOpenDrawerLayoutListener onOpenDrawerLayoutListener;
    private List<Fragment> fragments;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOpenDrawerLayoutListener) {
            onOpenDrawerLayoutListener = (OnOpenDrawerLayoutListener) context;
        }

        fragments = new ArrayList<>();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onOpenDrawerLayoutListener = null;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getTabList();
    }

    public static HomeFragment newInstance(){
        Bundle args = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }
    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return HomemainPresenter.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        toolbar.setTitle("首页");
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOpenDrawerLayoutListener != null) {
                    onOpenDrawerLayoutListener.onOpen();
                }
            }
        });

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    fabDownload.show();
                } else {
                    fabDownload.hide();
                }
            }
        });

        fabDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转github主页
                /*Bundle bundle = new Bundle();
                bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE,"Yizhi");
                bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL,
                        "https://github.com/Horrarndoo/YiZhi");
                startNewActivity(WebViewLoadActivity.class,bundle);*/
            }
        });
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.getMenu().findItem(R.id.night).setChecked(SpUtils.getNightModel(mContext));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.night:
                        item.setChecked(!item.isChecked());
                        SpUtils.setNightModel(mContext,item.isChecked());
                        ((BaseCompatActivity)mActivity).reload();
                        break;
                }
                return false;
            }
        });

        ToolbarAnimManager.animIn(mContext, toolbar);

    }

    @Override
    public void showTabList(String[] tabs) {
        Logger.w(Arrays.toString(tabs));
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.addTab(tlTabs.newTab().setText(tabs[i]));
            switch (i) {
                case TabFragmentIndex.TAB_ZHIHU_INDEX:
                    //TODO 跳转知乎fragment
                    fragments.add(ZhihuFragment.newInstance());

                    break;
                case TabFragmentIndex.TAB_WANGYI_INDEX:
                    //TODO 跳转网易fragment
                    fragments.add(ZhihuFragment.newInstance());

                    break;
                case TabFragmentIndex.TAB_WEIXIN_INDEX:
                    //TODO 跳转微信fragment
                    fragments.add(ZhihuFragment.newInstance());

                    break;
                default:
                    //TODO 默认知乎fragment
                    fragments.add(ZhihuFragment.newInstance());

                    break;
            }
        }

        vpFragment.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments));
        vpFragment.setCurrentItem(TabFragmentIndex.TAB_ZHIHU_INDEX);
        tlTabs.setupWithViewPager(vpFragment);
        tlTabs.setVerticalScrollbarPosition(TabFragmentIndex.TAB_ZHIHU_INDEX);
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.getTabAt(i).setText(tabs[i]);
        }
    }


    public interface OnOpenDrawerLayoutListener {
        void onOpen();
    }
}
