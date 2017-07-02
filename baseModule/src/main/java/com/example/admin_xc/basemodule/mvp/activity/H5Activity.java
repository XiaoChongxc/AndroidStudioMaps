package com.example.admin_xc.basemodule.mvp.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.example.admin_xc.basemodule.R;
import com.example.admin_xc.basemodule.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp.activity
 * 日期   :   2017/6/20
 * 时间   ：  11:47
 * 描述   ：
 */

public class H5Activity extends BaseActivity {
    @Bind(R.id.web)
    WebView web;
    @Bind(R.id.ll)
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        ButterKnife.bind(this);
//        web.loadUrl("file:///android_asset/aaa_httt.html");
        ll.setBackgroundDrawable(new BitmapDrawable(getResources(), createBitmap(700, 1000)));


//        LayerDrawable layerDrawable = (LayerDrawable) rl.getBackground();
////        ShapeDrawable drawable= (ShapeDrawable) layerDrawable.getDrawable(1);
////        drawable.setIntrinsicHeight(Utility.dip2px(getContext(),500));
//        layerDrawable.setLayerHeight(0, Utility.dip2px(getContext(), 150));
//        layerDrawable.setLayerHeight(1, Utility.dip2px(getContext(), 50));
//        layerDrawable.setLayerInset(1, 0, Utility.dip2px(getContext(), 150), 0, 0);

    }

    public Bitmap createBitmap(int width, int heigt) {
        Bitmap bit = Bitmap.createBitmap(width, heigt, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bit);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0, 0, 0, heigt, new int[]{Color.parseColor("#FFFFFF"), Color.parseColor("#000000")}, new float[]{0.5f, 0.5f}, Shader.TileMode.CLAMP));
        canvas.drawRect(0, 0, width, heigt, paint);
        return bit;
    }


}
