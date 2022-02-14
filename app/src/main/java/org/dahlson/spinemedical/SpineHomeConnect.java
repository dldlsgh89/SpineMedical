package org.dahlson.spinemedical;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SpineHomeConnect extends Fragment {
    ViewGroup viewGroup;
    Context context;

    Handler handler = new Handler();

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static SpineHomeConnect newInstance() {
        return new SpineHomeConnect();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home_connect, container, false);
        context = getContext();

        
        //실시간 데이터 버튼
        Button button = viewGroup.findViewById(R.id.realtime_data_btn);
        //데이터 전송 버튼
        Button button1 = viewGroup.findViewById(R.id.data_send_btn);
        
        //실시간 데이터 버튼 클릭 리스너
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CustomDialog customDialog = new CustomDialog(getActivity());
                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                //customDialog.callFunction();

                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog3);

                Button okButton = dialog.findViewById(R.id.okButton);
                Button closeButton = dialog.findViewById(R.id.close_btn);
                Button cancelButton = dialog.findViewById(R.id.cancel_btn);

                okButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        okButton.setVisibility(View.GONE);
                        cancelButton.setVisibility(View.VISIBLE);
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelButton.setVisibility(View.GONE);
                        okButton.setVisibility(View.VISIBLE);
                    }
                });

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        //데이터 전송버튼 클릭 리스너
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);

                TextView view = dialog.findViewById(R.id.message);
                view.setText("기기 데이터를 전송하시겠습니까?");

                Button okButton = dialog.findViewById(R.id.okButton);
                Button cancelButton = dialog.findViewById(R.id.cancelButton);

                okButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        dialog.dismiss();
                        CheckTypesTask checkTypesTask = new CheckTypesTask(getActivity());
                        checkTypesTask.execute();
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return viewGroup;
    }
}
