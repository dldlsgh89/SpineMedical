package org.dahlson.spinemedical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BaseActivity extends AppCompatActivity {

    protected static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_base);
    }

    /*@Override
    public void setContentView(int layoutResID){
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(layoutResID, null);
        ConstraintLayout activityContainer = (ConstraintLayout) fullView.findViewById(R.id.main_fragment);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);

        activity = this;
       *//* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //툴바 사용여부 결정(기본적으로 사용)
        if(useToolbar()){ setSupportActionBar(toolbar);
            setTitle("툴바예제");
        } else {
            toolbar.setVisibility(View.GONE);
        }*//*
    }*/

    protected void customSetContentView(int layoutResID){
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(layoutResID, null);
        ConstraintLayout activityContainer = (ConstraintLayout) fullView.findViewById(R.id.main_fragment);
        super.setContentView(fullView);

        activity = this;

        BottomNavigationView bottomNavigationView = findViewById(R.id.home_menu_bar);
        ItemSelectedListener itemSelectedListener = new ItemSelectedListener(activity);
        bottomNavigationView.setOnItemSelectedListener(itemSelectedListener);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //툴바 사용여부 결정(기본적으로 사용)
        if(useToolbar()){ setSupportActionBar(toolbar);
            setTitle("툴바예제");
        } else {
            toolbar.setVisibility(View.GONE);
        }*/

    }

    protected void inflateHeader(String text)
    {
        // inflation 대상을 포함시키는 레이아웃
        LinearLayout headerLayout = (LinearLayout)findViewById(R.id.header);
        // inflater 객체 생성.
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 레이아웃을 inflate한다.
        inflater.inflate(R.layout.header, headerLayout, true);
        // inflate 이후 텍스트 바꿔줌
        TextView textView = (TextView)headerLayout.findViewById(R.id.title_text);
        textView.setText(text);
    }

    //툴바를 사용할지 말지 정함
    protected boolean useToolbar(){
        return true;
    }

    /*//메뉴 등록하기
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }*/

    //앱바 메뉴 클릭 이벤트
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu1:
                Toast.makeText(getApplicationContext()
                        ,"메뉴1 클릭"
                        , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SubActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_menu2:
                Toast.makeText(getApplicationContext()
                        ,"메뉴2 클릭"
                        , Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, Sub2Activity.class);
                startActivity(intent2);
                return true;
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext()
                        ,"앱설정"
                        , Toast.LENGTH_SHORT).show();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }*/


    static class ItemSelectedListener extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
        private Context context;
        public ItemSelectedListener(Context thisContext) {
            this.context = thisContext;
        }
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Intent intent2;
            switch(menuItem.getItemId()) {
                case R.id.data_tab:
                    intent2 = new Intent(context, SpineHomeActivity.class);
                    context.startActivity(intent2);
                    activity.finish();
                    break;
                case R.id.message_tab:

                    break;
                case R.id.more_tab:
                    intent2 = new Intent(context, MoreActivity.class);
                    context.startActivity(intent2);
                    activity.finish();
                    break;
            }
            return true;
        }
    }
}
