package com.feximin.mvp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Feximin on 2018/8/4.
 */
public class SampleLoginActivity extends BaseMvpActivity<SampleLoginContractor.Presenter> implements  SampleLoginContractor.View{

    private EditText mEtName, mEtPassword;
    private Button mBtLogin;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        this.mEtName = findViewById(R.id.et_name);
        this.mBtLogin = findViewById(R.id.bt_login);
        this.mBtLogin.setOnClickListener(v -> mHostPresenter.login(mEtName.getText().toString(), mEtPassword.getText().toString()));
    }

    @Override
    SampleLoginContractor.Presenter generateHostPresenter() {
        return new SampleLoginContractor.Presenter(this);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed(int errCode) {
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }
}
