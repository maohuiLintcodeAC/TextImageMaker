package textimagemaker.hooyee.com.textimagemaker;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    private TextView mTitleTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNoActionBar();
        setContentView(R.layout.activity_base);

        mTitleTx = (TextView) findViewById(R.id.tx_title);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setTitle(String title) {
//        mToolbar.setTitle(title);
        mTitleTx.setText(title);
    }

    protected void setNoActionBar() {

    }

    protected void addLayoutToBase(@LayoutRes int layoutResID){

        FrameLayout llContent = (FrameLayout) findViewById(R.id.contentFrame); //v_content是在基类布局文件中预定义的layout区域
//        通过LayoutInflater填充基类的layout区域
        View v = LayoutInflater.from(this).inflate(layoutResID, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        llContent.addView(v, params);
    }
}
