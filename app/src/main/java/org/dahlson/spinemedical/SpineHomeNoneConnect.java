package org.dahlson.spinemedical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SpineHomeNoneConnect extends Fragment {
    ViewGroup viewGroup;
    Context context;

    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("spinemedical","SpineHomeNoneConnect onCreateView start");
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home_none_connect, container, false);

        Button button = viewGroup.findViewById(R.id.gearing_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("spinemedical","SpineHomeNoneConnect onCreateView onClick start");
                request_dialog();
            }
        });

        return viewGroup;
    }

    public void request_dialog(){
        String title = "원격 요청";
        String message = "데이터를 요청하시겠습니까?";
        String titleButtonYes = "예";
        String titleButtonNo = "아니오";
        AlertDialog dialog = makeRequestDialog(title, message, titleButtonYes, titleButtonNo);
        dialog.show();
    }

    private AlertDialog makeRequestDialog(CharSequence title, CharSequence message, CharSequence titleButtonYes, CharSequence titleButtonNo){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((SpineHomeActivity)getActivity()).ConnectFragment(SpineHomeConnect.newInstance(), "");
                    }
                }, 1);
            }
        });

        builder.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
