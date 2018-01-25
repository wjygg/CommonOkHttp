package com.lzy.commonhttpsdk;

/**
 * Created by wangjingyun on 2017/11/29.
 */

public interface HttpCallBack {

    public void onError(Exception e);

    public void onSucceed(Object result);

}
