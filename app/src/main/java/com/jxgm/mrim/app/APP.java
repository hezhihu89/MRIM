package com.jxgm.mrim.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;

import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.util.EMLog;
import com.jxgm.mrim.utiles.LOG;

import java.util.Iterator;
import java.util.List;

/**
 * @项目名 ：MRIM
 * @包名 ：com.jxgm.mrim.app
 * @User ： hezhi by：贺志虎
 * @创建时间 ：2016 年 01 月 11 日   00时 02分.
 * @类的描述 : TODO：
 */
public class APP extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static int mThreadId;
    private static String INCODE = "G216";
    private static String string;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mThreadId = android.os.Process.myPid();

        String processAppName = getAppName(mThreadId);
        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase("com.jxgm.mrim")) {
            LOG.d("TAG", "enter the service process!");
            //"com.easemob.chatuidemo"为demo的包名，换到自己项目中要改成自己包名

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        EMChat.getInstance().setAppkey("hezhihu89#mrim");
        EMChat.getInstance().init(mContext);
        /**
         /* debugMode == true 时为打开，sdk 会在log里输入调试信息
         /* @param debugMode
         /* 在做代码混淆的时候需要设置成false
         /*/
        EMChat.getInstance().setDebugMode(true);//在做打包混淆时，要关闭debug模式，避免消耗不必要的资源
        EMChatManager.getInstance().getChatOptions().setUseRoster(true);
        LOG.d("TAG", "++++++初始化设置APPkey++++");

    }

    public static Handler getHandler() {
        if (mHandler == null) {
            return mHandler = new Handler();
        }
        String s = "500";
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    public static int getThreadId() {
        return mThreadId;
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
