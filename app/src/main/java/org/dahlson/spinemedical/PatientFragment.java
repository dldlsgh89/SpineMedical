package org.dahlson.spinemedical;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PatientFragment extends Fragment {

    ViewGroup viewGroup;
    ArrayAdapter<String> adapter;

    String[] items = {"선택", "직접입력"};
    String[] hospital_items = {"선택", "직접입력"};
    String[] doctor_items = {"선택", "직접입력"};
    String[] group_items = {"선택", "활동형", "수면형", "세트"};
    String[] type_items = {"선택", "c", "역c", "s", "역s"};
    String[] stage_items = {"선택", "0", "1", "2", "3", "4", "5"};

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static PatientFragment newInstance() {
        return new PatientFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_patient, container, false);

        Context context = getContext();

        Spinner spinner_hospital = viewGroup.findViewById(R.id.spinner_hospital);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, hospital_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hospital.setAdapter(adapter);  //스피너에 어댑터 설정하기

        Spinner spinner_doctor = viewGroup.findViewById(R.id.spinner_doctor);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, doctor_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_doctor.setAdapter(adapter);  //스피너에 어댑터 설정하기

        Spinner spinner_group = viewGroup.findViewById(R.id.spinner_group);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, group_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_group.setAdapter(adapter);  //스피너에 어댑터 설정하기

        Spinner spinner_type = viewGroup.findViewById(R.id.spinner_type);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, type_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter);  //스피너에 어댑터 설정하기

        Spinner spinner_stage = viewGroup.findViewById(R.id.spinner_stage);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, stage_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_stage.setAdapter(adapter);  //스피너에 어댑터 설정하기

        Button change_btn = viewGroup.findViewById(R.id.change_btn);
        change_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        return viewGroup;
    }
}
