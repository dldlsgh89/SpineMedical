package org.dahlson.spinemedical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SpineHomeActivity extends AppCompatActivity {
    SpineHomeContent spineHomeContent;
    SpineHomeNoneConnect spineHomeNoneConnect;

    private static Activity activity;


    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("spinemedical","SpineHomeActivity onCreate start");
        //startActivity로 화면을 바꿔줬다고 해서 화면을 구성해주는건 아님. contentview 지정이 필요
        setContentView(R.layout.activity_spine_home);

        activity = SpineHomeActivity.this;
        
        //시작 fragment 지정
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        spineHomeContent = new SpineHomeContent();
        spineHomeNoneConnect = new SpineHomeNoneConnect();

        //fragmentTransaction.add는 Activity쪽이 프레그먼트가 아닐경우 가능하다.
        //만약 activity쪽 tag가 framgment 라고 한다면 add가 아닌 replace로 바꿔줄 수 있음.
        //단 tag가 fargment면 tag 내에 미리 tag 내에 들어갈 화면의 name이 들어가 있어야함.
        fragmentTransaction.add(R.id.MainFragment, spineHomeContent);
        fragmentTransaction.add(R.id.ButtonFragment, spineHomeNoneConnect);
        fragmentTransaction.commit();

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.home_menu_bar);
        //툴바 사용여부 결정(기본적으로 사용)
        if(useToolbar()){
            setSupportActionBar(toolbar);
        } else {
            toolbar.setVisibility(View.GONE);
        }*/

        BottomNavigationView bottomNavigationView = findViewById(R.id.home_menu_bar);
        ItemSelectedListener itemSelectedListener = new ItemSelectedListener(activity);
        bottomNavigationView.setOnItemSelectedListener(itemSelectedListener);

        /**ㅇ
         * 헤더 inflation 호출
         */
        this.inflateHeader();

    }

    /**
     * 헤더 inflation
     */
    private void inflateHeader()
    {
        // inflation 대상을 포함시키는 레이아웃
        LinearLayout headerLayout = (LinearLayout)findViewById(R.id.header);
        // inflater 객체 생성.
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 레이아웃을 inflate한다.
        inflater.inflate(R.layout.header, headerLayout, true);
        // inflate 이후 텍스트 바꿔줌
        TextView textView = (TextView)headerLayout.findViewById(R.id.title_text);
        textView.setText("교정기 착용데이터");
    }

    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void noneConnectFragment(SpineHomeNoneConnect fragment, String text) {
        Log.d("spinemedical", "noneConnectFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ButtonFragment, fragment).commit();
    }

    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void ConnectFragment(SpineHomeConnect fragment, String text) {
        Log.d("spinemedical", "ConnectFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ButtonFragment, fragment).commit();
    }

    //뒤로가기 클릭시 종료 
    @Override
    public void onBackPressed() {
        backKeyHandler.onBackPressed();
    }

    //툴바를 사용할지 말지 정함
    /*protected boolean useToolbar(){
        return true;
    }*/

    /*//메뉴 등록하기
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
    
    //앱바 메뉴 클릭 이벤트
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.data_tab:
                Toast.makeText(getApplicationContext(),"메뉴1 클릭", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SpineHomeActivity.class);
                startActivity(intent);
                return true;
            *//*case R.id.action_menu2: Toast.makeText(getApplicationContext(),
                    "메뉴2 클릭",
                    Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(this, Sub2Activity.class);
            startActivity(intent2);
            return true;*//*
            case R.id.more_tab:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent3 = new Intent(this, MoreActivity.class);
                startActivity(intent3);
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
