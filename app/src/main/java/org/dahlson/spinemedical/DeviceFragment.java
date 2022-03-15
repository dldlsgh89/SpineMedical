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

public class DeviceFragment extends Fragment implements MoreActivity.onKeyBackPressedListener{

    ViewGroup viewGroup;

    public static DeviceFragment newInstance(){
        return new DeviceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_device, container, false);
        
        //기존에 연동된 기기번호 가져오는 로직 필요
        
        //현재 기기와 연결된 센서모듈에서 기기번호를 가져오는 로직 필요

        EditText nowDeviceText = viewGroup.findViewById(R.id.now_device);
        EditText newDeviceText = viewGroup.findViewById(R.id.new_device);
        //연동버튼 클릭
        Button button = viewGroup.findViewById(R.id.show_dialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());

                boolean formCheck = true;
                String strNewDevice = String.valueOf(newDeviceText.getText()); //새로운 기기번호
                if(strNewDevice == null || strNewDevice.equals("")){
                    formCheck = false;
                }
                //입력폼 체크
                if(formCheck){
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_dialog);
                    TextView message = dialog.findViewById(R.id.message);
                    message.setText("변경하시겠습니까?");
                    Button okButton = dialog.findViewById(R.id.okButton);
                    Button cancelButton = dialog.findViewById(R.id.cancelButton);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //가입환자 정보 서버로 넘겨주는 로직 필요
                            dialog.setContentView(R.layout.custom_dialog2);
                            TextView message = dialog.findViewById(R.id.message);
                            message.setText("기기가 변경되었습니다.");
                            Button okButton = dialog.findViewById(R.id.okButton);
                            okButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ((MoreActivity)getActivity()).moreMainFragment(MoreMainFragment.newInstance(),"");
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
