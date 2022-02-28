package org.dahlson.spinemedical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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

        inflateHeader("더보기");
    }



    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void patientFragment(PatientFragment fragment, String text) {
        Log.d("spinemedical", "patientFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
        //MoreActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
        //MoreActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
    }

    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void passFragment(PassFragment fragment, String text) {
        Log.d("spinemedical", "passFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
    }

    //프레그먼트 내부에서 다른 프레그먼트로 전환
    public void deviceFragment(DeviceFragment fragment, String text) {
        Log.d("spinemedical", "deviceFragment start");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
    }

    public void MainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //MainActivity를 종료(메모리에서 제거)
        finish();
    }
    
    //뒤로가기 버튼 커스텀
    public interface onKeyBackPressedListener {
        void onBackKey();
    }
    private onKeyBackPressedListener mOnKeyBackPressedListener;
    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    public void onBackPressed() {
        Log.d("spinemedical","MoreActivity onBackPressed start");

        if (mOnKeyBackPressedListener != null) {
            Log.d("spinemedical","MoreActivity onBackPressed if");
            mOnKeyBackPressedListener.onBackKey();
        } else {
            Log.d("spinemedical","MoreActivity onBackPressed else");
            backKeyHandler.onBackPressed();
        }
    }
}
