package com.example.docentapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SaveChatActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;
    ListView listView;
    TextView textView1, textView2;
    ArrayAdapter<String> adapter;

    String[] strs = new String[]{DBHelper.COLUMN_TITLE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_chat);

        //listView = (ListView) findViewById(R.id.listview);
        textView1 = (TextView) findViewById(R.id.textview1);
        textView2 = (TextView) findViewById(R.id.textview2);

        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        dbHelper.onCreate(db);

        String sql = "SELECT * FROM " + DBHelper.Table_Name + ";";
        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()) {
            String name = cursor.getString(0);
            String contents = cursor.getString(1);
            textView1.setText(textView1.getText()+"\n"+name +"\n");
            textView2.setText(textView2.getText()+"\n"+contents+"\n");
        }

        //dbHelper.close();
        db.close();
        cursor.close();

//        textView.setVisibility(View.GONE);
//
//        int[] ints = new int[]{R.id.tv_name};
//
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strs);
//        listView.setAdapter(adapter);
//        listView.setOnClickListener((View.OnClickListener) this);

        //=================================================================================

//        SimpleCursorAdapter adapter = null;
//        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,cursor,
//                strs, ints, 0); //CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
//        listView.setAdapter(adapter);

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, strs);
//        listView.setAdapter(arrayAdapter);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String contents = strs[position];
//        textView.setVisibility(View.VISIBLE);
//
//    }

        /*SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.listview, cursor, strs, ints,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);

         */
//
//        public Cursor getInfoFromDb() {
//            String[] projection = new String[] {DBHelper.COLUMN_TITLE, DBHelper.COLUMN_CONTENTS};
//
//            Cursor c= getContentResolver().query(DBHelper.CONTENT_URI, projection,
//                    null,null,null);
//        }

//        ListView listview = findViewById(R.id.listview);
//        String sql = "SELECT * FROM "+ DBHelper.DB_NAME+";";
//        listview.setText(sql);
//        String countryList[] = {"Korea", "China", "Japan", "America", "NewZealand"};
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countryList);
//
//        ListView listView = (ListView) findViewById(R.id.listview);
//        listView.setAdapter(arrayAdapter);

       //displayList();
/*
        String countryList[] = {"Korea", "China", "Japan", "America", "NewZealand"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countryList);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);

 */

        //  helper = new DBHelper(this, DBHelper.DB_NAME, null, DBHelper.version);
        //updateListView();


//    void displayList() {
//        TextView tv = findViewById(R.id.listview);
//        //DBHelper helper = new DBHelper(this, DBHelper.DB_NAME, null, DBHelper.version);
//
//
//        tv.setText("test");
//
////        List<ListViewAdapterData> mkList = DBHelper.selectAllmark();
////        for(ListViewAdapterData lcad : mkList) {
////
////        }
//
//        tv.setText("");
//
//       // listView.setAdapter(adapter);
//
//        //cursor.close();
//    }


    // private void updateListView() {
 //       ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, helper.getDB());
//        ListView listView = findViewById(R.id.listview);
 //       listView.setAdapter(adapter);
 //   }
}
/*
    String sql,data;

    DBHelper helper=new DBHelper(this, DBHelper.DB_NAME,null,DBHelper.version);
    SQLiteDatabase db = helper.getWritableDatabase();

    //private static final String TAG_NAME = "title";
    //private static final String TAG_CONTENTS = "contents";
    ArrayList<String> list = new ArrayList<>();
    ListView lv = (ListView) findViewById(R.id.listview);
    //ArrayList<HashMap<String, String>> MarkList;
    //private Message se;
//    ArrayAdapter<String> Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_chat);


        sql = "SELECT * FROM " + DBHelper.Table_Name ;
        Cursor cursor = db.rawQuery(sql,null);
        try{
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    list.add(cursor.getString(0));
                    list.add(cursor.getString(1));
                }
            }
            else {
            }
        }finally{
            cursor.close();
        }

        View.OnClickListener btListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        }

 */

        /*
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.save_chat, list);

        lv.setAdapter(adapter);

         */





/*
    public void updateListView() {

    }

 */

        //TextView tv = (TextView) findViewById(R.id.view);

        //tv.setText("show");

        // DBHelper 객체 선언
        //helper = new DBHelper(SaveChatActivity.this, DBHelper.DB_NAME, null, DBHelper.version);
        // 쓰기모드에서 데이터 불러오기
        //db = helper.getReadableDatabase();
        //helper.onCreate(db);
        /*
        void showList() {
            try {
                db = this.openOrCreateDatabase(DBHelper.DB_NAME, MODE_PRIVATE, null);

                Cursor cs = db.rawQuery("SELECT * from " + DBHelper.Table_Name, null);

                if (cs != null) {
                    if (cs.moveToFirst()) {
                        do {
                            String title = cs.getString(0);
                            String contents = cs.getString(1);

                            //HashMap에 넣습니다.
                            HashMap<String, String> mark = new HashMap<String, String>();

                            mark.put(TAG_NAME, title);
                            mark.put(TAG_CONTENTS, contents);

                            //ArrayList에 추가합니다..
                            MarkList.add(mark);
                        } while (cs.moveToNext());
                    }
                }
                db.close();

                ListAdapter adapter = new SimpleAdapter(
                        this, MarkList, R.layout.listview,
                        new String[]{TAG_NAME, TAG_CONTENTS}, null);
                listView.setAdapter(adapter);

            }catch (SQLException se) {
                Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("",  se.getMessage());
            }
        }

*/

/*
        String sql = "select * from " + DBHelper.Table_Name + ";";
        Cursor cs = db.rawQuery(sql, null);

        String[] strs = new String[]{"txt"};
        int[] ints = new int[] {R.id.listview_txt};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(listView.getContext(), R.layout.listview, c, strs, ints,0);

        listView.setAdapter(adapter);*/

