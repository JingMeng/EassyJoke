package com.baimeng.framelibrary.skin;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.baimeng.framelibrary.skin.attr.SkinAttr;
import com.baimeng.framelibrary.skin.attr.SkinType;
import com.baimeng.library.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 * 皮肤属性解析的支持类
 */

public class SkinAttrSupport {

    public static List<SkinAttr> getSkinAttrs(Context context, AttributeSet attrs) {
        List<SkinAttr> skinAttrs = new ArrayList<>();
        int attrLength = attrs.getAttributeCount();
        for (int i = 0 ; i < attrLength ; i++){
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            //Log.e("TAG","attrName --->>>"+attrName+"attrValue ---->>>"+attrValue);
            //只关注重要的
            SkinType skinType = getSkinType(attrName);
            if(skinType != null){
                //资源名称 目前只有attrValue 是一个 @int类型
                LogUtils.i("SkinType！=null");
                String resName = getResName(context,attrValue);
                if (TextUtils.isEmpty(resName)){
                    continue;
                }
                SkinAttr skinAttr = new SkinAttr(resName,skinType);
                skinAttrs.add(skinAttr);
            }
        }
        LogUtils.i("SkinAttrs"+skinAttrs.toString());
        return skinAttrs;
    }

    /**
     * 获取资源名称
     * @param context
     * @param attrValue
     * @return
     */
    private static String getResName(Context context, String attrValue) {
        LogUtils.i("attrValue==================="+attrValue);
        if(attrValue.startsWith("@")){
            attrValue = attrValue.substring(1);
            int resId = Integer.parseInt(attrValue);
            LogUtils.i("resId"+"================="+resId);
            return context.getResources().getResourceEntryName(resId);
        }
        return null;
    }

    /**
     * 通过名称获取SkinType
     * @param attrName
     * @return
     */
    private static SkinType getSkinType(String attrName) {
        SkinType[] skinTypes = SkinType.values();
        for (SkinType skinType : skinTypes) {
            if(skinType.getResName().equals(attrName)){
                LogUtils.i("SkinType"+"==========="+skinType+"============attrName"+"==========="+attrName);
                return skinType ;
            }
        }
        return null;
    }
}
