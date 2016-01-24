package com.jxgm.mrim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.jxgm.mrim.R;
import com.jxgm.mrim.activity.base.BaseActivity;
import com.jxgm.mrim.app.APP;
import com.jxgm.mrim.utiles.MD5Utile;
import com.jxgm.mrim.utiles.Metricutile;
import com.jxgm.mrim.utiles.TextHelp;
import com.jxgm.mrim.utiles.ThreadPoolutile;
import com.jxgm.mrim.utiles.Toasts;

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
        mSignIn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (!isBack) {
            Toasts.makeToast(this, "再按一次退出").show();
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
                signIn();
                break;
            case R.id.sign_in:
                //注册
                signUp();
                break;
        }

    }

    /**
     * 登录
     */
    private void signIn() {
        //登陆
        final String singInName = mLoginName.getText().toString().trim();
        final String singInpass = mLoginPass.getText().toString().trim();
        if (!TextHelp.isUserName(singInName).equals(TextHelp.OK)) {
            Toasts.makeToast(this, TextHelp.isUserName(singInName)).show();
            return;
        }
        if (!TextHelp.isPassword(singInpass).equals(TextHelp.OK)) {
            Toasts.makeToast(this, TextHelp.isPassword(singInpass)).show();
            return;
        }
        //使用线程池登陆
        ThreadPoolutile.task(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setEnabled(false);
                    }
                });
                String sginInpassMD5 = MD5Utile.encodeByMD5(singInpass);
                clickSignIn(singInName, sginInpassMD5);
            }
        });

    }

    private void clickSignIn(String singInName, String singInpass) {

        EMChatManager.getInstance().login(singInName, singInpass, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        EMGroupManager.getInstance().loadAllGroups();
                        EMChatManager.getInstance().loadAllConversations();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toasts.makeToast(LoginActivity.this, "登陆聊天服务器成功！").show();
                                Intent toHomeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(toHomeIntent);
                                overridePendingTransition(R.anim.activity_alpha_out, R.anim.activity_alpha_ent);
                                finish();
                                setEnabled(true);

                            }
                        });
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, final String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setEnabled(true);
                        Toasts.makeToast(LoginActivity.this, message).show();
                    }
                });
            }
        });

    }

    /**
     * 注册
     */
    private void signUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (null != data.getStringExtra("userName")) {
                mLoginName.setText(data.getStringExtra("userName"));
            }
        }

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
        mAnimation.setAnimationListener(animationListener);
        view.startAnimation(mAnimation);
    }

    Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            setEnabled(false);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            setEnabled(true);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private void setEnabled(boolean enable) {
        mSignUp.setEnabled(enable);
        mSignIn.setEnabled(enable);
        mLoginPass.setEnabled(enable);
        mLoginName.setEnabled(enable);
    }

}
