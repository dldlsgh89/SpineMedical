package org.dahlson.spinemedical;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.MessageModel;

public class MessageActivity extends BaseActivity {
    RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customSetContentView(R.layout.activity_message);

        recyclerView = findViewById(R.id.message_recyclerView);
        //리싸이클러뷰에 레이아웃 매니저 설정하기
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        MessageAdapter messageAdapter = new MessageAdapter();
        /*for(){

        }*/
        messageAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "김현아", "안녕하십니까, 환자 여러분", "2022-01-01", 1));
        messageAdapter.addItem(new MessageModel(0, getURLForResource(R.drawable.doctor), "김인문", "안녕하십니까, 환자 여러분", "2022-\n01-01", 1));
        messageAdapter.addItem(new MessageModel(0, getURLForResource(R.drawable.doctor), "한창권", "안녕하십니까, 환자 여러분", "2022-\n01-01", 1));
        messageAdapter.addItem(new MessageModel(0, getURLForResource(R.drawable.doctor), "조상수", "안녕하십니까, 환자 여러분", "2022-\n01-01", 1));
        recyclerView.setAdapter(messageAdapter);


        messageAdapter.setOnItemClickListener(new OnMessageItemClickListener() {
            @Override
            public void onItemClick(MessageAdapter.ViewHolder holder, View view, int position) {
                Log.d("spinemedical", "MessageActivity - onItemClick - start");
                /*MessageModel item = messageAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), "아이템 선택됨: " + item.getContent(), Toast.LENGTH_LONG).show();*/
                Intent intent = new Intent(getApplicationContext(), MessageViewActivity.class);
                intent.putExtra("title", "김현아");
                startActivity(intent);
                activity.finish();
            }
        });

        inflateHeader("메시지 목록");
    }

    private String getURLForResource(int resId) {
        //return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resId).toString();
        return "android.resource://" + R.class.getPackage().getName() + "/" + resId;
    }

    private String getURLForDrawable(String fileName) {
        return "@drawable/" + fileName;
    }


}
