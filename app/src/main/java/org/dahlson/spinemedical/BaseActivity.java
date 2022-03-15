package org.dahlson.spinemedical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public abstract class BaseActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    protected static Activity activity;
    protected LinearLayout headerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //activiy 화면을 inflate하고 bottomNavigationView에 itemselectListener 바인딩
    protected void customSetContentView(int layoutResID){
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(layoutResID, null);
        ConstraintLayout activityContainer = (ConstraintLayout) fullView.findViewById(R.id.main_fragment);
        super.setContentView(fullView);
        activity = this;
        bottomNavigationView = findViewById(R.id.home_menu_bar);
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    //해더 타이틀 변경
    protected void inflateHeader(String text)
    {
        // inflation 대상을 포함시키는 레이아웃
        headerLayout = (LinearLayout)findViewById(R.id.header);
        // inflater 객체 생성.
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 레이아웃을 inflate한다.
        inflater.inflate(R.layout.header, headerLayout, true);
        // inflate 이후 텍스트 바꿔줌
        TextView textView = (TextView)headerLayout.findViewById(R.id.title_text);
        textView.setText(text);
    }

    //해더 백그라운드 컬러 변경
    protected void inflateHeaderSetColor(String text)
    {
        // inflate 이후 색 바꿔줌
        LinearLayout headerLayout2 = (LinearLayout)headerLayout.findViewById(R.id.message);
        Log.d("spinemedical", "BaseActivity - Color.parseColor(text) : " + Color.parseColor(text));
        headerLayout2.setBackgroundColor(Color.parseColor(text));
    }

    //시작시 현재 activity의 네비게이션메뉴의 itemid를 가져옴
    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    
    //선택된 메뉴 activity로 이동
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent2;
        switch(item.getItemId()) {
            case R.id.data_tab:
                intent2 = new Intent(this, SpineHomeActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.message_tab:
                intent2 = new Intent(this, MessageActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.more_tab:
                intent2 = new Intent(this, MoreActivity.class);
                this.startActivity(intent2);
                break;
        }
        this.finish();
        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    //선택된 메뉴아이템을 check
    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = bottomNavigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

}
