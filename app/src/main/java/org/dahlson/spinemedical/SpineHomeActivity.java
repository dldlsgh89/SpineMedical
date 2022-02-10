package org.dahlson.spinemedical;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SpineHomeActivity extends AppCompatActivity {
    SpineHomeContent spineHomeContent;
    SpineHomeNoneConnect spineHomeNoneConnect;

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("spinemedical","SpineHomeActivity onCreate start");
        //startActivity로 화면을 바꿔줬다고 해서 화면을 구성해주는건 아님. contentview 지정이 필요
        setContentView(R.layout.activity_spine_home);
        
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

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
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

    @Override
    public void onBackPressed() {
        backKeyHandler.onBackPressed();
    }
}
