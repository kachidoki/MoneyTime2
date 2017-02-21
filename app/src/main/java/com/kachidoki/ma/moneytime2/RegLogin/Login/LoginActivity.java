package com.kachidoki.ma.moneytime2.RegLogin.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kachidoki.ma.moneytime2.App.App;
import com.kachidoki.ma.moneytime2.App.AppConstant;
import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.RegLogin.Login.Di.DaggerLoginComponent;
import com.kachidoki.ma.moneytime2.RegLogin.Login.Di.LoginModule;
import com.kachidoki.ma.moneytime2.RegLogin.Register.RegisterActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/21.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {


    @Inject
    LoginContract.Presenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_num)
    EditText loginNum;
    @BindView(R.id.login_pass)
    EditText loginPass;
    @BindView(R.id.find_password)
    TextView findPassword;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login)
    Button login;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter.start();

    }


    @Override
    protected void setupActivityComponent() {
        DaggerLoginComponent.builder().appComponent(((App) getApplication()).getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @OnClick({R.id.find_password, R.id.login_register,R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_password:
                break;
            case R.id.login_register:
                startActivityForResult(new Intent(LoginActivity.this,RegisterActivity.class),AppConstant.ResquestRegister);
                break;
            case R.id.login:
                if (presenter.checkInput(loginNum.getText().toString(),loginPass.getText().toString())){
                    presenter.Login(loginNum.getText().toString(),loginPass.getText().toString());
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== AppConstant.ResquestRegister&&resultCode==AppConstant.RegisterSuccess){
//            Intent intent = new Intent();
//            intent.putExtra("name",data.getStringExtra("name"));
//            setResult(AppConstant.LoginSuccess,intent);
            finish();
        }
    }

    @Override
    public void showNameIsNotOk() {
        Toast.makeText(LoginActivity.this, "请输入正确的用户名",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPassWordIsNotOk() {
        Toast.makeText(LoginActivity.this, "请输入6-12位密码",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNameOrPassIsNo() {
        Toast.makeText(LoginActivity.this, "请填写用户名和密码",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginSucess() {
        Toast.makeText(LoginActivity.this, "登录成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void LoginFail() {
        Toast.makeText(LoginActivity.this, "登录验证失败",Toast.LENGTH_SHORT).show();
    }
}
