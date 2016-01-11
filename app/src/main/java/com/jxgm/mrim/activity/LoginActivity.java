package com.jxgm.mrim.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jxgm.mrim.R;
import com.jxgm.mrim.activity.base.BaseActivity;
import com.jxgm.mrim.app.APP;
import com.jxgm.mrim.utiles.LOG;
import com.jxgm.mrim.utiles.MD5Utile;
import com.jxgm.mrim.utiles.Metricutile;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @项目名 ：MRIM
 * @包名 ：com.jxgm.mrim.activity.base
 * @User ： hezhi by：贺志虎
 * @创建时间 ：2016 年 01 月 11 日   00时 02分.
 * @类的描述 : TODO：
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @InjectView(R.id.sign_in)
    TextView mSignIn;
    @InjectView(R.id.sign_up)
    Button mSignUp;
    private boolean isBack = false;

    @InjectView(R.id.sing_title)
    RelativeLayout mSingTitle;
    @InjectView(R.id.login_name)
    EditText mLoginName;
    @InjectView(R.id.login_pass)
    EditText mLoginPass;
    @InjectView(R.id.sing_content)
    RelativeLayout mSingContent;
    private TranslateAnimation mAnimation;
    private String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimation(mSingContent);
    }

    @Override
    public void initView() {
        setContentView(R.layout.login_activity);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mSignUp.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        if (!isBack) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            isBack = true;
            APP.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBack = false;
                }
            }, 2000);
        } else {
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.sign_up:
                //登录
                signUP();
                break;
            case R.id.sign_in:
                //注册
                signIn();
                break;
        }

    }

    private void signUP() {

    }

    private void signIn() {
        Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    /**
     * 执行动画
     *
     * @param view
     */
    private void startAnimation(View view) {
        if (mAnimation == null) {
            mAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    Animation.RELATIVE_TO_SELF,
                    Metricutile.getMetricHight(this),
                    (Animation.RELATIVE_TO_SELF));
        }
        mAnimation.setDuration(1500);
        mAnimation.setRepeatCount(0);
        mAnimation.setRepeatMode(Animation.RESTART);
        mAnimation.setFillAfter(true);
        view.startAnimation(mAnimation);
    }

}
