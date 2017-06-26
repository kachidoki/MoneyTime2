package com.kachidoki.ma.moneytime2.AddStatus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kachidoki.ma.kimgpicker.KIMGPicker;
import com.kachidoki.ma.kimgpicker.KPCompressor;
import com.kachidoki.ma.kimgpicker.KPConfig;
import com.kachidoki.ma.kimgpicker.Loader.ImageLoader;
import com.kachidoki.ma.moneytime2.AddStatus.Di.AddStatusModule;
import com.kachidoki.ma.moneytime2.AddStatus.Di.DaggerAddStatusComponent;
import com.kachidoki.ma.moneytime2.App.App;
import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
import com.kachidoki.ma.moneytime2.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/22.
 */

public class AddStatusActivity extends BaseActivity implements AddStatusContract.View {
    private static final int IMAGE_PICK_REQUEST = 10001;
    @Inject
    AddStatusContract.Presenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.addstatus_message)
    EditText addstatusMessage;
    @BindView(R.id.addstatus_img)
    ImageView addstatusImg;
    @BindView(R.id.addstatus_send)
    Button addstatusSend;

    private KPConfig config;
    private KPCompressor compressor;

    @Override
    protected void setupActivityComponent() {
        DaggerAddStatusComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .addStatusModule(new AddStatusModule(this))
                .build().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstatus);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        config = new KPConfig.Builder(this)
                            .multiSelect(false)
                            .needCrop(true)
                            .cropSize(1,1,800,800)
                            .needCompressor(true)
                            .build();
        compressor = new KPCompressor(this).MaxSize(800,800).Quality(90);
    }

    @OnClick({R.id.addstatus_img, R.id.addstatus_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addstatus_img:
                KIMGPicker.GoPick(this,config,new GlideImageLoader(),compressor,IMAGE_PICK_REQUEST);
                break;
            case R.id.addstatus_send:
                presenter.send(addstatusMessage.getText().toString());
                finish();
                break;
        }
    }

    @Override
    public void showMessageNo() {
        Toast.makeText(AddStatusActivity.this,"发送不能为空",Toast.LENGTH_LONG).show();
    }

    @Override
    public void sendScuess() {
        Toast.makeText(AddStatusActivity.this,"发送成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void sendFail(Exception e) {
        Toast.makeText(AddStatusActivity.this,"发送失败",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBitmap(Bitmap bitmap) {
        addstatusImg.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMAGE_PICK_REQUEST&&resultCode==Activity.RESULT_OK) {
            ArrayList<String> res = data.getStringArrayListExtra(KIMGPicker.RESULT);
            presenter.setBitmip(BitmapFactory.decodeFile(res.get(0)));
        }
    }

    final class GlideImageLoader implements ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView, int width, int height) {
            Glide.with(context)
                    .load(Uri.fromFile(new File(path)))
                    .error(R.mipmap.default_image)
                    .placeholder(R.color.gray)
                    .into(imageView);
        }
    }
}
