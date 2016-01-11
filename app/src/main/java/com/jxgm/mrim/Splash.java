package com.jxgm.mrim;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.jxgm.mrim.activity.LoginActivity;
import com.jxgm.mrim.activity.base.BaseActivity;
import com.jxgm.mrim.app.APP;

public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        APP.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_ent, R.anim.activity_out);
                finish();
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
