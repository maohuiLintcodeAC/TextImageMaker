package textimagemaker.hooyee.com.textimagemaker.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.WindowManager;

import textimagemaker.hooyee.com.textimagemaker.widget.TextDrawable;

/**
 * Created by Hooyee on 2017/10/2.
 * mail: hooyee_moly@foxmail.com
 */

public class Util {
    public static Drawable createDrawable(String content, int width, int height, int color) {
        return new TextDrawable(content, width, height, color);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }
}
