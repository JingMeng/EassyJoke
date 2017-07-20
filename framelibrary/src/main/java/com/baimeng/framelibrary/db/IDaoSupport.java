package com.baimeng.framelibrary.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public interface IDaoSupport<T> {
    void init (SQLiteDatabase database , Class<T> clazz) ;
    long insert(T t);
    //批量插入
    void insert(List<T> datas);
}
