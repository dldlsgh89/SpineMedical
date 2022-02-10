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

public class LoginFragment extends Fragment {
    ViewGroup viewGroup;

    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        Button login_btn = viewGroup.findViewById(R.id.login_btn);
        Button join_btn = viewGroup.findViewById(R.id.join_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).SpineHomeActivity();

            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText login_id = viewGroup.findViewById(R.id.login_id);
                String text = login_id.getText().toString();
                //getActivity()로 MainActivity의 replaceFragment를 불러옵니다.
                text = text != null ? text : "";
                ((MainActivity)getActivity()).replaceJoinFragment(JoinFragment.newInstance(), text);    // 새로 불러올 Fragment의 Instance를 Main으로 전달
            }
        });

        return viewGroup;
    }
}
