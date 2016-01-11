package com.jxgm.mrim.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jxgm.mrim.R;
import com.jxgm.mrim.activity.base.BaseActivity;
import com.jxgm.mrim.view.ActionBar;

import butterknife.InjectView;

/**
 * @项目名 ：MRIM
 * @包名 ：com.jxgm.mrim.activity
 * @User ： hezhihu89 by：贺志虎
 * @创建时间 ：2016 年 01 月 11 日   15时 09分.
 * @类的描述 : 註冊頁面
 */
public class SignInActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.sign_in_name)
    EditText mSignInName;
    @InjectView(R.id.sign_in_pass)
    EditText mSgnInPass;
    @InjectView(R.id.sign_in_reqpass)
    EditText mSignInReqpass;
    @InjectView(R.id.in_code)
    EditText mInCode;
    @InjectView(R.id.sign_in_but)
    Button mSignInBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.sign_in_activity);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.sign_in_but:
                //注册按钮
                signIn();
                break;
        }
    }

    private void signIn() {

    }
}
