package org.dahlson.spinemedical;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class JoinFragment extends Fragment {

    String arguments;

    String[] items = {"선택", "직접입력"};


    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static JoinFragment newInstance() {
        return new JoinFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("spinemedical", "onCreateView start");
        Log.d("spinemedical", "arguments : " + arguments);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_join, container, false);

        TextView textView = viewGroup.findViewById(R.id.textView7);

        Spinner spinner = viewGroup.findViewById(R.id.spinner_haspital);
        Context context = getContext();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);  //스피너에 어댑터 설정하기


        /*EditText textView2 = viewGroup.findViewById(R.id.editTextTextPersonName);
        textView2.setText(arguments);*/

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //스피너 리스너 설정
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(items[position]);
                if(items[position].equals("직접입력")){
                    textView.setEnabled(true);
                }else{
                    textView.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("");
            }
        });

        return viewGroup;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }


}
