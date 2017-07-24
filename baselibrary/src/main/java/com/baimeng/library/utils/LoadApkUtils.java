package com.baimeng.library.utils;

import android.app.Activity;
import android.os.Environment;
import android.widget.ProgressBar;

import com.sinieco.shougang.application;
import com.sinieco.shougang.bean.AVersionDto;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * Created by BaiMeng on 2017/5/17.
 */
public class LoadApkUtils {
    private ProgressBar bar ;

    private LoadApkUtils() {
    }

//    private interface CancelLoadApkListener{
//        void onCancel();
//    }
//    private CancelLoadApkListener listener ;
//    public void setCancelLoadApkListener (CancelLoadApkListener listener){
//        this.listener = listener ;
//    }
    private static LoadApkUtils instance ;
    public static synchronized LoadApkUtils getInstance(){
        if(instance == null){
            instance = new LoadApkUtils() ;
        }
        return instance ;
    }
    public void loadApk(final AVersionDto version ,Activity act){
        int index = version.url.lastIndexOf("/");
        String appName = version.url.substring(index + 1, version.url.length());
        OkHttpUtils.post().url(NetUtils.LE_LE  + version.url)
                .tag(this)//
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gangda/",appName) {
                    @Override
                    public void inProgress(final float progress, long total) {
                        application.loading = true ;
                        if(listener!=null){
                            listener.onApkLoading(progress);
                        }

                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        if(listener != null){
                            listener.onError();
                        }
                    }

                    @Override
                    public void onResponse(File response) {
                        application.loading = false ;
                        if(listener != null){
                            listener.onApkLoaded(response);
                        }
                    }
                });

    }
    private LoadApkListener listener ;
    public interface LoadApkListener{
         void onApkLoading(float progress);
         void onApkLoaded (File file);
         void onError();

    }
    public void setLoadApkListener(LoadApkListener listener){
        this.listener = listener ;
    }
    public void setProgressBar(ProgressBar bar){
        this.bar = bar ;
        bar.setMax(100);
    }
    public void cancleLoadApk(){
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
