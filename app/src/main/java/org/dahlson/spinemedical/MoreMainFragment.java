package org.dahlson.spinemedical;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MoreMainFragment extends Fragment{
    ViewGroup viewGroup;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static MoreMainFragment newInstance() {
        return new MoreMainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_more, container, false);

        Button changePatient = viewGroup.findViewById(R.id.change_patient);
        Button changePass = viewGroup.findViewById(R.id.change_pass);
        Button changeDevice = viewGroup.findViewById(R.id.change_device);
        Button doctorConnect = viewGroup.findViewById(R.id.doctor_connect);
        Button patientWithdrawal = viewGroup.findViewById(R.id.patient_withdrawal);
        Button logout = viewGroup.findViewById(R.id.logout);

        //환자정보변경버튼
        changePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoreActivity)getActivity()).patientFragment(PatientFragment.newInstance(), "");
            }
        });

        //비밀번호변경버튼
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoreActivity)getActivity()).passFragment(PassFragment.newInstance(), "");
            }
        });
        //연동기기변경버튼
        changeDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoreActivity)getActivity()).deviceFragment(DeviceFragment.newInstance(), "");
            }
        });
        //담당의선택
        doctorConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoreActivity)getActivity()).doctorConnectFragment(DoctorConnectFragment.newInstance(), "");
            }
        });
        //회원탈퇴
        patientWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoreActivity)getActivity()).withdrawalFragment(WithdrawalFragment.newInstance(), "");
            }
        });
        //로그아웃버튼
        logout.setOnClickListener(new View.OnClickListener() {
            //action
            //로그아웃 관련 로직 필요
            @Override
            public void onClick(View v) {
                ((MoreActivity)getActivity()).MainActivity();
            }
        });
        return viewGroup;
    }
}
