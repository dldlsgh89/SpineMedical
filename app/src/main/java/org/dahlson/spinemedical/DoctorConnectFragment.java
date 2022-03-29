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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.DoctorModel;
import org.dahlson.spinemedical.model.SpineDataModel;
import org.dahlson.spinemedical.util.RecyclerDecoration;

public class DoctorConnectFragment extends Fragment implements MoreActivity.onKeyBackPressedListener{

    ViewGroup viewGroup;
    Context context2;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static DoctorConnectFragment newInstance() {
        return new DoctorConnectFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_doctor_connect, container, false);
        Context context, context2 = getContext();
        Log.d("spinemedical","DoctorConnectFragment start");

        //검색버튼 클릭
        Button searchDoctorBtn = viewGroup.findViewById(R.id.search_doctor_btn);
        searchDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchDoctor = viewGroup.findViewById(R.id.search_doctor);
                String strSearchDoctorsearch = String.valueOf(searchDoctor.getText());
                if(strSearchDoctorsearch != null && strSearchDoctorsearch != ""){
                    //action
                    //입력된 아이디나 이름으로 검색 시작
                }
            }
        });

        return viewGroup;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = viewGroup.findViewById(R.id.doctor_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DoctorListAdapter doctorListAdapter = new DoctorListAdapter(context2, viewGroup);
        doctorListAdapter.addItem(new DoctorModel(0,"김진우", "중앙병원", "test01"));
        doctorListAdapter.addItem(new DoctorModel(0,"강진우", "서울대병원", "test02"));
        doctorListAdapter.addItem(new DoctorModel(0,"오진우", "충북대병원", "test03"));
        doctorListAdapter.addItem(new DoctorModel(0,"양진우", "충남대병원", "test04"));
        doctorListAdapter.addItem(new DoctorModel(0,"이진우", "전북대병원", "test05"));
        doctorListAdapter.addItem(new DoctorModel(0,"김우성", "중앙병원", "test06"));
        doctorListAdapter.addItem(new DoctorModel(0,"강창권", "서울대병원", "test07"));
        doctorListAdapter.addItem(new DoctorModel(0,"오반석", "충북대병원", "test08"));
        doctorListAdapter.addItem(new DoctorModel(0,"양오동", "충남대병원", "test09"));
        doctorListAdapter.addItem(new DoctorModel(0,"이의진", "전북대병원", "test10"));
        doctorListAdapter.addItem(new DoctorModel(0,"김보민", "중앙병원", "test11"));
        doctorListAdapter.addItem(new DoctorModel(0,"강칠구", "서울대병원", "test12"));
        doctorListAdapter.addItem(new DoctorModel(0,"오철민", "충북대병원", "test13"));
        doctorListAdapter.addItem(new DoctorModel(0,"양우진", "충남대병원", "test14"));
        doctorListAdapter.addItem(new DoctorModel(0,"이종수", "전북대병원", "test15"));
        recyclerView.setAdapter(doctorListAdapter);

        // 아이템간 공백 추가
        RecyclerDecoration spaceDecoration = new RecyclerDecoration("h",-10,0);
        recyclerView.addItemDecoration(spaceDecoration);

        doctorListAdapter.setOnItemClickListener(new OnDoctorItemClickListener() {
            @Override
            public void onItemClick(DoctorListAdapter.ViewHolder holder, TextView textView) {
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);
                TextView message = dialog.findViewById(R.id.message);

                String strDoctorName = String.valueOf(holder.textView1.getText());

                message.setText(strDoctorName + " 의사에게 담당의 연결 요청 메시지를 보내겠습니까?");
                Button okButton = dialog.findViewById(R.id.okButton);
                Button cancelButton = dialog.findViewById(R.id.cancelButton);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //메시지 보내는 로직 필요
                        dialog.setContentView(R.layout.custom_dialog2);
                        TextView message = dialog.findViewById(R.id.message);
                        message.setText("해당 의사에게 담당의 연결 요청 메시지를 보냈습니다.");
                        Button okButton = dialog.findViewById(R.id.okButton);
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
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
                dialog.show();
            }
        });
    }

    //BackStack 으로 뒤로가기 버튼 누르면 전 화면으로 이동하기 위함
    @Override
    public void onBackKey() {
        Log.d("spinemedical","DoctorConnectFragment onBackKey start");
        MoreActivity activity = (MoreActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        //getActivity().getSupportFragmentManager().beginTransaction().remove(patientFragment).commit();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("spinemedical","DoctorConnectFragment onAttach start");
        super.onAttach(context);
        ((MoreActivity)context).setOnKeyBackPressedListener(this);
    }
}
