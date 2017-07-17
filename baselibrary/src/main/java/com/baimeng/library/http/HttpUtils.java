package com.baimeng.library.http;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;

import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/15.
 * 链式调用
 */

public class HttpUtils {

    private String mUrl ;

    private int mType = GET_TYPE ;

    private Context mContext ;

    private Map<String , Object> mParams ;

    private static final int POST_TYPE = 0x0011 ;
    private static final int GET_TYPE = 0x0012 ;

    public HttpUtils(Context context){
        mContext = context ;
        mParams = new HashMap<String , Object>() ;
    }

    public static HttpUtils with (Context context){
        return new HttpUtils(context) ;
    }

    public HttpUtils url (String url){
        mUrl = url ;
        return this ;
    }

    public HttpUtils post(){
        mType = POST_TYPE ;
        return this ;
    }

    public HttpUtils get(){
        mType = GET_TYPE ;
        return this ;
    }

    public HttpUtils addParams(String key , Object value){
        mParams.put(key,value);
        return this ;
    }

    public HttpUtils addParams(Map<String , Object> params){
        mParams.putAll(params);
        return this;
    }

    public void execute(EngineCallBack callBack){
        if(callBack == null){
            callBack = EngineCallBack.DEFAULT_CALL_BACK ;
        }
        callBack.onPreExecute(mContext,mParams);
        if(mType == POST_TYPE){
            post  (mContext ,mUrl,mParams,callBack);
        }else if(mType == GET_TYPE){
            get(mContext ,mUrl,mParams,callBack);
        }
    }

    public void execute(){
        execute(null);
    }

    private static IHttpEngine mHttpEngine  = new OkHttpEngine();

    //可以在Application中初始化引擎
    public static void init(IHttpEngine engine){
        mHttpEngine = engine ;
    }

    public void exchangeEngine(IHttpEngine engine){
        mHttpEngine = engine ;
    }

    private void get(Context context ,String url, Map<String, Object> params, EngineCallBack callBack) {
        mHttpEngine.get(context ,url,params,callBack);
    }


    private void post(Context context ,String url, Map<String, Object> params, EngineCallBack callBack) {
        mHttpEngine.post(context ,url,params,callBack);
    }

    public static String joinParams(String url, Map<String, Object> params) {
        if(params == null || params.size() <= 0){
            return url ;
        }
        StringBuffer stringBuffer = new StringBuffer(url);
        if(!url.contains("?")){
            stringBuffer.append("?");
        }else {
            if(! url.endsWith("?")){
                stringBuffer.append("&");
            }
        }
        for (Map.Entry<String,Object> entry : params.entrySet()){
            stringBuffer.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        return stringBuffer.toString();
    }

    public static Class<?> analysisClazzInfo(Object object){
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

    public void cancelRequest(Object tag){
        mHttpEngine.cancelRequest(tag);
    }
}
