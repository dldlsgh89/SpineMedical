package org.dahlson.spinemedical;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MoreMainFragment extends Fragment {
    ViewGroup viewGroup;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static MoreMainFragment newInstance() {
        return new MoreMainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_more, container, false);

        Button change_patient = viewGroup.findViewById(R.id.change_patient);
        Button change_pass = viewGroup.findViewById(R.id.change_pass);
        Button change_device = viewGroup.findViewById(R.id.change_device);
        Button logout = viewGroup.findViewById(R.id.logout);

        change_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoreActivity)getActivity()).patientFragment(PatientFragment.newInstance(), "");
            }
        });

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoreActivity)getActivity()).passFragment(PassFragment.newInstance(), "");
            }
        });

        change_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoreActivity)getActivity()).deviceFragment(DeviceFragment.newInstance(), "");
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return viewGroup;
    }
}
