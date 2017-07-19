package com.baimeng.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/7/19.
 */

public class DaoSupportImpl<T> implements IDaoSupport<T> {
    private SQLiteDatabase mSqliteDataBase;
    private Class<T> mClazz;

    @Override
    public int insert(T t) {

        return 0;
    }

    public void init (SQLiteDatabase database ,Class<T> clazz){
        this.mSqliteDataBase = database ;
        this.mClazz = clazz ;
        StringBuffer sb = new StringBuffer();
        sb.append("create table if not exists ").append(DaoUtils.getTableName(clazz)).append("(id integer primay key autoincrement, ");
        Field[] fields = mClazz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            String name = field.getName();
            String type = field.getType().getSimpleName();
            sb.append(name).append(DaoUtils.getColumnType(type)).append(", ");
        }
        StringBuffer sqliteStr = sb.replace(sb.length() - 2, sb.length(), ")");
        mSqliteDataBase.execSQL(sqliteStr.toString());
    }


}
