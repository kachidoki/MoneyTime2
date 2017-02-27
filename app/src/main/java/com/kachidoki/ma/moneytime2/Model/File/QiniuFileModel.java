package com.kachidoki.ma.moneytime2.Model.File;

import com.kachidoki.ma.qiniu.TokenUtils.Auth;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

/**
 * Created by mayiwei on 2017/2/27.
 */

public class QiniuFileModel implements FileSource {
    private String AK;
    private String SK;
    private String bucket;
    private UploadManager uploadManager;

    public QiniuFileModel(String AK, String SK, String bucket){
        this.AK = AK;
        this.SK = SK;
        this.bucket = bucket;
        uploadManager = new UploadManager();
    }

    @Override
    public void upload(byte[] data, String key, String token, final FileCallback fileCallback) {
        uploadManager.put(data, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                if (info.isOK()){
                    fileCallback.sucess();
                }else {
                    fileCallback.fail(null);
                }
            }
        },null);
    }

    @Override
    public String getToken() {
        return Auth.create(AK,SK).uploadToken(bucket);
    }


}
