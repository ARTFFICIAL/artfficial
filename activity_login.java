package com.example.docentapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;


public class activity_login extends AppCompatActivity {
    private EditText ed_id, ed_PassWord;
    private Button ed_login, ed_resister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_id = findViewById(R.id.ed_id);
        ed_PassWord = findViewById(R.id.ed_PassWord);
        ed_login= findViewById(R.id.ed_login);
        ed_resister = findViewById(R.id.ed_resister);

        ed_resister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_login.this, Login.class);
                startActivity(intent);
            }
        });
        ed_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(activity_login.this, Search.class);
                startActivity(intent);
            String userID = ed_id.getText().toString();
            String userPassword = ed_PassWord.getText().toString();


            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if(success) { //로그인에 성공한 경우

                            String userID = jsonObject.getString("userID");
                            String userPassword = jsonObject.getString("userPassword");
                            Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity_login.this, Search.class);

                            //intent.putExtra("userID", userID);
                            //intent.putExtra("userPassword", userPassword);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                };
            }
        });


    }
}