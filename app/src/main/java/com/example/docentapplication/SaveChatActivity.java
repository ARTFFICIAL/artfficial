package com.example.docentapplication;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class SaveChatActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;
    ListView listView1, listView2;
    ArrayAdapter<String> adapter1, adapter2;

    List<String> dataList1, dataList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listView1 = findViewById(R.id.listview1);
        listView2 = findViewById(R.id.listview2);
        dataList1 = new ArrayList<>();
        dataList2 = new ArrayList<>();
        dbHelper = new DBHelper(this);

        dataList1 = dbHelper.getData();
        dataList2 = dbHelper.getData2();
        adapter1 = new ArrayAdapter<>(this, R.layout.save_chat, R.id.textview1, dataList1);
        listView1.setAdapter(adapter1);
        adapter2 = new ArrayAdapter<>(this, R.layout.save_chat, R.id.textview1, dataList2);
        listView2.setAdapter(adapter2);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 항목에 해당하는 내용을 AlertDialog로 표시
                String selectedItem = dataList1.get(position);
                showAlertDialog(selectedItem);
            }
        });

    }
    private void showAlertDialog(String selectedItem) {
        // dataList2에서 selectedItem에 해당하는 내용 가져오기
        int index = dataList1.indexOf(selectedItem);
        String content = dataList2.get(index);

        // AlertDialog 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(selectedItem)
                .setMessage(content)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 확인 버튼 클릭 시 동작할 내용 작성
                    }
                })
                .show();
    }
}