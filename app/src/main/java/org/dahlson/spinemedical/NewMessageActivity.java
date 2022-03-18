package org.dahlson.spinemedical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.MessageModel;
import org.dahlson.spinemedical.util.RecyclerDecoration;

public class NewMessageActivity extends BaseActivity{

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("spinemedical", "NewMessageActivity - start");
        customSetContentView(R.layout.activity_new_message);

        recyclerView = findViewById(R.id.message_recyclerView);
        //리싸이클러뷰에 레이아웃 매니저 설정하기
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerDecoration("h",-15,0));

        MessageAdapter messageAdapter = new MessageAdapter();
        MessageModel messageModel = new MessageModel();
        messageModel.setImgUrl("https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg");
        messageModel.setDoctorName("김인문");
        messageModel.setHospitalName("중앙병원");
        messageModel.setViewType(6);

        messageAdapter.addItem(messageModel);
        recyclerView.setAdapter(messageAdapter);

        messageAdapter.setOnItemClickListener(new OnMessageItemClickListener() {
            @Override
            public void onItemClick(MessageAdapter.ViewHolder holder, View view, int position) {
                Log.d("spinemedical", "NewMessageActivity - onItemClick - start");
                Intent intent = new Intent(getApplicationContext(), MessageViewActivity.class);
                String strDoctorName = String.valueOf(holder.doctorName.getText());
                intent.putExtra("title", strDoctorName);
                intent.putExtra("messageType", "new");
                startActivity(intent);
                activity.finish();
            }
        });
    }

    public void onBackPressed() {
        Log.d("spinemedical","MessageViewActivity onBackPressed start");
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
        //MainActivity를 종료(메모리에서 제거)
        finish();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_new_message;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.message_tab;
    }
}
