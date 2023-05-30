package com.example.docentapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    private EditText ed_id, ed_PassWord, ed_name, ed_age;
    private View btn_resister;
    @Override
    protected void onCreate(Bundle savedInstanceState){ //액티비티 시작시 처음으로 실행되는 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        //아이디 값 찾아주기
        ed_id = findViewById(R.id.ed_id);
        ed_id = findViewById(R.id.ed_PassWord);
        ed_id = findViewById(R.id.ed_name);
        ed_id = findViewById(R.id.ed_age);

        //회원가입 버튼 클릭시 수행
        btn_resister = findViewById(R.id.btn_resister);
        btn_resister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //EditText에 현재 입력되어있는 값을 get한다.
                String userID = ed_id.getText().toString();
                String userPassword = ed_PassWord.getText().toString();
                String userName = ed_name.getText().toString();
                int userAge = Integer.parseInt(ed_age.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) { //회원등록에 성공한 경우
                                Toast toast = Toast.makeText(Login.this, "회원 등록에 성공하였습니다.",
                                        Toast.LENGTH_SHORT);
                                View toastView = toast.getView();

                                TextView toastText = toast.getView().findViewById(android.R.id.message);
                                toastText.setTextColor(Color.RED);
                                toast.show();

                                Intent intent = new Intent(Login.this, activity_login.class);
                                startActivity(intent);
                            } else {
                                Toast toast = Toast.makeText(Login.this, "회원 등록에 실패하였습니다.",
                                        Toast.LENGTH_SHORT);
                                View toastView = toast.getView();

                                TextView toastText = toast.getView().findViewById(android.R.id.message);
                                toastText.setTextColor(Color.RED);
                                toast.show();

                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //서버로 Volley이용해서 요청하기
                // resisterRe = new ResisterRe(userID, userPassword, userName, userAge, responseListener);

                ResisterRe resisterRe = new
                        ResisterRe(userID, userPassword, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(resisterRe);
            }
        });
    }
}
