package com.example.hume_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tcp on 2014/12/23.
 * 数据库类
 */
public class MySQLiteDb extends SQLiteOpenHelper {
    private static MySQLiteDb mysql = null;

    /*数据库名称*/
    public static String SQLDB_NAME = "hume.db";
    /*数据库版本*/
    public static int SQLDB_VERSION = 1;
    /*数据库表名*/
    private static String TAB_NAME = "mytest";
    /*数据库字段名*/
    private static String Phone_name = "name";
    private static String Phone_hp = "hp";
    private static String Phone_mp = "mp";
    private static String Phone_num = "number";

    /*数据库语句*/
    private static String CREATE_TAB_NAME = "create table "+TAB_NAME+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            Phone_name+" text,"+Phone_hp+" INTEGER DEFAULT 100,"+Phone_mp+" INTEGER DEFAULT 100,"+Phone_num+" INTEGER);";

    /*构造函数*/
    MySQLiteDb (Context context){
        super(context,SQLDB_NAME,null,SQLDB_VERSION);
    }

    /*单例模式*/
    static synchronized MySQLiteDb getInstance(Context context){
        if(mysql==null){
            mysql = new MySQLiteDb(context);
        }
        return mysql;
    }

    /*向数据库中添加表*/
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TAB_NAME);
    }

    /*更新数据库版本*/
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    }

    /*删除数据库*/
    public boolean deleteDatabase(Context context){
        return context.deleteDatabase(SQLDB_NAME);
    }
}
