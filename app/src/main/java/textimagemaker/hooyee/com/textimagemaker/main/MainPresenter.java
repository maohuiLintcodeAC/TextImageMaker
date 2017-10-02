package textimagemaker.hooyee.com.textimagemaker.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;

import textimagemaker.hooyee.com.textimagemaker.R;
import textimagemaker.hooyee.com.textimagemaker.util.Util;
import textimagemaker.hooyee.com.textimagemaker.widget.TextDrawable;

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
//                PreviewActivity.startItself(mContext);
                Drawable drawable = Util.createDrawable(mView.getTextContent(), Util.getScreenWidth(mContext), Util.getScreenHeight(mContext), Color.WHITE);
                Log.i("TAG", "boolean: " + mView.getTextContent().contains("\n"));
                mView.updatePreview(drawable);
                break;
            default:
                break;
        }
    }

}
