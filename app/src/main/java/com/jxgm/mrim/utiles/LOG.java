package com.jxgm.mrim.utiles;

import android.text.TextUtils;
import android.util.Log;

/**
 * @项目名 ：GooglePlay
 * @包名 ：com.jxgm.mrim.utiles
 * @User ： hezhi by：贺志虎
 * @创建时间 ：2016 年 01 月 11 日   00时 09分.
 * @类的描述 : TODO：
 */
public class LOG {
    public static boolean isDebug = false;

    /**
     * 打印log方法
     *
     * @param tag
     * @param message
     */
    public static void d(String tag, String message) {
        if (!isDebug) {
            return;
        }
        Log.d(tag, message);
    }
}
