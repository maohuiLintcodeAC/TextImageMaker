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

/**
 * Created by Hooyee on 2017/10/2.
 * mail: hooyee_moly@foxmail.com
 */

public class TextDrawable extends Drawable {
    private String mContent;
    private int mWidth;
    private int mHeight;
    private TextPaint mPaint;

    public TextDrawable(String content, int width, int height, int color) {
        this.mContent = content;
        this.mWidth = width;
        this.mHeight = height;
        this.mPaint = new TextPaint();
        mPaint.setColor(color);
        mPaint.setTextSize(48);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        //文字自动换行
        StaticLayout layout = new StaticLayout(mContent, mPaint, canvas.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F,true);
        canvas.save();
        int y = (canvas.getHeight() - layout.getHeight()) / 2;
        canvas.translate(0, y);
        mPaint.setTextAlign(Paint.Align.LEFT);
        //文字的位置
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
