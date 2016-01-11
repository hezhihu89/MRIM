package com.jxgm.mrim.utiles;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @项目名 ：MRIM
 * @包名 ：com.jxgm.mrim.utiles
 * @User ： hezhihu89 by：贺志虎
 * @创建时间 ：2016 年 01 月 11 日   11时 48分.
 * @类的描述 : TODO：
 */
public class Metricutile {

    private static DisplayMetrics metrics = new DisplayMetrics();

    public static float getMetricHight(Context context) {
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
