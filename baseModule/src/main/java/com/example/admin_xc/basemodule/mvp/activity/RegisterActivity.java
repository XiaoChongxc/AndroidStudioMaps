package com.example.admin_xc.basemodule.mvp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin_xc.basemodule.R;
import com.example.admin_xc.basemodule.base.BaseActivity;
import com.example.admin_xc.basemodule.mvp.contract.RegitsterContract;
import com.example.admin_xc.basemodule.mvp.presenter.RegisterPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp.activity
 * 日期   :   2017/6/6
 * 时间   ：  16:22
 * 描述   ：
 */

public class RegisterActivity extends BaseActivity implements RegitsterContract.View {

    RegitsterContract.Presenter presenter;
    @Bind(R.id.ed_name)
    EditText edName;
    @Bind(R.id.ed_pwd)
    EditText edPwd;
    @Bind(R.id.ed_varify)
    EditText edVarify;
    @Bind(R.id.tv_getcode)
    TextView tvGetcode;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        new RegisterPresenter(this);
    }

    @OnClick(R.id.tv_getcode)
    void getCode() {
        presenter.onSendSms();
    }

    @OnClick(R.id.btn_register)
    void onRegister() {
        presenter.onRegister();
    }

    @Override
    public String getSmsCode() {
        return edVarify.getText().toString();
    }

    @Override
    public String getUserName() {
        return edName.getText().toString();
    }

    @Override
    public String getPwd() {
        return edPwd.getText().toString();
    }

    @Override
    public void setSendText() {

    }

    @Override
    public void setSurButton() {

    }

    @Override
    public void setTitle() {


    }

    @Override
    public void setPresenter(RegitsterContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
