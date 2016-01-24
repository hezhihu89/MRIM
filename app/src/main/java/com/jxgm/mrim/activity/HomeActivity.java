package com.jxgm.mrim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.easemob.chat.EMContactManager;
import com.easemob.exceptions.EaseMobException;
import com.easemob.util.EMLog;
import com.jxgm.mrim.R;
import com.jxgm.mrim.activity.base.BaseActivity;
import com.jxgm.mrim.app.APP;
import com.jxgm.mrim.utiles.LOG;
import com.jxgm.mrim.utiles.Toasts;

import java.util.List;
import java.util.logging.Handler;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by hezhihu89 on 2016/1/12.
 */
public class HomeActivity extends BaseActivity {

    @InjectView(R.id.test)
    Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.home_activity);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APP.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMContactManager.getInstance().addContact("13879979682", "你好");
                        } catch (EaseMobException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

        APP.getHandler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> usernames = EMContactManager.getInstance().getContactUserNames();//需异步执行
                    LOG.d("用户名", usernames.size() + "+++++++++++++");

                    if (usernames != null) {
                        for (String name : usernames) {
                            LOG.d("用户名", name + "+++++++++++++");
                        }
                    }

                } catch (EaseMobException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //如果是服务里调用，必须加入new task标识
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);


    }
}
