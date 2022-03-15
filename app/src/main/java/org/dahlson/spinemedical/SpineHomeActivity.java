package org.dahlson.spinemedical;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SpineHomeActivity extends BaseActivity {
    SpineHomeContent spineHomeContent;
    SpineHomeNoneConnect spineHomeNoneConnect;

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("spinemedical","SpineHomeActivity onCreate start");
        //startActivity로 화면을 바꿔줬다고 해서 화면을 구성해주는건 아님. contentview 지정이 필요
        //setContentView(R.layout.activity_spine_home);
        customSetContentView(R.layout.activity_spine_home);

        inflateHeader("교정기 착용데이터");
        //시작 fragment 지정
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        spineHomeContent = new SpineHomeContent();
        spineHomeNoneConnect = new SpineHomeNoneConnect();
        fragmentTransaction.add(R.id.main_fragment, spineHomeContent);
        fragmentTransaction.add(R.id.ButtonFragment, spineHomeNoneConnect);
        fragmentTransaction.commit();

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

    @Override
    int getContentViewId() {
        return R.layout.activity_spine_home;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.data_tab;
    }
}
