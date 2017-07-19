package com.baimeng.framelibrary.db;

/**
 * Created by Administrator on 2017/7/19.
 */

public class DaoUtils {
    public static String getTableName(Class<?> clazz){
        return clazz.getSimpleName() ;
    }
    public static String getColumnType(String type){
        String value = null ;
        if(type.contains("String")){
            value = " text";
        }else if ( type.contains("int")){
            value = " integer" ;
        }else if(type.contains("boolean")){
            value = " boolean" ;
        }else if(type.contains("float")){
            value = " float";
        }else if (type.contains("double")){
            value = "double" ;
        }else if (type.contains("char")){
            value = " varchar" ;
        }else if(type.contains("long")){
            value = " long" ;
        }
        return value ;
    }
}
