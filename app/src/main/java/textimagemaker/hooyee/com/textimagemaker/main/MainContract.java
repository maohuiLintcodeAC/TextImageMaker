package textimagemaker.hooyee.com.textimagemaker.main;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Hooyee on 2017/9/30.
 */

public interface MainContract {
    interface Presenter extends android.view.View.OnClickListener{

    }

    interface View {
        String getTextContent();

        void updateContent(String content);

        void clearContent();

        void updatePreview(Drawable drawable);

        void updateAlignPolicy();

        Bitmap getPreviewBitmap();

        int getAlignPolicy();
    }
}
