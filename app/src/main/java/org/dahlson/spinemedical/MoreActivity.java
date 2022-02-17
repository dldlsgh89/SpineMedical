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

public class MoreActivity extends BaseActivity {

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);
    MoreMainFragment moreMainFragment;
    PatientFragment patientFragment;
    PassFragment passFragment;
    DeviceFragment deviceFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_more);
        customSetContentView(R.layout.activity_more);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        moreMainFragment = new MoreMainFragment();
        fragmentTransaction.add(R.id.main_fragment, moreMainFragment);
        fragmentTransaction.commit();

        /**ㅇ
         * 헤더 inflation 호출
         */
        inflateHeader("더보기");
    }



    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void patientFragment(PatientFragment fragment, String text) {
        Log.d("spinemedical", "noneConnectFragment start");
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).addToBackStack(null).commit();*/
        MoreActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
    }

    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void passFragment(PassFragment fragment, String text) {
        Log.d("spinemedical", "ConnectFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
    }

    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void deviceFragment(DeviceFragment fragment, String text) {
        Log.d("spinemedical", "ConnectFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
    }

    //뒤로가기 버튼을 뺏어올 리스너 등록
    public interface onKeyBackPressedListener {
        void onBackKey();
    }
    private onKeyBackPressedListener mOnKeyBackPressedListener;
    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }

    //뒤로가기 클릭시 종료
    /*@Override
    public void onBackPressed() {
        backKeyHandler.onBackPressed();
    }*/

    //메인에서 토스트를 띄우며 종료확인을 하기 위해 필드선언
    //EndToast endToast = new EndToast(this);
    @Override
    public void onBackPressed() {
        Log.d("spinemedical","MoreActivity onBackPressed start");
        if (mOnKeyBackPressedListener != null) {
            Log.d("spinemedical","MoreActivity onBackPressed if");
            mOnKeyBackPressedListener.onBackKey();
        } else {
            Log.d("spinemedical","MoreActivity onBackPressed else");
            backKeyHandler.onBackPressed();
            /*//쌓인 BackStack 여부에 따라 Toast를 띄울지, 뒤로갈지
            if(getSupportFragmentManager().getBackStackEntryCount()==0){
                //* 종료 EndToast Bean 사용
                //endToast.showEndToast("종료하려면 한번 더 누르세요.");

            }else{
                super.onBackPressed();
            }*/
        }
    }
}
