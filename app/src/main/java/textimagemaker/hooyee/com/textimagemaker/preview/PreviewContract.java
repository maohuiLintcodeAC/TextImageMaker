package textimagemaker.hooyee.com.textimagemaker.preview;

import android.graphics.Bitmap;

/**
 * Created by Hooyee on 2017/10/5.
 * mail: hooyee_moly@foxmail.com
 */

public interface PreviewContract {
    interface Presenter {

    }

    interface View {
        void startToolbarAnimator();
        Bitmap getPreviewBitmap();
    }
}
