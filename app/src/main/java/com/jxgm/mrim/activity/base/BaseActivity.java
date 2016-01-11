package com.jxgm.mrim.activity.base;

import android.app.Activity;
import android.os.Bundle;

import com.jxgm.mrim.utiles.LOG;
import com.jxgm.mrim.utiles.MD5Utile;

/**
 * @项目名 ：MRIM
 * @包名 ：com.jxgm.mrim.activity
 * @User ： hezhi by：贺志虎
 * @创建时间 ：2016 年 01 月 10 日   23时 57分.
 * @类的描述 : TODO：
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置是否是debug模式
        LOG.isDebug = true;
        LOG.d("TAG","+++++++base+++++++++");

        initView();
        initData();
        initEvent();


    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();
}
