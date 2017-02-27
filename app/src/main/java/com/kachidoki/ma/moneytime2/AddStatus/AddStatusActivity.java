package com.kachidoki.ma.moneytime2.AddStatus;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kachidoki.ma.moneytime2.AddStatus.Di.AddStatusModule;
import com.kachidoki.ma.moneytime2.AddStatus.Di.DaggerAddStatusComponent;
import com.kachidoki.ma.moneytime2.App.App;
import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
import com.kachidoki.ma.moneytime2.R;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/22.
 */

public class AddStatusActivity extends BaseActivity implements AddStatusContract.View {
    private static final int IMAGE_PICK_REQUEST = 0;
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
    }

    @OnClick({R.id.addstatus_img, R.id.addstatus_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addstatus_img:
                pickImage(this,IMAGE_PICK_REQUEST);
                break;
            case R.id.addstatus_send:
                presenter.send(addstatusMessage.getText().toString());
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
        finish();
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
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == IMAGE_PICK_REQUEST) {
                Uri uri = data.getData();
                try {
                    presenter.setBitmip(MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void pickImage(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, requestCode);
    }
}
