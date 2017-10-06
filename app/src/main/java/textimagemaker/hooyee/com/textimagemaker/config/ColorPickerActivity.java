package textimagemaker.hooyee.com.textimagemaker.config;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;

import textimagemaker.hooyee.com.textimagemaker.R;
import textimagemaker.hooyee.com.textimagemaker.util.FlagModel;
import textimagemaker.hooyee.com.textimagemaker.util.Util;


public class ColorPickerActivity extends Activity {
    private static String sParam = "param";
    private String mKey;
    Button mSaveBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        mKey = getIntent().getStringExtra(sParam);

        final ColorPicker picker = (ColorPicker) findViewById(R.id.picker);
        SVBar svBar = (SVBar) findViewById(R.id.svbar);
        OpacityBar opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
        SaturationBar saturationBar = (SaturationBar) findViewById(R.id.saturationbar);

        picker.addSVBar(svBar);
        picker.addOpacityBar(opacityBar);
        picker.addSaturationBar(saturationBar);

//To get the color
        int oldColor = picker.getColor();

//To set the old selected color u can do it like this
        picker.setOldCenterColor(FlagModel.getInt(this, mKey, oldColor));

        picker.setShowOldCenterColor(true);

        mSaveBt = (Button) findViewById(R.id.bt_save);
        mSaveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = picker.getColor();
                FlagModel.putInt(ColorPickerActivity.this, mKey, color);
                Util.toast("Color Set Success");
                onBackPressed();
            }
        });

    }

    public static void startItself(Context context, String msg) {
        Intent intent = new Intent(context, ColorPickerActivity.class);
        intent.putExtra(sParam, msg);
        context.startActivity(intent);
    }

}
