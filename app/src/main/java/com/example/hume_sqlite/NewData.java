package com.example.hume_sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by tcp on 2014/12/23.
 * 对数据库表中的数据进行添加，删除，修改，查找操作
 */
public class NewData extends Activity {
    MySQLiteDb mysqldb = null;
    SQLiteDatabase mydb = null;
    Context mycontext = null;

    /*数据库字段*/
    public final String TABLE_NAME = "humetable";
    public final String ID = "_id";
    public final String NAME = "name";
    public final String HP = "hp";
    public final String MP = "mp";
    public final String NUM = "num";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.create_data);
        mycontext = this;
        mysqldb = MySQLiteDb.getInstance(mycontext);
        mydb = mysqldb.getReadableDatabase();
        /*初始化数据*/
        for(int i=0; i<10; ++i){
            insert(NAME,"tcp的数据库"+i);
        }
        /*增加数据*/
        Button btn_create = (Button)findViewById(R.id.btn_createdata);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(NAME,"马克思");
                Toast.makeText(NewData.this,"添加一条数据",Toast.LENGTH_LONG).show();
            }
        });

        Button btn_delete = (Button)findViewById(R.id.btn_deletedata);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(ID,"1");
                Toast.makeText(NewData.this,"删除ID为1的数据",Toast.LENGTH_LONG).show();
            }
        });

        Button btn_update = (Button)findViewById(R.id.btn_editdata);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(NAME,"马克思","黑格尔");
                Toast.makeText(NewData.this,"将马克思改为黑格尔",Toast.LENGTH_LONG).show();
            }
        });

        Button btn_find = (Button)findViewById(R.id.btn_searchdata);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = find(ID,"5");
                String data = c.getString(c.getColumnIndex(NAME));
                Toast.makeText(NewData.this,"查找ID为5的数据名称是："+data,Toast.LENGTH_LONG).show();
            }
        });
        super.onCreate(savedInstanceState);
    }


    /*数据库插入数据*/
    private void insert(String key, String data) {
        ContentValues value = new ContentValues();
        value.put(key,data);
        mydb.insert(TABLE_NAME,null,value);
    }

    /*数据库删除数据*/
    private void delete(String key,String data){
        mydb.delete(TABLE_NAME,key+"=?",new String[]{data});
    }

    /*数据库修改数据*/
    private void update(String key,String olddata,String newdata){
        ContentValues value = new ContentValues();
        value.put(key,newdata);
        mydb.update(TABLE_NAME,value,key+"=?",new String[]{olddata});
    }

    /*数据库查找数据*/
    private Cursor find(String key,String data){
        Cursor c = mydb.query(TABLE_NAME,null,key+"=?",new String[]{data},null,null,null);
        if(c!=null)c.moveToFirst();
        return c;
    }
}
