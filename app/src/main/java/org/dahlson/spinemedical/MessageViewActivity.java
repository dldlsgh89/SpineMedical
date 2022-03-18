package org.dahlson.spinemedical;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.MessageModel;
import org.dahlson.spinemedical.util.RecyclerDecoration;

public class MessageViewActivity extends BaseActivity{

    RecyclerView recyclerView;
    private InputMethodManager imm;
    Point size;
    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("spinemedical", "MessageViewActivity - start");
        customSetContentView(R.layout.activity_message_view);

        //타이블 백그라운드색 변경
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        inflateHeader(title);
        inflateHeaderSetColor("#7EF2AF");

        recyclerView = findViewById(R.id.message_recyclerView);
        //리싸이클러뷰에 레이아웃 매니저 설정하기
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //아이템 간격
        recyclerView.addItemDecoration(new RecyclerDecoration("h",-15,0));

        MessageReceiveAdapter messageReceiveAdapter = new MessageReceiveAdapter();

        //새로운 메시지 or 기존 메시지 모두 같은 activity 및 adapter를 사용하기 때문에 아이템을 추가하기 전에
        //넘어온 정보(현재 유저정보, 메세지 및 채팅을 하려고 선택한 유저정보)를 가지고 db에서 이전에 먼저 하던 메시지나 채팅이 있는지 확인하여 데이터를 리턴 받은 뒤 처리해야함

        //현재는 그냥 이전 화면에서 old, new 메시지를 구분하여 보내주는 걸로 구분함
        
        //action -- 현재 유저정보, 메시지 및 채팅을 하려고 선택한 유저정보 로 이전 메시지 정보 조회
        //if 이전 메시지 정보가 있으면 for으로 adapter 추가
        if(intent.getStringExtra("messageType").equals("old")){
            messageReceiveAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "김현아", "안녕하십니까, 환자 여러분. 전북대 정형외과 외래교수 김현아입니다. ", "2022-01-01",1));
            messageReceiveAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "이인호", "넹 안녕하세요", "2022-01-01",2));
            messageReceiveAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "김현아", "안녕하십니까, 환자 여러분. 전북대 정형외과 외래교수 김현아입니다. ", "2022-01-01",3));
            messageReceiveAdapter.addItem(new MessageModel(0, "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "이인호", "넹 안녕하세요.", "2022-01-01",4));
        }
        //이전 메시지가 정보가 없으면 adapter 추가 없음

        //하나의 리싸이클러뷰에 두개의 어댑터를 연결해보려고 했는데 잘 안됨
        recyclerView.setAdapter(messageReceiveAdapter);

        //메시지 등록
        EditText messageText = findViewById(R.id.message_text);
        //메시지창 포커스
        messageText.requestFocus();
        //입력창 안열림
        //messageText.setShowSoftInputOnFocus(false);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        Button messageSend = findViewById(R.id.message_send);
        //전송버튼 클릭시 이벤트 
        messageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //입력창 닫힘
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                //action
                //입력된 내용 저장 및 화면에 불러오는 로직 필요함

                //입력창 비워줌
                messageText.setText("");
            }
        });


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
