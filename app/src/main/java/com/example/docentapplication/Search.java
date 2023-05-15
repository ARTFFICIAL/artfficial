package com.example.docentapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

    EditText messageEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        ImageButton searchBnt = (ImageButton) findViewById(R.id.search_btn);

        searchBnt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                messageEditText = findViewById(R.id.search_message_edit_text);
                String searchTxt = messageEditText.getText().toString().trim();
                if(searchTxt.length()!=0){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("searchTxt", searchTxt);
                    startActivity(intent);
                }
            }
        });

    }


}
