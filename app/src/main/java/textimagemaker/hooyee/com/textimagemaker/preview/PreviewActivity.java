package textimagemaker.hooyee.com.textimagemaker.preview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import textimagemaker.hooyee.com.textimagemaker.BaseActivity;
import textimagemaker.hooyee.com.textimagemaker.R;

public class PreviewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayoutToBase(R.layout.activity_preview);
    }

    public static void startItself(Context context) {
        Intent intent = new Intent(context, PreviewActivity.class);
        context.startActivity(intent);
    }
}
