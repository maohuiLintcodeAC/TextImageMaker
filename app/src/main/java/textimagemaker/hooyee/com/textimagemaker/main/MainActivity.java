package textimagemaker.hooyee.com.textimagemaker.main;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import textimagemaker.hooyee.com.textimagemaker.Constants;
import textimagemaker.hooyee.com.textimagemaker.R;
import textimagemaker.hooyee.com.textimagemaker.config.ColorPickerActivity;
import textimagemaker.hooyee.com.textimagemaker.util.AnimatorUtil;
import textimagemaker.hooyee.com.textimagemaker.util.FlagModel;
import textimagemaker.hooyee.com.textimagemaker.util.Util;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainPresenter mPresenter;
    private EditText mEdit;
    private ImageView mPreviewIv;
    private ImageView mAlignPolicyIv;
    private TextView mTipTx;

    private LinearLayout.LayoutParams mParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this, this);

        mEdit = (EditText) findViewById(R.id.et_content);
        mPreviewIv = (ImageView) findViewById(R.id.iv_preview);
        mAlignPolicyIv = (ImageView) findViewById(R.id.iv_align_policy);
        mTipTx = (TextView) findViewById(R.id.tx_tip);

        mPreviewIv.setOnClickListener(mPresenter);
        mAlignPolicyIv.setOnClickListener(mPresenter);

        findViewById(R.id.bt_clear).setOnClickListener(mPresenter);
        findViewById(R.id.bt_preview).setOnClickListener(mPresenter);

        mParams = (LinearLayout.LayoutParams) mEdit.getLayoutParams();
        mParams.gravity = Gravity.CENTER;
        mEdit.setLayoutParams(mParams);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //创建Menu菜单
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //对菜单项点击内容进行设置
        int id = item.getItemId();
        if (id == R.id.set_bg_color) {
            Util.toast(getResources().getString(R.string.pick_color_bg));
            ColorPickerActivity.startItself(this, Constants.BG_COLOR);
        } else if (id == R.id.set_text_color) {
            Util.toast(getResources().getString(R.string.pick_color_text));
            ColorPickerActivity.startItself(this, Constants.TEXT_COLOR);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        mPreviewIv.setBackgroundColor(FlagModel.getInt(this, Constants.BG_COLOR, getResources().getColor(R.color.bt_color)));
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public String getTextContent() {
        return mEdit.getText().toString();
    }

    @Override
    public void updateContent(String content) {
        mEdit.setText(content);
    }

    @Override
    public void clearContent() {
        mEdit.setText("");
    }

    @Override
    public void updatePreview(Drawable drawable) {
        mPreviewIv.setImageDrawable(drawable);
        AnimatorUtil.bounceAnimator(mTipTx, 1000);
        mTipTx.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                mTipTx.setVisibility(View.GONE);
                mTipTx.animate().alpha(0).setInterpolator(new LinearInterpolator()).start();
            }
        }, 1200);
    }

    @Override
    public void updateAlignPolicy() {
        switch (mParams.gravity) {
            case Gravity.CENTER:
                mAlignPolicyIv.setImageResource(R.drawable.align_right);
                mParams.gravity = Gravity.RIGHT;
                mEdit.setGravity(Gravity.RIGHT);
                break;
            case Gravity.LEFT:
                mAlignPolicyIv.setImageResource(R.drawable.align_center);
                mParams.gravity = Gravity.CENTER;
                mEdit.setGravity(Gravity.CENTER);
                break;
            case Gravity.RIGHT:
                mAlignPolicyIv.setImageResource(R.drawable.align_left);
                mParams.gravity = Gravity.LEFT;
                mEdit.setGravity(Gravity.LEFT);
                break;
        }
    }

    @Override
    public Bitmap getPreviewBitmap() {
        mPreviewIv.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(mPreviewIv.getDrawingCache());
        mPreviewIv.setDrawingCacheEnabled(false);
        return bmp;
    }

    @Override
    public int getAlignPolicy() {
        return mParams.gravity;
    }
}
