package textimagemaker.hooyee.com.textimagemaker.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.Layout;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import textimagemaker.hooyee.com.textimagemaker.AppApplication;
import textimagemaker.hooyee.com.textimagemaker.widget.TextDrawable;

/**
 * Created by Hooyee on 2017/10/2.
 * mail: hooyee_moly@foxmail.com
 */

public class Util {
    public static AppApplication gContext;

    public static Drawable createDrawable(String content, int width, int height, int color) {
        return new TextDrawable(content, width, height, color);
    }

    public static Drawable createDrawable(String content, int width, int height, int color, Layout.Alignment alignment) {
        return new TextDrawable(content, width, height, color, alignment);
    }

    public static boolean checkPermission(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public static void requestPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context,
                        permissions,
                        1);
                break;
            }
        }
    }

    public static void toast(String msg) {
        Toast.makeText(gContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    public static File saveBitmap(Bitmap bmp, String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, System.currentTimeMillis() + ".png");
        try {
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            Util.gContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            Toast.makeText(Util.gContext, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public static void shareMultiplePictureToTimeLine(File... files) {
        Intent intent = new Intent();
//        ComponentName comp = new ComponentName("com.tencent.mm",
//                "com.tencent.mm.ui.tools.ShareToTimeLineUI");
//        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");

        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        for (File f : files) {
            Uri uri;
            if (Build.VERSION.SDK_INT >= 24) {
                uri = FileProvider.getUriForFile(gContext, "textimagemaker.hooyee.com.textimagemaker.fileprovider", f);
            } else {
                uri = Uri.fromFile(f);
            }
            imageUris.add(uri);
        }
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        intent.putExtra("Kdescription", "wwwwwwwwwwwwwwwwwwww");
//        gContext.startActivity(intent);
        gContext.startActivity(Intent.createChooser(intent, "Share"));
    }

    public static void shareImage(File... files) {
        Intent intent = new Intent();
//        ComponentName comp = new ComponentName("com.tencent.mm",
//                "com.tencent.mm.ui.tools.ShareToTimeLineUI");
//        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");

        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        for (File f : files) {
            Uri uri = Uri.fromFile(f);
            imageUris.add(uri);
        }
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        //文字描述
//        intent.putExtra("Kdescription", "wwwwwwwwwwwwwwwwwwww");
        gContext.startActivity(Intent.createChooser(intent, "Share"));
    }

}
