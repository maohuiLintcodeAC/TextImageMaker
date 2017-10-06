package textimagemaker.hooyee.com.textimagemaker.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import textimagemaker.hooyee.com.textimagemaker.Constants;
import textimagemaker.hooyee.com.textimagemaker.R;
import textimagemaker.hooyee.com.textimagemaker.preview.PreviewActivity;
import textimagemaker.hooyee.com.textimagemaker.util.FlagModel;
import textimagemaker.hooyee.com.textimagemaker.util.Util;

/**
 * Created by Hooyee on 2017/9/30.
 */

public class MainPresenter implements MainContract.Presenter {
    private Context mContext;
    private MainContract.View mView;

    public MainPresenter(Context context, MainContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_clear:
                mView.clearContent();
                break;
            case R.id.bt_preview:
                preview();
                break;
            case R.id.iv_align_policy:
                mView.updateAlignPolicy();
                preview();
                break;
            case R.id.iv_preview:
                Util.gContext.gBitmap = mView.getPreviewBitmap();
                PreviewActivity.startItself(mContext);
                break;
            default:
                break;
        }
    }

    private void preview() {
        Layout.Alignment alignment = verifyAlignment(mView.getAlignPolicy());
        Drawable drawable = Util.createDrawable(mView.getTextContent(), Util.getScreenWidth(mContext), Util.getScreenHeight(mContext), FlagModel.getInt(mContext, Constants.TEXT_COLOR, Color.WHITE), alignment);
        mView.updatePreview(drawable);
    }

    private Layout.Alignment verifyAlignment(int gravity) {
        Layout.Alignment alignment;
        switch (gravity) {
            case Gravity.CENTER:
                alignment = Layout.Alignment.ALIGN_CENTER;
                break;
            case Gravity.LEFT:
                alignment = Layout.Alignment.ALIGN_NORMAL;
                break;
            case Gravity.RIGHT:
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
                break;
            default:
                alignment = Layout.Alignment.ALIGN_CENTER;
                break;
        }
        return alignment;
    }

}
