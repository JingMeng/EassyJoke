package com.baimeng.framelibrary.skin;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.baimeng.library.utils.LogUtils;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/29.
 * 皮肤资源
 */

public class SkinResources {

    //资源通过mResource获取
    private Resources mSkinResource ;
    private String mPackageName ;
    public SkinResources(Context context , String skinPath) {
        try {
            //获取当前app的Resources
            Resources superRes = context.getResources() ;
            //通过反射获取到AssetManager实例
            AssetManager am = AssetManager.class.newInstance();
            //反射获取到addAssetPath方法
            Method addAssetMothod = AssetManager.class.getMethod("addAssetPath", String.class);
            //反射调用addAssetPath方法
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"blue.skin");
            if(file.exists()){
                Log.i("file.path===========",file.getAbsolutePath());
            }
            if(file.isFile()&&file.exists()){
                addAssetMothod.invoke(am,file.getAbsolutePath() );
                Log.i("00000000000000","skinPath==="+skinPath);
                //获取皮肤apk的Resources
                mSkinResource = new Resources(am,superRes.getDisplayMetrics(),superRes.getConfiguration());
//            //获取一个apk的包名
//            mPackageName = context.getPackageManager()
//                    .getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES)
//                    .packageName;
//
//            LogUtils.i("包名================="+mPackageName);


                Log.i("context==null?","====================="+(context==null?true:false));
                // 获取skinPath包名
                mPackageName = context.getPackageManager().getPackageArchiveInfo(
                        file.getAbsolutePath(), PackageManager.GET_ACTIVITIES).packageName;

                Log.i("包名","====================="+mPackageName);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过名字获取Drawable
     * @param resName
     * @return
     */
    public Drawable getDrawableByName(String resName){
        try {
            Log.i("resName","  ========"+resName) ;
            int resId = mSkinResource.getIdentifier(resName, "drawable", mPackageName);
            Log.i("TAG========",resId+"");
            Log.i("TAG========",resName+"");
            Drawable drawable = mSkinResource.getDrawable(resId);
            return drawable ;
        }catch (Exception e){
            e.printStackTrace();
            return null ;
        }
    }

    /**
     * 通过名字获取Color
     * @param resName
     * @return
     */
    public ColorStateList getColorByName(String resName){
        try {
            int resId = mSkinResource.getIdentifier(resName, "drawable", mPackageName);
            LogUtils.i("colorid======="+resId);
            Log.i("colorid=======",resId+"");
            ColorStateList color = mSkinResource.getColorStateList(resId);
            return color ;
        }catch (Exception e){
            e.printStackTrace();
            return null ;
        }
    }
}
