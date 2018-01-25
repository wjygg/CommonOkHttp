package com.lzy.commonhttpsdk;

/**
 * Created by wangjingyun on 2018/1/4.
 */

public interface HttpCallBackProgress extends HttpCallBack {

    //下载进度
    public void onProgress(int progress);
}
