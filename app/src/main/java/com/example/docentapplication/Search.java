package com.example.docentapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

    EditText messageEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        ImageButton searchBnt = (ImageButton) findViewById(R.id.search_btn);
        ImageButton googleBnt = (ImageButton) findViewById(R.id.google_btn);

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

        googleBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGlensApp();
            }
        });


    }
    private void openGlensApp() {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.ar.lens");
        if (intent != null) {
            startActivity(intent);
        } else {
            // 구글 렌즈 앱이 설치되어 있지 않은 경우 - 구글 play스토어 이동

            Uri playStoreUri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.ar.lens&hl=ko");
            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, playStoreUri);
            startActivity(playStoreIntent);
        }


    }

}
