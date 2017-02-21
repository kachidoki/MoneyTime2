package com.kachidoki.ma.moneytime2.RegLogin.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kachidoki.ma.moneytime2.Add.AddContract;
import com.kachidoki.ma.moneytime2.App.App;
import com.kachidoki.ma.moneytime2.App.AppConstant;
import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.RegLogin.Register.Di.DaggerRegisterComponent;
import com.kachidoki.ma.moneytime2.RegLogin.Register.Di.RegisterModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/21.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {


    @Inject
    RegisterContract.Presenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.register_num)
    EditText registerNum;
    @BindView(R.id.register_pass)
    EditText registerPass;
    @BindView(R.id.register_pass_re)
    EditText registerPassRe;
    @BindView(R.id.register)
    Button register;

    @Override
    protected void setupActivityComponent() {
        DaggerRegisterComponent.builder().appComponent(((App) getApplication()).getAppComponent())
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter.start();

    }


    @Override
    public void showNameIsShort() {
        Toast.makeText(RegisterActivity.this, "用户名过短",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNameIsLong() {
        Toast.makeText(RegisterActivity.this, "用户名太长了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPassIsNot() {
        Toast.makeText(RegisterActivity.this, "请输入超过6位的密码",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPassIsNotSame() {
        Toast.makeText(RegisterActivity.this, "两次密码不一致",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSucess() {
        Toast.makeText(RegisterActivity.this, "注册成功",Toast.LENGTH_SHORT).show();
        setResult(AppConstant.RegisterSuccess);
        finish();
    }

    @Override
    public void registerFail(Exception e) {
        Toast.makeText(RegisterActivity.this, "注册失败",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.register)
    public void onClick() {
        register(registerNum.getText().toString(),registerPass.getText().toString(),registerPassRe.getText().toString());
    }

    private void register(String name,String pass,String passRe){
        if (presenter.checkInput(name,pass,passRe)){
            presenter.Login(name,pass);
        }
    }
}
