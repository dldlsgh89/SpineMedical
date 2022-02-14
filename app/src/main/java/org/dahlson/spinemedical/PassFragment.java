package org.dahlson.spinemedical;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PassFragment extends Fragment {

    ViewGroup viewGroup;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static PassFragment newInstance() {
        return new PassFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_pass, container, false);

        Button change_btn = viewGroup.findViewById(R.id.change_btn);
        change_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        return viewGroup;
    }
}
