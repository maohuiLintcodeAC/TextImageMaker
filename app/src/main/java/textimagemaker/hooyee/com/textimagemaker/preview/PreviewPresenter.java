package textimagemaker.hooyee.com.textimagemaker.preview;

import android.Manifest;
import android.content.Context;
import android.view.View;

import java.io.File;

import textimagemaker.hooyee.com.textimagemaker.Constants;
import textimagemaker.hooyee.com.textimagemaker.R;
import textimagemaker.hooyee.com.textimagemaker.util.Util;

/**
 * Created by Hooyee on 2017/10/5.
 * mail: hooyee_moly@foxmail.com
 */

public class PreviewPresenter implements View.OnClickListener {
    private Context mContext;
    private PreviewContract.View mView;
    private State mState = State.NONE;
    private File mFile;

    public PreviewPresenter(Context context, PreviewContract.View view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_share:
                if (mFile == null) {
                    Util.toast(mContext.getResources().getString(R.string.tip_save));
                    break;
                }
                Util.shareImage(mFile);
                break;
            case R.id.iv_preview:
                mView.startToolbarAnimator();
                break;
            case R.id.bt_save:
                if(!Util.checkPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Util.requestPermissions(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    break;
                }
                if (mState != State.NONE) {
                    Util.toast(mContext.getResources().getString(R.string.saved));
                    break;
                }
                mState = State.SAVING;
                File file = Util.saveBitmap(mView.getPreviewBitmap(), Constants.PATH_DIR);
                mState = State.SAVED;
                if (file != null) {
                    mFile = file;
                    Util.toast(mContext.getResources().getString(R.string.succeed_save));
                } else {
                    Util.toast(mContext.getResources().getString(R.string.failed_save));
                }
                break;
        }
    }

    enum State {
        SAVING,
        SAVED,
        NONE
    }
}
