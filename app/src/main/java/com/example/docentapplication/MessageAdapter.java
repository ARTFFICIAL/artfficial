package com.example.docentapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(chatView);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Message message = messageList.get(position);

        holder.starBtn.setTag(position);
        holder.leftChatTextView.setTag(position);
        holder.rightChatTextView.setTag(position);

        if(message.getSentBy().equals(Message.SENT_BY_ME)){
            holder.leftChatView.setVisibility(View.GONE);
            holder.rightChatView.setVisibility(View.VISIBLE);
            holder.rightChatTextView.setText(message.getMessage());
        }else{
            holder.rightChatView.setVisibility(View.GONE);
            holder.leftChatView.setVisibility(View.VISIBLE);
            holder.leftChatTextView.setText(message.getMessage());
        }

        holder.starBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(holder.starBtn.isChecked()){
                    message.setChecked(true);
                    String index = holder.rightChatTextView.getTag().toString();
                    int idx = Integer.parseInt(index);
                    System.out.println("별 확인하기 : " + holder.starBtn.getTag());
                    System.out.println("답 인덱스 확인하기 : " + holder.leftChatTextView.getTag());
                    System.out.println("답 확인하기 : " + holder.leftChatTextView.getText());
                }else{
                    message.setChecked(false);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftChatView, rightChatView;
        TextView leftChatTextView, rightChatTextView;
        CheckBox starBtn;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            starBtn = itemView.findViewById(R.id.star_btn);
            leftChatView = itemView.findViewById(R.id.left_chat_view);
            rightChatView = itemView.findViewById(R.id.right_chat_view);
            leftChatTextView = itemView.findViewById(R.id.left_chat_text_view);
            rightChatTextView = itemView.findViewById(R.id.right_chat_text_view);

        }
    }
}
