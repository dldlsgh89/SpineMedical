package org.dahlson.spinemedical;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    ViewGroup viewGroup;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("spinemedical", "LoginFragment start");
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        Button loginBtn = viewGroup.findViewById(R.id.login_btn);
        Button joinFormBtn = viewGroup.findViewById(R.id.join_form_btn);
        Button findPwBtn = (Button) viewGroup.findViewById(R.id.find_pw);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText loginId = viewGroup.findViewById(R.id.login_id);  //아이디
                EditText loginPw = viewGroup.findViewById(R.id.login_pw);  //패스워드
                String strLoginId = String.valueOf(loginId.getText());
                String strLoginPw = String.valueOf(loginPw.getText());
                boolean loginCheck = true;
                if(strLoginId == null || strLoginId.equals("")){
                    loginCheck = false;
                }
                if(strLoginPw == null || strLoginPw.equals("")){
                    loginCheck = false;
                }
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog2);

                TextView message = dialog.findViewById(R.id.message);
                message.setText("입력되지 않은 정보가 있습니다.");
                Button okButton = dialog.findViewById(R.id.okButton);
                if(loginCheck){
                    //action
                    Log.d("spinemedical", "strLoginId : " + strLoginId);
                    Log.d("spinemedical", "strLoginPw : " + strLoginPw);
                    ((MainActivity)getActivity()).SpineHomeActivity();
                    //실제로는 유효성 검사 및 로그인 처리
                }else {
                    dialog.show();
                }

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        
        //가입화면으로 이동
        joinFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity()로 MainActivity의 replaceFragment를 불러옵니다.
                ((MainActivity)getActivity()).replaceJoinFragment(JoinFragment.newInstance());    // 새로 불러올 Fragment의 Instance를 Main으로 전달*/
            }
        });
        
        //비밀번호 초기화 화면으로 이동
        findPwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFinwPwFragment(FindPwFragment.newInstance());
            }
        });

        return viewGroup;
    }
}
