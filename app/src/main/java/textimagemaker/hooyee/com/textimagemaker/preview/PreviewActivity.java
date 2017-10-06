package textimagemaker.hooyee.com.textimagemaker.preview;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import textimagemaker.hooyee.com.textimagemaker.AppApplication;
import textimagemaker.hooyee.com.textimagemaker.BaseActivity;
import textimagemaker.hooyee.com.textimagemaker.R;
import textimagemaker.hooyee.com.textimagemaker.util.Util;

public class PreviewActivity extends BaseActivity implements PreviewContract.View{
    private ImageView mPreviewIv;
    private PreviewPresenter mPresenter;
    private View mBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayoutToBase(R.layout.activity_preview);
        mToolbar.setVisibility(View.GONE);
//        mToolbar.setTranslationY(-200f);

        mPreviewIv = (ImageView) findViewById(R.id.iv_preview);
        mPreviewIv.setImageBitmap(Util.gContext.gBitmap);
        mPresenter = new PreviewPresenter(this, this);

        mPreviewIv.setOnClickListener(mPresenter);
        mBottom = findViewById(R.id.ly_bottom);
        findViewById(R.id.bt_share).setOnClickListener(mPresenter);
        findViewById(R.id.bt_save).setOnClickListener(mPresenter);

        Util.requestPermissions(this, new String[] {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });
    }

    public static void startItself(Context context) {
        Intent intent = new Intent(context, PreviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Bitmap getPreviewBitmap() {
        mPreviewIv.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(mPreviewIv.getDrawingCache());
        mPreviewIv.setDrawingCacheEnabled(false);
        return bmp;
    }

    @Override
    public void startToolbarAnimator() {
        if (mToolbar.getVisibility() == View.GONE) {
            mToolbar.setTranslationY(-200f);
            mToolbar.setVisibility(View.VISIBLE);
            mToolbar.animate().translationY(0).setDuration(500).start();
            mBottom.setVisibility(View.VISIBLE);
            mBottom.setAlpha(0);
            mBottom.animate().alpha(1).setDuration(500).start();
        } else {
            mToolbar.animate().translationY(-200f).setDuration(500).start();
            mBottom.animate().alpha(0).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mToolbar.setVisibility(View.GONE);
                    mBottom.setVisibility(View.GONE);
                    mBottom.animate().setListener(null);
                }
            }).start();
        }
    }
}
