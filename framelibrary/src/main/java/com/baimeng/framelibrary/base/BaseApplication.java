package com.baimeng.framelibrary.base;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;
import com.baimeng.library.fixbug.FixDexManager;
import com.baimeng.library.utils.VersionUtils;

/**
 * Created by Administrator on 2017/7/6.
 */

public class BaseApplication extends Application {

    public static PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        //程序入口设置系统全局异常捕捉类
        ExceptionCrashHandler.getInstance().init(this);

        //初始化Andfix的patchManager
//        patchManager = new PatchManager(this);
//        patchManager.init(VersionUtils.getVersionName(this));
//        patchManager.loadPatch();

        //自己的热修复
        //加载之前的apatch包
//        try {
//            FixDexManager fixDexManager = new FixDexManager(this);
//            fixDexManager.loadFixDex();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
