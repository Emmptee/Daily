package com.sdf.daily.ui;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;

import com.sdf.daily.R;
import com.sdf.daily.widgets.MovingImageView;
import com.sdf.sdk.base.activity.BaseCompatActivity;
import com.sdf.sdk.rxbus.RxBus;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.nv_menu)
    NavigationView nvMenu;
    @BindView(R.id.dl_root)
    DrawerLayout dlRoot;
    @BindView(R.id.bv_bar)
    BottomNavigationView bottomNavigationView;

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int FOURTH = 3;
    private static final int FIFTH = 4;
    private MovingImageView mivMenu;
    private CircleImageView civHead;
    private static final int FRAGMENTCOUNT = 5;

    private SupportFragment[] mFragments = new SupportFragment[FRAGMENTCOUNT];


    @Override
    protected void initData() {
        super.initData();
        RxBus.get().register(this);
    }

    /**
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        mivMenu = nvMenu.getHeaderView(0).findViewById(R.id.miv_menu);
        civHead = nvMenu.getHeaderView(0).findViewById(R.id.civ_head);

        if (savedInstanceState != null) {
            mFragments[FIRST] = HomeRootFragment.newInstance();
            mFragments[SECOND] = HomeRootFragment.newInstance();
            mFragments[THIRD] = HomeRootFragment.newInstance();
            mFragments[FOURTH] = HomeRootFragment.newInstance();
            mFragments[FIFTH] = HomeRootFragment.newInstance();
        }

    }

    /**
     *
     * @return layout Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
