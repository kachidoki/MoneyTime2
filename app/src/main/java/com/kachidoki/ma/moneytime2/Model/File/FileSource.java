package com.kachidoki.ma.moneytime2.Model.File;

/**
 * Created by mayiwei on 2017/2/27.
 */

public interface FileSource {

    public interface FileCallback{
        void sucess(String url);
        void fail(Exception e);
    }

    void upload(final byte[] data, final String key, final String token,FileCallback fileCallback);

    String getToken();

}
