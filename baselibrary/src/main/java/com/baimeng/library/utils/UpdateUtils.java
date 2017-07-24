package com.baimeng.library.utils;

import android.content.Intent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.sinieco.shougang.activity.LoginActivity;
import com.sinieco.shougang.bean.AVersionDto;

/**
 * Created by BaiMeng on 2017/3/24.
 */
public class UpdateUtils implements View.OnClickListener {
    private LoginActivity context ;
    Gson gson = new Gson();
    private View view ;
    private ProgressBar bar;
    private PopupWindow pop;
    private AVersionDto dto;

    public UpdateUtils(LoginActivity context) {
        this.context = context ;
    }


    /**
     * 检查软件是否有更新版本
     *
     * @return
     */
    public void checkUpdate(View view)  {
        this.view = view ;
        //查看是否有新的版本
//        AVersionDto dto = new AVersionDto(getVersionName());
//        ARequest<AVersionDto> request = new ARequest<AVersionDto>(TimeUtils.formatTime(System.currentTimeMillis()),dto);
//        String s = gson.toJson(request);
//        OkHttpUtils.post().url(NetUtils.LE_LE+NetUtils.UPDATE)
//                .addParams(NetUtils.REQUEST,s)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        //
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        parseJson(response);
//                    }
//                });

    }

    private boolean parseJson(String response) {

        return false ;
    }

    private void downLoadApk(String url) {
        int index = url.lastIndexOf("/");
        String appName = url.substring(index + 1, url.length());
        LogUtils.i(NetUtils.LE_LE+url);
        LogUtils.i("name:"+appName);
        //下载新的App
    }





    /*
     * 进入程序的主界面
     */
    private void LoginMain(){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    public void onClick(View v) {

    }
}
