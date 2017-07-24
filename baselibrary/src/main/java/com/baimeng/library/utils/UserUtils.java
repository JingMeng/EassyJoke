package com.baimeng.library.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinieco.shougang.bean.APermInstanceDto;
import com.sinieco.shougang.bean.AUserInfoDto;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by BaiMeng on 2017/4/14.
 */
public class UserUtils {
    static Gson gson = new Gson() ;
    public static void saveUser(AUserInfoDto data ,Context context){
        if(data.userName != null){
            SpUtils.removeValue(context,"userName");
            SpUtils.putValue(context , "userName" ,data.userName);
        }

        if(data.userId != 0){
            SpUtils.removeValue(context,"userId");
            SpUtils.putValue(context , "userId" ,data.userId);
        }

        if(data.loginName != null){
            SpUtils.removeValue(context,"loginName");
            SpUtils.putValue(context , "loginName" ,data.loginName);
        }

        if(data.password != null){
            SpUtils.removeValue(context,"password");
            SpUtils.putValue(context , "password" ,data.password);
        }

        if(data.mobile != null){
            SpUtils.removeValue(context,"mobile");
            SpUtils.putValue(context , "mobile" ,data.mobile);
        }

        if(data.userRole != null){
            SpUtils.removeValue(context,"userRole");
            SpUtils.putValue(context , "userRole" ,data.userRole);
        }

        if(data.userOrgParStr != null){
            SpUtils.removeValue(context,"userOrgParStr");
            SpUtils.putValue(context , "userOrgParStr" ,data.userOrgParStr);
        }

        if(data.postBox != null){
            SpUtils.removeValue(context,"postBox");
            SpUtils.putValue(context , "postBox" ,data.postBox);
        }

        if(data.type != 0){
            SpUtils.removeValue(context,"type");
            SpUtils.putValue(context , "type" ,data.type);
        }

        if(data.positions != null){
            SpUtils.removeValue(context,"positions");
            SpUtils.putValue(context , "positions" ,data.positions);
        }

        if(data.permInstanceDtos != null){
            SpUtils.removeValue(context,"permInstanceDtos");
            String dtos = gson.toJson(data.permInstanceDtos);
            SpUtils.putValue(context , "permInstanceDtos" ,dtos);
        }

        if(data.photoPath != null){
            SpUtils.removeValue(context,"photoPath");
            SpUtils.putValue(context,"photoPath",data.photoPath);
        }

    }
    public static String getUserName(Context context){
        return (String)SpUtils.getValue(context,"userName","");
    }
    public static Long getUserId(Context context){
        return (Long) SpUtils.getValue(context,"userId",-1L);
    }
    public static String getLoginName(Context context){
        return (String)SpUtils.getValue(context,"loginName","");
    }
    public static String getPassword(Context context){
        return (String)SpUtils.getValue(context,"password","");
    }
    public static String getMobile(Context context){
        return (String)SpUtils.getValue(context,"mobile","");
    }
    public static String getUserRole(Context context){
        return (String)SpUtils.getValue(context,"userRole","");
    }
    public static Integer getType(Context context){
        return (Integer) SpUtils.getValue(context,"type",-1);
    }
    public static List<APermInstanceDto> getPermInstanceDtos(Context context){
        String dtos = (String) SpUtils.getValue(context, "permInstanceDtos", "");
        if(dtos != ""){
            Type type = new TypeToken<List<APermInstanceDto>>(){}.getType();
            return gson.fromJson(dtos, type);
        }
        return null ;
    }
    public static String getPhotoPath(Context context){
        return (String)SpUtils.getValue(context,"photoPath","");
    }
    public static String getPostBox(Context context){
        return (String)SpUtils.getValue(context,"postBox","");
    }
    public static String getPositions(Context context){
        return (String)SpUtils.getValue(context,"positions","");
    }
    public static String getUserOrgParStr(Context context){
        return (String)SpUtils.getValue(context,"userOrgParStr","");
    }

    public static void clearUser(Context context) {
        SpUtils.removeValue(context,"userName");
        SpUtils.removeValue(context,"userId");
        SpUtils.removeValue(context,"loginName");
        SpUtils.removeValue(context,"password");
        SpUtils.removeValue(context,"mobile");
        SpUtils.removeValue(context,"userRole");
        SpUtils.removeValue(context,"userOrgParStr");
        SpUtils.removeValue(context,"postBox");
        SpUtils.removeValue(context,"type");
        SpUtils.removeValue(context,"positions");
        SpUtils.removeValue(context,"permInstanceDtos");
        SpUtils.removeValue(context,"photoPath");

    }
}
