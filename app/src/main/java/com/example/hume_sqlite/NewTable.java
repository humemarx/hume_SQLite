package com.example.hume_sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Created by tcp on 2014/12/23.
 * 创建数据库表
 */
public class NewTable extends Activity {
    MySQLiteDb mysqldb = null;
    SQLiteDatabase mydb = null;
    Context mycontext = null;

    /*创建SQL表的语句*/
    private final String CREATE_TAB_NAME = "create table mytable("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "name text,"+"hp INTEGER DEFAULT 100,"+"mp INTEGER DEFAULT 100,"+"num INTEGER);";
    /*删除SQL表的语句*/
    private final String DELETE_TAB_NAME = "drop table mytable";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.create_table);
        mycontext = this;
        mysqldb = MySQLiteDb.getInstance(mycontext);
        mydb = mysqldb.getReadableDatabase();

        Button btn_createtab = (Button)findViewById(R.id.btn_createtab);
        btn_createtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mydb.execSQL(CREATE_TAB_NAME);
                    Toast.makeText(NewTable.this,"成功添加此表",Toast.LENGTH_LONG).show();
                }
                catch (SQLiteException e){
                    Toast.makeText(NewTable.this,"数据库中已经有该表",Toast.LENGTH_LONG).show();
                }
            }
        });

        Button btn_deleteteb = (Button)findViewById(R.id.btn_deletetab);
        btn_deleteteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mydb.execSQL(DELETE_TAB_NAME);
                    Toast.makeText(NewTable.this,"成功删除该表",Toast.LENGTH_LONG).show();
                }
                catch (SQLiteException e){
                    Toast.makeText(NewTable.this,"数据库中已经无该表",Toast.LENGTH_LONG).show();
                }
            }
        });
        super.onCreate(savedInstanceState);
    }
}
