package textimagemaker.hooyee.com.textimagemaker.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import textimagemaker.hooyee.com.textimagemaker.R;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private MainPresenter mPresenter;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this, this);

        mEdit = (EditText) findViewById(R.id.et_content);
        findViewById(R.id.bt_preview).setOnClickListener(mPresenter);
        findViewById(R.id.iv_align_policy).setOnClickListener(mPresenter);
        findViewById(R.id.bt_clear).setOnClickListener(mPresenter);
    }

    @Override
    public String getTextContent() {
        return mEdit.getText().toString();
    }

    @Override
    public void clearContent() {
        mEdit.setText("");
    }
}
