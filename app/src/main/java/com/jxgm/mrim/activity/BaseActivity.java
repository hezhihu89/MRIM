package com.jxgm.mrim.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * @项目名 ：GooglePlay
 * @包名 ：com.jxgm.mrim.activity
 * @User ： hezhi by：贺志虎
 * @创建时间 ：2016 年 01 月 10 日   23时 57分.
 * @类的描述 : TODO：
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();


    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();
}
