package com.jxgm.mrim.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.exceptions.EaseMobException;
import com.jxgm.mrim.R;
import com.jxgm.mrim.activity.base.BaseActivity;
import com.jxgm.mrim.app.APP;
import com.jxgm.mrim.utiles.LOG;
import com.jxgm.mrim.utiles.MD5Utile;
import com.jxgm.mrim.utiles.TextHelp;
import com.jxgm.mrim.utiles.Toasts;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @项目名 ：MRIM
 * @包名 ：com.jxgm.mrim.activity
 * @User ： hezhihu89 by：贺志虎
 * @创建时间 ：2016 年 01 月 11 日   15时 09分.
 * @类的描述 : 註冊頁面
 */
public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.sign_in_name)
    EditText mSignInName;
    @InjectView(R.id.sign_in_pass)
    EditText mSignInPass;
    @InjectView(R.id.sign_in_reqpass)
    EditText mSignInReqpass;
    @InjectView(R.id.in_code)
    EditText mInCode;
    @InjectView(R.id.sign_in_but)
    Button mSignInBut;
    @InjectView(R.id.sign_in_cancle)
    Button mSignInCancle;
    private AlertDialog.Builder dialog;
    private String signInName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.sign_in_activity);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        dialog = new AlertDialog.Builder(this);
    }

    @Override
    public void initEvent() {
        mSignInCancle.setOnClickListener(this);
        mSignInBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.sign_in_but:
                //注册按钮
                signIn();
                break;
            case R.id.sign_in_cancle:
                putResule(false);
                finish();
                break;
        }
    }

    private void signIn() {
        signInName = mSignInName.getText().toString().trim();
        String signInPass = mSignInPass.getText().toString().trim();
        String signInReqPass = mSignInReqpass.getText().toString().trim();
        String singInCode = mInCode.getText().toString().trim();

        if (!TextHelp.isUserName(signInName).equals(TextHelp.OK)) {
            Toasts.makeToast(SignUpActivity.this, TextHelp.isUserName(signInName)).show();
            return;
        }
        if (!TextHelp.isPassword(signInPass).equals(TextHelp.OK)) {
            Toasts.makeToast(SignUpActivity.this, TextHelp.isPassword(signInPass)).show();
            return;
        }
        if (!signInPass.equals(signInReqPass)) {
            Toasts.makeToast(SignUpActivity.this, "两次输入密码不同").show();
            return;
        }
        if (TextUtils.isEmpty(singInCode)) {
            Toasts.makeToast(SignUpActivity.this, "请输入邀请码").show();
            return;
        }
        if (!singInCode.equals("1010")) {
            Toasts.makeToast(SignUpActivity.this, "邀请码错误").show();
            return;
        }
        mSignInBut.setEnabled(false);
        signInThread(signInName, signInPass);
    }

    /**
     * 执行注册
     *
     * @param signInName 用户名
     * @param signInPass 密码
     */
    private void signInThread(final String signInName, final String signInPass) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String msg = "code xxx";
                try {
                    //使用MD5 加密
                    String MD5Pass = MD5Utile.encodeByMD5(signInPass);
                    // 调用sdk注册方法
                    EMChatManager.getInstance().createAccountOnServer(signInName, MD5Pass);
                    msg = "注册成功";
                } catch (final EaseMobException e) {
                    //注册失败
                    int errorCode = e.getErrorCode();
                    if (errorCode == EMError.NONETWORK_ERROR) {
                        msg = ("网络异常，请检查网络！");
                        LOG.d("TAG", "网络异常");
                    } else if (errorCode == EMError.USER_ALREADY_EXISTS) {
                        msg = ("用户已存在！");
                        LOG.d("TAG", "用户已存在");
                    } else if (errorCode == EMError.UNAUTHORIZED) {
                        msg = ("注册失败，无权限！");
                        LOG.d("TAG", "注册失败 无权限");
                    } else {
                        msg = ("注册失败: ");
                        LOG.d("TAG", "注册失败");
                    }
                } finally {
                    final String finalMsg = msg;
                    APP.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            mSignInBut.setEnabled(true);
                            if (dialog != null) {
                                Dialog(finalMsg);

                            }
                        }
                    });
                }

            }
        }).start();
    }

    private void Dialog(String finalMsg) {
        dialog.setTitle("提示").setMessage(finalMsg).setIcon(R.mipmap.ic_launcher).
                setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        putResule(true);
                        finish();
                    }
                }).show();

    }

    private void putResule(boolean ok) {
        Bundle bundle = new Bundle();
        if (signInName == null || !ok) {
            bundle.putString("userName", "");
        } else {
            bundle.putString("userName", signInName);//给 bundle 写入数据
        }
        Intent mIntent = new Intent();
        mIntent.putExtras(bundle);
        setResult(RESULT_OK, mIntent);
    }

}
