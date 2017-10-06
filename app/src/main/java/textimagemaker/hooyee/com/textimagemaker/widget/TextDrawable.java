package textimagemaker.hooyee.com.textimagemaker.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import textimagemaker.hooyee.com.textimagemaker.util.StringUtil;

/**
 * Created by Hooyee on 2017/10/2.
 * mail: hooyee_moly@foxmail.com
 */

public class TextDrawable extends Drawable {
    private String mContent;
    private int mWidth;
    private int mHeight;
    private TextPaint mPaint;
    private Layout.Alignment mAlign;

    public TextDrawable(String content, int width, int height, int color) {
        this(content, width, height, color,Layout.Alignment.ALIGN_CENTER);
    }

    public TextDrawable(String content, int width, int height, int color, Layout.Alignment align) {
        this.mContent = content;
        this.mWidth = width;
        this.mHeight = height;
        this.mPaint = new TextPaint();
        this.mAlign = align;
        mPaint.setColor(color);
        mPaint.setTextSize(64);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        switch (mAlign) {
            case ALIGN_NORMAL:
                break;
            case ALIGN_OPPOSITE:
                mContent = StringUtil.reverseString(mContent);
                break;
            case ALIGN_CENTER:
                break;
        }
        //文字自动换行

        StaticLayout layout = new StaticLayout(mContent, mPaint, canvas.getWidth(), mAlign, 1.0F, 0.0F,true);
        canvas.save();
        int y = (canvas.getHeight() - layout.getHeight()) / 2;
        canvas.translate(0, y);
//        mPaint.setTextAlign(mAlign);
//        mPaint.setTextAlign(Paint.Align.RIGHT);
        // 文字宽度
        float textWidth =  mPaint.measureText(mContent);
        layout.draw(canvas);
        Log.i("TAG", "layout.height = " + layout.getHeight());
        Log.i("TAG", "canvas.height = " + canvas.getHeight());
        canvas.restore();
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

}
