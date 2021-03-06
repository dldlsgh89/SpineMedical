package org.dahlson.spinemedical;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LoginFragment loginFragment;
    JoinFragment joinFragment;

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.LoginFragment, loginFragment).commit();

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/

        /**ㅇ
         * 헤더 inflation 호출
         */
        this.inflateHeader();
        //FragmentManager manager = getSupportFragmentManager();
        //loginFragment = (LoginFragment) manager.findFragmentById(R.id.LoginFragment);
        //joinFragment = (JoinFragment) manager.findFragmentById(R.id.LoginFragment);
        // 화면 전환 프래그먼트 선언 및 초기 화면 설정
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
    }


    //가입 화면 프레그먼트로 전환
    public void replaceJoinFragment(JoinFragment joinFragment) {
        Log.d("spinemedical", "replaceJoinFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.LoginFragment, joinFragment).addToBackStack(null).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    //비밀번호 찾기 화면 프레그먼트로 전환
    public void replaceFinwPwFragment(FindPwFragment findPwFragment) {
        Log.d("spinemedical", "replaceFinwPwFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.LoginFragment, findPwFragment).addToBackStack(null).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    //로그인 화면 프레그먼트로 전환
    public void replaceLoginFragment(LoginFragment loginFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.LoginFragment, loginFragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    //로그인 화면 프레그먼트로 전환
    public void pairingFragment(PairingFragment pairingFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.LoginFragment, pairingFragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }


    public void SpineHomeActivity(){
        Intent intent = new Intent(this, SpineHomeActivity.class);
        startActivity(intent);
        //MainActivity를 종료(메모리에서 제거)
        finish();
    }

    public void PairingActivity(){
        Intent intent = new Intent(this, PairingActivity.class);
        startActivity(intent);
        //MainActivity를 종료(메모리에서 제거)
        finish();
    }


    //뒤로가기 버튼 커스텀
    public interface onKeyBackPressedListener {
        void onBackKey();
    }
    private MainActivity.onKeyBackPressedListener mOnKeyBackPressedListener;
    public void setOnKeyBackPressedListener(MainActivity.onKeyBackPressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    public void onBackPressed() {
        Log.d("spinemedical","MainActivity onBackPressed start");

        if (mOnKeyBackPressedListener != null) {
            Log.d("spinemedical","MainActivity onBackPressed if");
            mOnKeyBackPressedListener.onBackKey();
        } else {
            Log.d("spinemedical","MainActivity onBackPressed else");
            backKeyHandler.onBackPressed();
        }
    }
}