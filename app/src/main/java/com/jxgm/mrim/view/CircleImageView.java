package com.jxgm.mrim.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jxgm.mrim.R;

/**
 * Created by hezhihu89 on 16-1-24.
 * 自定义圆形ImageView
 */
public class CircleImageView extends ImageView {
    private Paint paint;
    private boolean usedWidth;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyleAttr, 0);
        usedWidth = a.getBoolean(R.styleable.CircleImageView_used_width, true);

        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        //获取设置的图片
        if (null != drawable) {
            //将drable 强转为BitmapDrable 获取到bitmap
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            //获取圆形的bitmao
            Bitmap b = getCricleBitmap(bitmap);
            final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());
            final Rect rectDesc = new Rect(0, 0, getWidth(), getHeight());
            //重置画笔
            paint.reset();

            //开始画
            canvas.drawBitmap(b, rectSrc, rectDesc, paint);

        } else {
            super.onDraw(canvas);

        }

    }

    private Bitmap getCricleBitmap(Bitmap bitmap) {
        //拷贝一份bitmap
        Bitmap outBimap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //创建画布 用来画拷贝的图片
        Canvas canvas = new Canvas(outBimap);
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        //设置画布的色彩
        canvas.drawARGB(0, 0, 0, 0);
        //设置画笔没有锯齿
        paint.setAntiAlias(true);
        //设置画笔颜色
        paint.setColor(0xff424242);

        //获取图片的宽

        int x = getUsedWidth(bitmap);
        //设置圆形画布的规格
        canvas.drawCircle(x / 2, x / 2, x / 2, paint);

        //设置画布bitmap 和 outbitmao 相交时的模式  //包含
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //开始在画布上面画圆形bitmap
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return outBimap;


    }

    public int getUsedWidth(Bitmap bitmap) {
        if (usedWidth) {
            return bitmap.getWidth();
        } else {
            return bitmap.getHeight();
        }
    }
}
