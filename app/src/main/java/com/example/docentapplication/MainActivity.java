package com.example.docentapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    BuildConfig buildConfig;
    RecyclerView recyclerView;
    TextView welcomeTextView;
    EditText messageEditText;
    ImageButton sendButton;
    List<Message> messageList;
    MessageAdapter messageAdapter;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton gobackBnt = (ImageButton) findViewById(R.id.go_back);
        gobackBnt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });

        messageList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        welcomeTextView = findViewById(R.id.welcome_text);
        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_btn);

        //setup recycler view
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        getSearchText();

        sendButton.setOnClickListener((v)->{
            String question = messageEditText.getText().toString().trim();
            if(question.length()!=0){
                addTOChat(question,Message.SENT_BY_ME);
//            Toast.makeText(this,question,Toast.LENGTH_LONG).show();
                messageEditText.setText("");
                callAPI(question);
                welcomeTextView.setVisibility(View.GONE);
            }
        });
    }

    void getSearchText(){

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String searchTxt = bundle.getString("searchTxt");

        String question = searchTxt.trim();
        addTOChat(question, Message.SENT_BY_ME);
        callAPI(question);
        welcomeTextView.setVisibility(View.GONE);
    }

    void addTOChat(String message, String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message, sentBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }

    void addResponse(String response){
        messageList.remove(messageList.size()-1); //입력중... 지우기
        addTOChat(response,Message.SENT_BY_BOT);

    }


    void callAPI(String question){

        //okhttp
        messageList.add(new Message("입력중...", Message.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "gpt-3.5-turbo");

            JSONArray messageArr = new JSONArray();
            JSONObject obj_s = new JSONObject();
            JSONObject obj_q = new JSONObject();
            JSONObject obj_a = new JSONObject();

            obj_s.put("role", "system");
            obj_s.put("content", "예술작품에 대한 도슨트를 자세히 작성해줘");

            obj_q.put("role", "user");
            obj_q.put("content", question);

            messageArr.put(obj_q);
            messageArr.put(obj_s);

            jsonBody.put("messages", messageArr);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", buildConfig.CHATGPT_API_KEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject jasonObject = null;
                    try {
                        jasonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jasonObject.getJSONArray("choices");
                            String result = jsonArray.getJSONObject(0)
                                            .getJSONObject("message")
                                                    .getString("content");
                            addResponse(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    addResponse("Failed to load response due to " + response.body().toString());
                }
            }
        });
    }

}