package com.baimeng.framelibrary.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class DaoSupportImpl<T> implements IDaoSupport<T> {
    private SQLiteDatabase mSqliteDataBase;
    private Class<T> mClazz;

    @Override
    public long insert(T obj) {
        ContentValues values = getContentValueByObj(obj);
        return mSqliteDataBase.insert(DaoUtils.getTableName(mClazz),null,values);
    }

    @Override
    public void insert(List<T> datas) {
        mSqliteDataBase.beginTransaction();
        for (T data : datas) {
            insert(data);
        }
        mSqliteDataBase.setTransactionSuccessful();
        mSqliteDataBase.endTransaction();
    }

    //利用反射将泛型类转换成ContentValue
    private ContentValues getContentValueByObj(T obj) {
        ContentValues values = new ContentValues() ;
        Field[] fields = mClazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                Object value = field.get(obj);
                //value应该是类型Integer，Long等等
                Method putMethod = ContentValues.class.getDeclaredMethod("put", String.class, value.getClass());
                putMethod.invoke(values,key,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values ;
    }

    public void init (SQLiteDatabase database ,Class<T> clazz){
        this.mSqliteDataBase = database ;
        this.mClazz = clazz ;
        StringBuffer sb = new StringBuffer();
        sb.append("create table if not exists ").append(DaoUtils.getTableName(clazz)).append("(id integer primay key auto_increment, ");
        Field[] fields = mClazz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            String name = field.getName();
            String type = field.getType().getSimpleName();
            sb.append(name).append(DaoUtils.getColumnType(type)).append(", ");
        }
        StringBuffer sqliteStr = sb.replace(sb.length() - 2, sb.length(), ");");
        mSqliteDataBase.execSQL(sqliteStr.toString());
    }


}
