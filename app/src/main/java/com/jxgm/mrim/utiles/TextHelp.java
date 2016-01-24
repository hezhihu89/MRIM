package com.jxgm.mrim.utiles;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hezhihu89 on 16-1-24.
 * 用于验证出入框中的字符
 */
public class TextHelp {

    public static String OK = "ok";

    public static String isPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return "请输入密码";
        }
        if (password.length() > 16 || password.length() < 6) {
            return "密码 必须大于6位，小于16位";
        }
        if (!isContance(password)) {
            return "密码必须字母开始";
        }
        return OK;
    }

    public static String isUserName(String userName) {
        if (TextUtils.isEmpty(userName)) {
            return "请输入用户名";
        }
        if (!isEmail(userName) && !isMobileNo(userName)) {
            return "用户名格式错误";
        }

        return OK;
    }

    //是否是邮箱
    private static boolean isEmail(String userName) {//邮箱判断正则表达式
        Pattern pattern = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher mc = pattern.matcher(userName);
        return mc.matches();
    }

    //是否是手机号码
    private static boolean isMobileNo(String mobileNo) {
        Pattern pattern = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher mc = pattern.matcher(mobileNo);
        return mc.matches();
    }

    //判断是否以字母开始
    private static boolean isContance(String password) {
        // ^[A-Za-z0-9]+$
        char[] chars = password.toCharArray();
        char aChar = chars[0];
        String s = aChar + "";
        Pattern pattern = Pattern
                .compile("^[a-zA-Z]");
        Matcher mc = pattern.matcher(s);
        return mc.matches();
    }

    private static boolean isZH(String userName) {
        String regExp = "[\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(userName);
        return m.matches();
    }
}
