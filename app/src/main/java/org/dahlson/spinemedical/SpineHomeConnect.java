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
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog3);

                Button okButton = dialog.findViewById(R.id.okButton);
                Button closeButton = dialog.findViewById(R.id.close_btn);
                Button cancelButton = dialog.findViewById(R.id.cancel_btn);

                TextView nowPressure = dialog.findViewById(R.id.now_pressure);  //현재 압력
                TextView averagePressure = dialog.findViewById(R.id.average_pressure);  //평균 압력
                TextView nowTemperature = dialog.findViewById(R.id.now_temperature);  //현재 온도
                TextView averageTemperature = dialog.findViewById(R.id.average_temperature);  //평균 온도

                //체크시작버튼
                okButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        //체크시작버튼 클릭시 시작버튼 안보이고 취소버튼 보임
                        okButton.setVisibility(View.GONE);
                        cancelButton.setVisibility(View.VISIBLE);
                        
                        //action
                        //연동된 센서모듈에서 실시간 데이터 받아와 보여주는 로직 필요
                        
                        //임의의 데이터니까 지워도 됨
                        nowPressure.setText("0.6");
                        averagePressure.setText("0.5");
                        nowTemperature.setText("20.4");
                        averageTemperature.setText("20.6");
                    }
                });
                
                //정지버튼
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //정지버튼 클릭시 정지버튼 안보이고 시작버튼 보임
                        cancelButton.setVisibility(View.GONE);
                        okButton.setVisibility(View.VISIBLE);

                        nowPressure.setText("-");
                        averagePressure.setText("-");
                        nowTemperature.setText("-");
                        averageTemperature.setText("-");
                    }
                });
                
                //닫기버튼
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
                
                //데이터 전송 확인 버튼 클릭
                okButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        dialog.dismiss();
                        //action
                        //연동된 센서모듈에서 저장된 데이터를 받아와 서버로 전송해줘야함.
                        //화면 보여주기용 프로그래스 다이얼로그
                        CheckTypesTask checkTypesTask = new CheckTypesTask(getActivity());
                        checkTypesTask.execute();
                    }
                });
        
                //취소 버튼 클릭
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
