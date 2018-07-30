package com.sdf.daily;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdf.sdk.base.activity.BaseCompatActivity;
import com.sdf.sdk.helper.RxHelper;
import com.sdf.sdk.utils.StringUtils;
import com.sdf.sdk.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class SplashActivity extends BaseCompatActivity{

    @BindView(R.id.ll_skip)
    LinearLayout llSkip;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;

    private static final String TAG = "RxPermission";
    private boolean mIsCancle;
    private int mTime = 3;

    @Override
    protected void initView(Bundle savedInstanceState) {
        //注：魅族pro6s-7.0-flyme6权限没有像类似6.0以上手机一样正常的提示dialog获取运行时权限，而是直接默认给了权限
        requestPermissions();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onBackPressedSupport() {
        mIsCancle = true;
        setIsTransAnim(false);
        finish();
    }

    @OnClick(R.id.ll_skip)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_skip:
                mIsCancle = true;
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                break;
        }
    }

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(SplashActivity.this);
        //请求权限全部结果
        rxPermission.request(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new io.reactivex.functions.Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean){
                            ToastUtils.showToast("APP未能获取全部需要的权限，可能无法提供更好的服务" );
                        }
                        initCountDown();
                    }
                });

    }

    private void initCountDown() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)//计时次数
                .map(new Function<Long, Object>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mTime - aLong;// 3-0 3-2 3-1
                    }
                })
                .compose(RxHelper.rxSchedulerHelper())
                .subscribe(new io.reactivex.Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object value) {
                        //                        Logger.e("value = " + value);
                        String s = String.valueOf(value);
                        if (tvCountDown != null)
                            tvCountDown.setText(StringUtils.isEmpty(s) ? "" : s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        if (!mIsCancle) {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }
}
