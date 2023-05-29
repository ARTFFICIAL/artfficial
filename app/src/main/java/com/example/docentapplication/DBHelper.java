package com.example.docentapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE if not exists mytable ("
                + "name Text,"
                + "contents Text);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE if exists mytable";

        db.execSQL(sql);
        onCreate(db);
    }


    // mytable 데이터 입력
    public void insert(String name, String contents) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO mytable VALUES ('" + name + "', '" + contents + "');");

        db.close();
    }

    // mytable 데이터 삭제
    public void Delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE mytable WHERE NAME = '" + name + "'");
        db.close();
    }


}
