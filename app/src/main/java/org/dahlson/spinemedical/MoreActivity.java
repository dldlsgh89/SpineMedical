package org.dahlson.spinemedical;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MoreActivity extends AppCompatActivity {

    private static Activity activity;

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);
    MoreMainFragment moreMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        activity = MoreActivity.this;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        moreMainFragment = new MoreMainFragment();
        fragmentTransaction.add(R.id.more_fragment, moreMainFragment);
        fragmentTransaction.commit();

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

    //뒤로가기 클릭시 종료
    @Override
    public void onBackPressed() {
        backKeyHandler.onBackPressed();
    }

    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void patientFragment(PatientFragment fragment, String text) {
        Log.d("spinemedical", "noneConnectFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.more_fragment, fragment).commit();
    }

    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void passFragment(PassFragment fragment, String text) {
        Log.d("spinemedical", "ConnectFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.more_fragment, fragment).commit();
    }

    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void deviceFragment(DeviceFragment fragment, String text) {
        Log.d("spinemedical", "ConnectFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.more_fragment, fragment).commit();
    }
}
