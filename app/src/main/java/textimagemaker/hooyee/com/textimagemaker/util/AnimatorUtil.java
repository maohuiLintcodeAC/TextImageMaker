package textimagemaker.hooyee.com.textimagemaker.util;

import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by Hooyee on 2017/10/7.
 * mail: hooyee_moly@foxmail.com
 */

public class AnimatorUtil {
    public static void bounceAnimator(View view, long duration) {
        view.setRotation(-50);
        view.setAlpha(1);
        view.animate().setInterpolator(new BounceInterpolator()).rotation(20).setDuration(duration).start();
    }
}
