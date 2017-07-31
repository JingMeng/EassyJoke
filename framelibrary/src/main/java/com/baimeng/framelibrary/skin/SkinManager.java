package com.baimeng.framelibrary.skin;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.baimeng.framelibrary.base.BaseSkinActivity;
import com.baimeng.framelibrary.skin.attr.SkinView;
import com.baimeng.framelibrary.skin.config.SkinPreUtils;
import com.baimeng.library.utils.LogUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/29.
 * 皮肤管理类
 */


public class SkinManager {

    private static SkinManager mInstance ;
    private Context mContext ;
    private Map<Activity,List<SkinView>> mSkinViews = new HashMap<>() ;
    private SkinResources mSkinResource ;
    static {
        mInstance = new SkinManager() ;
    }

    public static SkinManager getInstance() {
        return mInstance ;
    }

    public void init(Context context){
        this.mContext = context.getApplicationContext();
        // 每一次打开应用都会到这里来，防止皮肤被任意删除，做一些措施
        String skinPath = SkinPreUtils.getInstance(mContext).getSkinPath();
        File file = new File(skinPath);
        if(!file.exists()){
            // 不存在，清空皮肤
            SkinPreUtils.getInstance(mContext).clearSkinPath();
            return;
        }
        // 最好做一下  能不能获取到包名
        String packageName = mContext.getPackageManager()
                .getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES).packageName;
        if(TextUtils.isEmpty(packageName)){
            SkinPreUtils.getInstance(mContext).clearSkinPath();
            return;
        }

        // 最好校验签名  增量更新再说


        // 做一些初始化的工作,
        mSkinResource = new SkinResources(mContext,skinPath);
    }

    public int loadSkin(String skinPath) {
        //校验签名 （增量更新讲）

        //初始化资源管理
        mSkinResource = new SkinResources(mContext ,skinPath);

        //改变皮肤
        //遍历集合
        Set<Activity> keys = mSkinViews.keySet();
        for (Activity key : keys) {
            List<SkinView> skinViews = mSkinViews.get(key);
            for (SkinView skinView : skinViews) {
                LogUtils.i("SkinView"+skinView.toString());
                skinView.skin();
            }
        }

        return 0;
    }

    public int restoreDefault() {
        return 0;
    }

    /**
     * 获取skinviews通过activity
     * @param activity
     * @return
     */
    public List<SkinView> getSkinViews(Activity activity) {
        return mSkinViews.get(activity);
    }

    public void register(Activity activity, List<SkinView> skinViews) {
        mSkinViews.put(activity,skinViews);
    }

    public SkinResources getSkinResource(){
        return mSkinResource ;
    }

    public void checkChangeSkin(SkinView skinView) {
        String currentSkinPath = SkinPreUtils.getInstance(mContext).getSkinPath();
        if(!TextUtils.isEmpty(currentSkinPath)){
            skinView.skin();
        }
    }
}
