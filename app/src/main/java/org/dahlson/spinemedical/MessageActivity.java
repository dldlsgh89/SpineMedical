package org.dahlson.spinemedical;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.MessageModel;

public class MessageActivity extends BaseActivity {
    RecyclerView recyclerView;
    Context context;
    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

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
        messageAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "김현아", "안녕하십니까, 환자 여러분", "2022-01-01", 5));
        messageAdapter.addItem(new MessageModel(0, getURLForResource(R.drawable.doctor), "김인문", "안녕하십니까, 환자 여러분", "2022-\n01-01", 5));
        messageAdapter.addItem(new MessageModel(0, getURLForResource(R.drawable.doctor), "한창권", "안녕하십니까, 환자 여러분", "2022-\n01-01", 5));
        messageAdapter.addItem(new MessageModel(0, getURLForResource(R.drawable.doctor), "조상수", "안녕하십니까, 환자 여러분", "2022-\n01-01", 5));
        recyclerView.setAdapter(messageAdapter);


        messageAdapter.setOnItemClickListener(new OnMessageItemClickListener() {
            @Override
            public void onItemClick(MessageAdapter.ViewHolder holder, View view, int position) {
                Log.d("spinemedical", "MessageActivity - onItemClick - start");
                /*MessageModel item = messageAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), "아이템 선택됨: " + item.getContent(), Toast.LENGTH_LONG).show();*/
                Intent intent = new Intent(getApplicationContext(), MessageViewActivity.class);
                String strFromName = String.valueOf(holder.fromName.getText());
                intent.putExtra("title", strFromName);
                intent.putExtra("messageType", "old");
                startActivity(intent);
                activity.finish();
            }
        });

    
        //새로운 메시지 버튼 클릭
        /*AppCompatImageButton newMessageBtn = findViewById(R.id.new_message_btn);
        newMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("spinemedical", "MessageActivity - newMessageBtn - click");
                Intent intent = new Intent(getApplicationContext(), NewMessageActivity.class);
                startActivity(intent);
                activity.finish();
            }
        });*/

        inflateHeader("메시지 목록");
    }

    private String getURLForResource(int resId) {
        //return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resId).toString();
        return "android.resource://" + R.class.getPackage().getName() + "/" + resId;
    }

    public void onBackPressed() {
        Log.d("spinemedical","MessageActivity onBackPressed start");
        backKeyHandler.onBackPressed();
    }

    private String getURLForDrawable(String fileName) {
        return "@drawable/" + fileName;
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_message;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.message_tab;
    }
}
