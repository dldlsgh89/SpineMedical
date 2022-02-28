package org.dahlson.spinemedical;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        Button button = viewGroup.findViewById(R.id.show_dialog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
