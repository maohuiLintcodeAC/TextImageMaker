package textimagemaker.hooyee.com.textimagemaker;

import android.app.Application;
import android.graphics.Bitmap;

import textimagemaker.hooyee.com.textimagemaker.util.Util;

/**
 * Created by Hooyee on 2017/10/3.
 * mail: hooyee_moly@foxmail.com
 */

public class AppApplication extends Application {
    public Bitmap gBitmap;

    @Override
    public void onCreate() {
        super.onCreate();
        Util.gContext = this;
    }
}
