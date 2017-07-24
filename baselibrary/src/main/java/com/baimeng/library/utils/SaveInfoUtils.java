package com.baimeng.library.utils;

import android.Manifest;
import android.content.Context;
import android.os.Environment;

import com.sinieco.shougang.bean.AUserInfoDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by BaiMeng on 2017/3/27.
 */
public class SaveInfoUtils {
    public static void saveInfo(final AUserInfoDto dto, Context context){
        XPermissionUtils.requestPermissions(context, 1, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}
                , new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        try {
                            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/shougang/");
                            if(!dir.exists()){
                                dir.mkdirs();
                            }
                            File file = new File(dir,"user.text");
                            if(file.exists()){
                                file.delete();
                            }
                            file.mkdirs();
                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                            oos.writeObject(dto);
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onPermissionDenied() {

                    }
                });

    }

    public static AUserInfoDto getInfo(Context context){
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/shougang/","user.text");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                AUserInfoDto dto = (AUserInfoDto) ois.readObject();
                return dto ;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null ;
    }
}
