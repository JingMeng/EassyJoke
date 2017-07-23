package com.baimeng.framelibrary.base;

import android.content.Context;
import android.util.Log;

import com.baimeng.library.http.EngineCallBack;
import com.baimeng.library.http.HttpUtils;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/16.
 */

public abstract class HttpCallBack <T> implements EngineCallBack {
    /**
     * 请求网络前封装参数
     * @param context
     * @param params
     */
    public void onPreExecute(Context context , Map<String, Object> params){
        Log.i("请求网络前封装参数","          ");
        onPreExecute();
    }
    public void onPreExecute(){
    };

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        T objResponse = (T)gson.fromJson(result, HttpUtils.analysisClazzInfo(this));
        Log.i("TAG==================",result);

        onSuccess(objResponse);
    }

    public abstract void onSuccess(T result);
}
