package org.dahlson.spinemedical;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {
    Handler handler = new Handler();

    //style 테마로 화면을 만들때는 xml 레이아웃에 대한 인플레이션을 진행하지 않기 때문에
    //seContentView가 없음
    //스플래시 화면의 경우 화면이 보여지고 1초후에 메인 화면으로 전환되도록 핸들러 이용 필요


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1초 뒤에 MainActivity 를 띄워주도록 하는 메서드
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
