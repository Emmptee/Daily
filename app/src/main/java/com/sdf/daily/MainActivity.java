package com.sdf.daily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sdf.sdk.base.activity.BaseCompatActivity;

public class MainActivity extends BaseCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 初始化view
     * <p>
     * 子类实现 控件绑定、视图初始化等内容
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    /**
     * 获取当前layouty的布局ID,用于设置当前布局
     * <p>
     * 交由子类实现
     *
     * @return layout Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
