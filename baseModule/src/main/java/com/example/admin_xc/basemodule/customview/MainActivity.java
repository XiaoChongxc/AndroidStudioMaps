package com.example.admin_xc.basemodule.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin_xc.basemodule.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaaa);
        MyScrollView myScrollView = (MyScrollView) findViewById(R.id.myscrollview);
        LinePathView linePathView = (LinePathView) findViewById(R.id.sign);
        myScrollView.setView(linePathView);
    }
}
