package org.dahlson.spinemedical;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.MessageModel;
import org.dahlson.spinemedical.model.SpineDataModel;

public class MassageActivity extends BaseActivity {
    RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customSetContentView(R.layout.activity_massage);

        recyclerView = findViewById(R.id.message_recyclerView);
        //리싸이클러뷰에 레이아웃 매니저 설정하기
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        MessageAdapter messageAdapter = new MessageAdapter();
        messageAdapter.addItem(new MessageModel(0, getURLForDrawable("doctor.png"), "김현아", "안녕하십니까, 환자 여러분", "2022-01-01"));
        messageAdapter.addItem(new MessageModel(0, getURLForDrawable("doctor.png"), "김현아", "안녕하십니까, 환자 여러분", "2022-01-01"));
        messageAdapter.addItem(new MessageModel(0, getURLForDrawable("doctor.png"), "김현아", "안녕하십니까, 환자 여러분", "2022-01-01"));
        messageAdapter.addItem(new MessageModel(0, getURLForDrawable("doctor.png"), "김현아", "안녕하십니까, 환자 여러분", "2022-01-01"));

        recyclerView.setAdapter(messageAdapter);
        inflateHeader("메시지 목록");
    }

    private String getURLForResource(String fileName) {
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + fileName).toString();
    }

    private String getURLForDrawable(String fileName) {
        return "@drawable/" + fileName;
    }
}
