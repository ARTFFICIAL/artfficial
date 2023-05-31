package com.example.docentapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "myDB.db";
    public static final String Table_Name = "mytable";
    public static final int version = 1;
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENTS = "contents";
    private Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE if not exists "
                + Table_Name + " (" + COLUMN_TITLE + " TEXT primary key, "
                + COLUMN_CONTENTS + " TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE if exists " + Table_Name;
        db.execSQL(sql);
        onCreate(db);
    }

    // mytable 데이터 입력
    public void insert(String name, String contents) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, name);
        cv.put(COLUMN_CONTENTS, contents);

        db.insert(Table_Name, null, cv);

        db.close();
    }

    // mytable 데이터 삭제
    public void Delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE " + Table_Name + " WHERE " + COLUMN_TITLE + " = '" + name + "'");
        db.close();
    }

    public List<String> getData2() {
        List<String> dataList2 = new ArrayList<>();
        //List<String> dataList3 = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Table_Name + ";", null);

        if(cursor.moveToFirst()) {
            do {
                String text = cursor.getString(1);
                dataList2.add(text);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dataList2;
    }
    public List<String> getData() {
        List<String> dataList1 = new ArrayList<>();
        //List<String> dataList3 = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Table_Name + ";", null);

        if(cursor.moveToFirst()) {
            do {
                String text = cursor.getString(0);
                dataList1.add(text);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dataList1;
    }
}
