package com.example.google_lens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button clickbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickbutton = findViewById(R.id.clickbutton);

        clickbutton.setOnClickListener(new View.OnClickListener() {
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
