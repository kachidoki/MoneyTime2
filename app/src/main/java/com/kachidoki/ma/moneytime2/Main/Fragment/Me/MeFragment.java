package com.kachidoki.ma.moneytime2.Main.Fragment.Me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kachidoki.ma.moneytime2.About.AboutActivity;
import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Me.Di.MeModule;
import com.kachidoki.ma.moneytime2.Main.MainActivity;
import com.kachidoki.ma.moneytime2.Model.User.User;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.RegLogin.Login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class MeFragment extends BaseLazyFragment implements MeContract.View {

    @Inject
    MeContract.Presenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.me_userme)
    ImageView meUserme;
    @BindView(R.id.me_mystatus)
    TextView meMystatus;
    @BindView(R.id.me_myfollowing)
    TextView meMyfollowing;
    @BindView(R.id.me_myfollower)
    TextView meMyfollower;
    @BindView(R.id.me_userstatus)
    LinearLayout meUserstatus;
    @BindView(R.id.me_isusenet)
    Switch meIsusenet;
    @BindView(R.id.me_userName)
    TextView meUserName;
    @BindView(R.id.me_logout)
    LinearLayout meLogout;
    @BindView(R.id.me_setting)
    LinearLayout meSetting;
    @BindView(R.id.me_about)
    LinearLayout meAbout;


    @Override
    protected void setupComponent(Context context) {
        ((MainActivity) context).getMainComponent()
                .plus(new MeModule(this))
                .inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("个人");
        return view;
    }


    @Override
    public void onLazyLoad() {
        presenter.start();
    }

    @OnClick({R.id.me_mystatus, R.id.me_myfollowing, R.id.me_myfollower, R.id.me_userme, R.id.me_logout,R.id.me_setting, R.id.me_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_mystatus:
                break;
            case R.id.me_myfollowing:
                Toast.makeText(getContext(),"正在完善敬请期待",Toast.LENGTH_LONG).show();
                break;
            case R.id.me_myfollower:
                Toast.makeText(getContext(),"正在完善敬请期待",Toast.LENGTH_LONG).show();
                break;
            case R.id.me_userme:
                presenter.OnUserImgClick();
                break;
            case R.id.me_logout:
                presenter.logout();
                break;
            case R.id.me_setting:
                Toast.makeText(getContext(),"正在完善敬请期待",Toast.LENGTH_LONG).show();
                break;
            case R.id.me_about:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
        }
    }

    @Override
    public void showNotLogin() {
        meUserName.setText("请先登录");
        meUserme.setImageDrawable(getResources().getDrawable(R.drawable.icon_nolog));
        meUserstatus.setVisibility(View.GONE);
        meLogout.setVisibility(View.GONE);
    }

    @Override
    public void showLogin() {
        presenter.loadLoginUser();
        meLogout.setVisibility(View.VISIBLE);
        meUserstatus.setVisibility(View.VISIBLE);
    }


    @Override
    public void setStatusNum(int num) {
        meMystatus.setText("动态 " + num);
    }

    @Override
    public void setFollowerNum(int num) {
        meMyfollower.setText("粉丝 " + num);
    }

    @Override
    public void setFollowingNum(int num) {
        meMyfollowing.setText("关注 " + num);
    }


    @Override
    public void setUser(User user) {
        meUserName.setText(user.username());
        meUserme.setImageDrawable(getResources().getDrawable(R.drawable.icon_usernoimg));
    }

    @Override
    public void goToConfig() {

    }

    @Override
    public void gotoLogin() {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    @Override
    public void showLogout() {
        Toast.makeText(getContext(), "已退出", Toast.LENGTH_LONG).show();
        showNotLogin();
    }
}
