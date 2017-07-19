package com.baimeng.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/7/19.
 */

public interface IDaoSupport<T> {
    void init (SQLiteDatabase database , Class<T> clazz) ;
    int insert(T t);
}
