package com.sdf.daily;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.sdf.sdk.global.GlobalApplication;
import com.tencent.bugly.crashreport.CrashReport;

public class DailyApplication extends GlobalApplication {

    private static int SCREEN_WIDTH = 630;
    private static int SCREEN_HIGHT = 787;
    public static float DIMEN_RATE = -1.0f;
    public static int DIMEN_DPI = -1;
    private DailyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化屏幕的宽和高
        getScreenSize();
        CrashReport.initCrashReport(getApplicationContext(),"c73ecc54da",false);
    }

    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(displayMetrics);
        DIMEN_RATE = displayMetrics.density / 1.0F;
        DIMEN_DPI = displayMetrics.densityDpi;
        SCREEN_WIDTH = displayMetrics.widthPixels;
        SCREEN_HIGHT = displayMetrics.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HIGHT){
            int t = SCREEN_HIGHT;
            SCREEN_HIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }
}
