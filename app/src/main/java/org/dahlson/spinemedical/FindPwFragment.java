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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class FindPwFragment extends Fragment implements MainActivity.onKeyBackPressedListener{

    ViewGroup viewGroup;
    EditText idText;
    EditText emailText;
    AppCompatButton findPwBtn;

    /**
     * 헤더 inflation
     */
    private void inflateHeader()
    {
        // inflation 대상을 포함시키는 레이아웃
        LinearLayout headerLayout = (LinearLayout)getActivity().findViewById(R.id.header);
        TextView textView = headerLayout.findViewById(R.id.title_text);
        textView.setText("비밀번호 초기화");
    }

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static FindPwFragment newInstance() {
        return new FindPwFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("spinemedical", "FindPwFragment start");
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_find_pw, container, false);

        idText = viewGroup.findViewById(R.id.id_text);
        emailText = viewGroup.findViewById(R.id.email_text);
        findPwBtn = viewGroup.findViewById(R.id.find_pw_btn);

        findPwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strIdText = String.valueOf(idText.getText());
                strIdText = strIdText.trim();
                String strEmailText = String.valueOf(emailText.getText());
                strEmailText = strEmailText.trim();
                boolean idCheck = true;
                if(strIdText == null || strIdText.equals("")){
                    idCheck = false;
                }
                if(strEmailText == null || strEmailText.equals("")){
                    idCheck = false;
                }

                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                if(idCheck){
                    dialog.setContentView(R.layout.custom_dialog);
                    TextView message = dialog.findViewById(R.id.message);
                    message.setText("비밀번호를 초기화하시겠습니까?");
                    Button okButton = dialog.findViewById(R.id.okButton);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //action
                            //해당 이메일로 가입된 유저가 있는지 확인후
                            //입력된 이메일의 유저 정보에서 비밀번호를 초기화 화여 업데이트 한뒤에
                            //업데이트 한 초기화 비밀번호를 등록된 이메일로 전송해주는 로직 필요
                            dialog.setContentView(R.layout.custom_dialog2);
                            TextView message = dialog.findViewById(R.id.message);
                            message.setText("아이디에 등록된 이메일로 초기화된 암호가 전송되었습니다.");
                            Button okButton = dialog.findViewById(R.id.okButton);
                            okButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ((MainActivity)getActivity()).replaceLoginFragment(LoginFragment.newInstance());
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                    Button cancelBtn = dialog.findViewById(R.id.cancelButton);
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }else {
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
                    dialog.show();
                }
            }
        });

        return viewGroup;
    }

    //BackStack 으로 뒤로가기 버튼 누르면 전 화면으로 이동하기 위함
    @Override
    public void onBackKey() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)context).setOnKeyBackPressedListener(this);
    }
}
