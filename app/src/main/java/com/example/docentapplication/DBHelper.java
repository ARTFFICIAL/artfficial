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
        //db.execSQL("INSERT INTO " + Table_Name + " VALUES ('" + name + "', '" + contents + "');");
        /*if (result == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "데이터 추가 성공", Toast.LENGTH_SHORT).show();
        }*/
        db.close();
    }

    // mytable 데이터 삭제
    public void Delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE " + Table_Name + " WHERE " + COLUMN_TITLE + " = '" + name + "'");
        db.close();
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
  /*
    public void SelectAll (){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cs = db.query(Table_Name, null,null,null,null,
                null,null);
        while(cs.moveToNext()){
            System.out.println(cs.getString(0)+ " " + cs.getString(1));
        }
        //ContentValues cv = new ContentValues();
        //db.execSQL("SELECT * FROM "+ Table_Name );
        db.close();

//        cv.get(COLUMN_TITLE, name);
//        cv.get(COLUMN_CONTENTS, content);


    }

       // 데이터 불러오기
       public ArrayList getDB() {
           ArrayList mk_info = new ArrayList<String>();

           String sql = "SELECT * FROM " + Table_Name;

           Cursor cursor = getWritableDatabase().rawQuery(sql,null);
           if(cursor!=null && cursor.moveToFirst()){
               do {
                   mk_info.add(cursor.getString(1));
               }while(cursor.moveToNext());
           }

           cursor.close();

           return mk_info;

           //DB open
           SQLiteDatabase db = getReadableDatabase();

           Cursor cursor = db.rawQuery("Select * from " + Table_Name, null);

           while(cursor.moveToNext()) {
               mk_info.add(cursor.getString(0)
                       +"\n"
                       +mk_info.add(cursor.getString(1)));
           }
           return mk_info;


       }

    public static List<ListViewAdapterData> selectAllmark() {
        List<ListViewAdapterData> mkList = new ArrayList<ListViewAdapterData>();

        String sql = "SELECT * FROM " + Table_Name +";";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            do {
                mkList.add(new ListViewAdapterData(cursor.getString(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return mkList;
    } */

//    public void getDB() {
//        ArrayList mk_info = new ArrayList<String>();
//
//        String sql = "SELECT * FROM " + Table_Name;
//
//        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                mk_info.add(cursor.getString(1));
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//    }
}
