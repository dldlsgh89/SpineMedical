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

public class MoreMainFragment extends Fragment implements MoreActivity.onKeyBackPressedListener{
    ViewGroup viewGroup;
    Context context;
    private OnBackPressedCallback callback;

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


    //BackStack 으로 뒤로가기 버튼 누르면 전 화면으로 이동하기 위함
    @Override
    public void onBackKey() {
        Log.d("spinemedical","MoreMainFragment onBackKey start");
        /*MoreActivity activity = (MoreActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();*/

        ((MoreActivity)getActivity()).onBackHandler();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("spinemedical","MoreMainFragment onAttach start");
        super.onAttach(context);

       /* callback = new OnBackPressedCallback(true){
            @Override
            public void handleOnBackPressed(){
                onBackKey();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);*/

        ((MoreActivity)context).setOnKeyBackPressedListener(this);
    }

    /*@Override
    public void onPause() {
        Log.d("spinemedical","MoreMainFragment onPause start");
        super.onPause();
        //this.onStop();
        //((MoreActivity)context).setOnKeyBackPressedListener(null);
    }

    @Override
    public void onStop() {
        Log.d("spinemedical","MoreMainFragment onStop start");
        super.onStop();
    }

    @Override
    public void onDetach(){
        super.onDetach();
        callback.remove();
    }*/
}
