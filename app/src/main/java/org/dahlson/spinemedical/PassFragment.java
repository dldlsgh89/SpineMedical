package org.dahlson.spinemedical;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PassFragment extends Fragment implements MoreActivity.onKeyBackPressedListener{

    ViewGroup viewGroup;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static PassFragment newInstance() {
        return new PassFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_pass, container, false);

        EditText nowPwText = viewGroup.findViewById(R.id.now_pw);
        EditText newPwText = viewGroup.findViewById(R.id.new_pw);
        EditText newPwCheckText = viewGroup.findViewById(R.id.new_pw_check);

        //비밀번호변경 버튼 클릭
        Button change_btn = viewGroup.findViewById(R.id.change_btn);
        change_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());

                String strNowPw = String.valueOf(nowPwText.getText());
                String strNewPw = String.valueOf(newPwText.getText());
                String strNewPwCheck = String.valueOf(newPwCheckText.getText());
                boolean formCheck = true;
                if(strNowPw == null || strNowPw.equals("")){
                    formCheck = false;
                }
                if(strNewPw == null || strNewPw.equals("")){
                    formCheck = false;
                }
                if(strNewPwCheck == null || strNewPwCheck.equals("")){
                    formCheck = false;
                }

                if(formCheck){
                    //새로운 비밀번호 체크
                    if(!strNewPw.equals(strNewPwCheck)){
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_dialog2);
                        TextView message = dialog.findViewById(R.id.message);
                        message.setText("새로운 비밀번호가 일치하지 않습니다.");
                        Button okButton = dialog.findViewById(R.id.okButton);
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }else{
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_dialog);
                        TextView message = dialog.findViewById(R.id.message);
                        message.setText("비밀번호를 수정하시겠습니까?");
                        Button okButton = dialog.findViewById(R.id.okButton);
                        Button cancelButton = dialog.findViewById(R.id.cancelButton);
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //action
                                //수정된 환자 정보 서버로 넘겨주는 로직 필요
                                dialog.setContentView(R.layout.custom_dialog2);
                                TextView message = dialog.findViewById(R.id.message);
                                message.setText("수정되었습니다.");
                                Button okButton = dialog.findViewById(R.id.okButton);
                                okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ((MoreActivity)getActivity()).moreMainFragment(MoreMainFragment.newInstance(), "");
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                }else{
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_dialog2);
                    TextView message = dialog.findViewById(R.id.message);
                    message.setText("입력되지 않은 정보가 있습니다.");
                    Button okButton = dialog.findViewById(R.id.okButton);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
                dialog.show();
            }
        });

        return viewGroup;
    }

    //BackStack 으로 뒤로가기 버튼 누르면 전 화면으로 이동하기 위함
    @Override
    public void onBackKey() {
        Log.d("spinemedical","PassFragment onBackKey start");
        MoreActivity activity = (MoreActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("spinemedical","PassFragment onAttach start");
        super.onAttach(context);
        ((MoreActivity)context).setOnKeyBackPressedListener(this);
    }
}
