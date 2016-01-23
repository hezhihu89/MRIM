package com.jxgm.mrim.utiles;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jxgm.mrim.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by hezhihu89 on 16-1-23.
 * 封装Toast类
 */
public class Toasts {

    private static Toast mToast;
    private static Toasts myToasts;
    private static List<Toast> list = new ArrayList<>(2);
    private static View toastView;
    private static ImageView mToastImage;
    private static TextView mToastText;
    private static Context mContext;


    public static Toasts makeToast(Context context, CharSequence msg) {
        mContext = context;
        if (myToasts == null) {
            synchronized (Toasts.class) {
                if (myToasts == null) {
                    myToasts = new Toasts();
                }
            }
        }

        initView(context);
        mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        myToasts.setText(msg);
        mToast.setView(toastView);
        list.add(mToast);
        return myToasts;
    }

    public void show() {
        if (list.size() == 2) {
            list.get(0).cancel();
            Toast remove = list.remove(0);
            remove = null;
        }
        list.get(0).show();
    }

    public void setText(CharSequence msg) {
        if (toastView != null) {
            mToastText.setText(msg);
        }
    }

    public void setImage(@DrawableRes int resId) {
        if (mToast != null) {
            mToastImage.setImageResource(resId);
        }
    }


    private static void initView(Context mContext) {
        toastView = View.inflate(mContext, R.layout.toast_layout, null);
        mToastText = (TextView) toastView.findViewById(R.id.toast_tv);
        mToastImage = (ImageView) toastView.findViewById(R.id.toast_img);
       // mToastImage.setOnClickListener(onClickListener);
    }

   /* private static View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mContext != null) {
                makeToast(mContext, "感谢支持").show();
            }
        }
    };*/
}
