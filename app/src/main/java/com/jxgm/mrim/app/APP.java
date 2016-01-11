package com.jxgm.mrim.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @项目名 ：MRIM
 * @包名 ：com.jxgm.mrim.app
 * @User ： hezhi by：贺志虎
 * @创建时间 ：2016 年 01 月 11 日   00时 02分.
 * @类的描述 : TODO：
 */
public class APP extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static int mThreadId;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mThreadId = android.os.Process.myPid();
    }

    public static Handler getHandler() {
        if (mHandler == null) {
            return mHandler = new Handler();
        }
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    public static int getThreadId() {
        return mThreadId;
    }
}
