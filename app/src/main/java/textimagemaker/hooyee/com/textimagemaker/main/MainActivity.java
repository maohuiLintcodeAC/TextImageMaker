package textimagemaker.hooyee.com.textimagemaker.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import textimagemaker.hooyee.com.textimagemaker.R;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private MainPresenter mPresenter;
    private EditText mEdit;
    private ImageView mPreviewIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this, this);

        mEdit = (EditText) findViewById(R.id.et_content);
        mPreviewIv = (ImageView) findViewById(R.id.iv_preview);

        findViewById(R.id.bt_preview).setOnClickListener(mPresenter);
        findViewById(R.id.iv_align_policy).setOnClickListener(mPresenter);
        findViewById(R.id.bt_clear).setOnClickListener(mPresenter);

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

        Log.i("TAG", mPreviewIv.getDrawable().getBounds().toString());
    }
}
