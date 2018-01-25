package com.lzy.commonhttpsdk;

import android.content.Context;
import android.text.TextUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangjingyun on 2017/11/29.
 */

public class HttpUtils {
    // 上下文
    private Context mContext;
    // 网络访问引擎 多态
    private static HttpEngine mHttpEngine = new OkHttpEngine();
    // 接口地址
    private String mUrl;
    //下载文件路径
    private String downLoadFilsUrl;
    // 请求参数
    private Map<String, String> mParams;
    //上传文件
    private Map<String, Object> fileParams;
    // get请求标识
    private final int GET_REQUEST = 0x0011;
    // post请求标识
    private final int POST_REQUEST = 0x0022;
    //上传文件
    private final int UPLOAD_FILES = 0x0033;
    //下载文件
    private final int DOWNLOAD_FILES = 0x0044;

    // 请求的方式
    private int mRequestMethod = GET_REQUEST;

    // 是否缓存
    private boolean mCache = false;

    // 切换引擎
    public void exchangeEngine(OkHttpEngine httpEngine){
        this.mHttpEngine = httpEngine;
    }

    private HttpUtils(Context context) {
        this.mContext = context;
        mParams = new ConcurrentHashMap<>();
    }

    // 可以在Application中配置HttpEngine
    public static void initEngine(HttpEngine httpEngine){
          mHttpEngine = httpEngine;
    }


    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    public HttpUtils addParams(String key,String values){
        if(mParams!=null){
            mParams.put(key,values);
        }
        return this;
    }

    public HttpUtils initFileParams(){
        fileParams=new ConcurrentHashMap<>();
        return this;
    }

    public HttpUtils addFileParams(String key,Object values){

        if(fileParams!=null){

            fileParams.put(key,values);
        }
        return this;
    }

    public HttpUtils url(String url) {
        mUrl = url;
        return this;
    }

    /**
     * 保存路径
     * @param url
     * @return
     */
    public HttpUtils downLoadFilsUrl(String url) {
        downLoadFilsUrl = url;
        return this;
    }

    /**
     * get
     * @return
     */
    public HttpUtils get(){
        mRequestMethod=GET_REQUEST;
        return this;
    }
    /**
     * post
     * @return
     */
    public HttpUtils post(){
        mRequestMethod=POST_REQUEST;
        return this;
    }
    /**
     * 上传
     * @return
     */
    public HttpUtils UploadFiles(){
        mRequestMethod=UPLOAD_FILES;
        return this;
    }

    /**
     * 下载文件
     * @return
     */
    public HttpUtils downLoadFiles(){
        mRequestMethod=DOWNLOAD_FILES;
        return this;
    }

    /**
     * 取消所有请求
     * @return
     */
    public void cancelAll(){
        mHttpEngine.cancelAll();
    }

    // 执行方法
    public void execute(HttpCallBack httpCallBack) {
        if (TextUtils.isEmpty(mUrl)) {
            throw new NullPointerException("访问路径不能为空");
        }

        if (mRequestMethod == GET_REQUEST) {
            mHttpEngine.get(mContext,mUrl, mParams, httpCallBack,false);
        }

        if (mRequestMethod == POST_REQUEST) {
            mHttpEngine.post(mContext,mUrl, mParams, httpCallBack,false);
        }

        if(mRequestMethod==UPLOAD_FILES){
            mHttpEngine.sendMultipart(mContext,mUrl,fileParams,httpCallBack,false);
        }
    }

    // 下载进度执行方法
    public void execute(HttpCallBackProgress httpCallBackProgress) {

        if (TextUtils.isEmpty(mUrl)) {
            throw new NullPointerException("访问路径不能为空");
        }

        if (TextUtils.isEmpty(downLoadFilsUrl)) {
            throw new NullPointerException("保存路径不能为空");
        }

        if(mRequestMethod==DOWNLOAD_FILES){
            mHttpEngine.downLoadFiles(mContext,mUrl,downLoadFilsUrl,httpCallBackProgress);
        }
    }



}
