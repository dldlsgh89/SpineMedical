package org.dahlson.spinemedical;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import butterknife.internal.Utils;

public class MainActivity extends AppCompatActivity {
    LoginFragment loginFragment;
    JoinFragment joinFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.LoginFragment, LoginFragment.newInstance()).commit();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        /**
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


    //프레그먼트 내부에서 다른 프레그먼트로 전환
    /*public void replaceFragment(JoinFragment joinFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.LoginFragment, joinFragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }*/



}