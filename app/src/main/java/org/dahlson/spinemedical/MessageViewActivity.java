package org.dahlson.spinemedical;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.MessageModel;
import org.dahlson.spinemedical.util.RecyclerDecoration;

public class MessageViewActivity extends BaseActivity{

    RecyclerView recyclerView;
    Point size;
    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("spinemedical", "MessageViewActivity - start");
        customSetContentView(R.layout.activity_message_view);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        inflateHeader(title);
        inflateHeaderSetColor("#7EF2AF");

        recyclerView = findViewById(R.id.message_recyclerView);
        //리싸이클러뷰에 레이아웃 매니저 설정하기
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerDecoration("h",-15,0));

        MessageReceiveAdapter messageReceiveAdapter = new MessageReceiveAdapter();
        messageReceiveAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "김현아", "안녕하십니까, 환자 여러분. 전북대 정형외과 외래교수 김현아입니다. ", "2022-01-01",1));
        messageReceiveAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "이인호", "넹 안녕하세요", "2022-01-01",2));
        messageReceiveAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "김현아", "안녕하십니까, 환자 여러분. 전북대 정형외과 외래교수 김현아입니다. ", "2022-01-01",3));
        messageReceiveAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "이인호", "넹 안녕하세요.", "2022-01-01",4));
        recyclerView.setAdapter(messageReceiveAdapter);


       //recyclerView.add

     /*   MessageSendAdapter messageSendAdapter = new MessageSendAdapter();
        messageSendAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "김현아", "안녕하십니까, 환자 여러분. 전북대 정형외과 외래교수 김현아입니다. ", "2022-01-01",1));
        messageSendAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "이인호", "넹 안녕하세요", "2022-01-01",1));
        recyclerView.setAdapter(messageSendAdapter);*/

    }

    /*
     * 팝업 크기 설정
     */
    /*private void setPupSize() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int widthPixels = metrics.widthPixels * 8/10;
        int heightPixels = widthPixels * 191/124;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(widthPixels,heightPixels);

        params.gravity = Gravity.CENTER;

        popupLayout.setLayoutParams(params);
    }*/

    public void onBackPressed() {
        Log.d("spinemedical","MessageViewActivity onBackPressed start");
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
        //MainActivity를 종료(메모리에서 제거)
        finish();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_message_view;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.message_tab;
    }
}
