package org.dahlson.spinemedical;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

public class WithdrawalFragment  extends Fragment implements MoreActivity.onKeyBackPressedListener {

    ViewGroup viewGroup;

    public static WithdrawalFragment newInstance(){
        return new WithdrawalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_withdrawal, container, false);

        Button withdrawalBtn = viewGroup.findViewById(R.id.withdrawal_btn);
        withdrawalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nowPw = viewGroup.findViewById(R.id.now_pw);
                String strNowPw = String.valueOf(nowPw.getText());
                Dialog dialog = new Dialog(getActivity());
                if(strNowPw.equals("")){
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_dialog2);
                    TextView message = dialog.findViewById(R.id.message);
                    message.setText("비밀번호를 입력하지 않았습니다.");
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
                    message.setText("탈퇴하게 되면 되돌릴 수 없습니다. 정말 탈퇴하시겠습니까?");
                    Button okButton = dialog.findViewById(R.id.okButton);
                    Button cancelButton = dialog.findViewById(R.id.cancelButton);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //action
                            //실제 탈퇴처리 로직

                            dialog.setContentView(R.layout.custom_dialog2);
                            TextView message = dialog.findViewById(R.id.message);
                            message.setText("탈퇴되었습니다..");
                            Button okButton = dialog.findViewById(R.id.okButton);
                            okButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
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
                dialog.show();
            }
        });

        return viewGroup;
    }

    //BackStack 으로 뒤로가기 버튼 누르면 전 화면으로 이동하기 위함
    @Override
    public void onBackKey() {
        Log.d("spinemedical","DeviceFragment onBackKey start");
        MoreActivity activity = (MoreActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("spinemedical","DeviceFragment onAttach start");
        super.onAttach(context);
        ((MoreActivity)context).setOnKeyBackPressedListener(this);
    }
}
