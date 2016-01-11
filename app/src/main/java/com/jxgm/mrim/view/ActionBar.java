package com.jxgm.mrim.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxgm.mrim.R;

import butterknife.InjectView;

/**
 * @项目名 ：MRIM
 * @包名 ：com.jxgm.mrim.view
 * @User ： hezhihu89 by：贺志虎
 * @创建时间 ：2016 年 01 月 11 日   15时 51分.
 * @类的描述 : TODO：
 */
public class ActionBar extends RelativeLayout {

    @InjectView(R.id.action_bar_title)
    TextView actionBarTitle;
    @InjectView(R.id.action_bar_back)
    ImageView actionBarBack;

    public ActionBar(Context context) {
        this(context, null);
    }

    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.action_bar, this, true);

    }

    public TextView getTitle() {
        return actionBarTitle;
    }

    public View getBack() {

        return actionBarBack;
    }
}
