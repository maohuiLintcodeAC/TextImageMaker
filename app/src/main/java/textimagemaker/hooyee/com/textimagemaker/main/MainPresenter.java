package textimagemaker.hooyee.com.textimagemaker.main;

import android.content.Context;
import android.view.View;

import textimagemaker.hooyee.com.textimagemaker.preview.PreviewActivity;
import textimagemaker.hooyee.com.textimagemaker.R;

/**
 * Created by Hooyee on 2017/9/30.
 */

public class MainPresenter implements View.OnClickListener{
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
                PreviewActivity.startItself(mContext);
                break;
            default:
                break;
        }
    }
}
