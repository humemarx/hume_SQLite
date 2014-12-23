package com.example.hume_sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by tcp on 2014/12/23.
 * 新建SQL的类
 */
public class NewSQLite extends Activity {
    MySQLiteDb mysqldb = null;
    SQLiteDatabase mydb = null;
    Context mycontext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.create_sql);
        mycontext = this;
        /*创建数据库*/
        mysqldb = MySQLiteDb.getInstance(mycontext);
        mydb = mysqldb.getReadableDatabase();

        Button btn_create = (Button)findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewSQLite.this,"创建数据库成功",Toast.LENGTH_LONG).show();
            }
        });

        Button btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysqldb = MySQLiteDb.getInstance(mycontext);
                mydb = mysqldb.getReadableDatabase();
                /*关闭数据库*/
                mysqldb.close();
                /*删除数据库*/
                mysqldb.deleteDatabase(mycontext);
                Toast.makeText(NewSQLite.this,"删除数据库成功",Toast.LENGTH_LONG).show();
            }
        });
        super.onCreate(savedInstanceState);
    }
}
