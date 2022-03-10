package org.dahlson.spinemedical;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.SpineDataModel;
import org.dahlson.spinemedical.util.RecyclerDecoration;

public class SpineHomeContent extends Fragment {

    ViewGroup viewGroup;
    Context context;
    String[] items = {"전체", "착용", "미착용"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home_content, container, false);

        context = getContext();

        Spinner wear_state = viewGroup.findViewById(R.id.wear_state);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wear_state.setAdapter(adapter);  //스피너에 어댑터 설정하기

        return viewGroup;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*TableLayout tableLayout = viewGroup.findViewById(R.id.spine_data);
        //tableLayout.addView(spineDataAdapter);*/

        RecyclerView recyclerView = viewGroup.findViewById(R.id.spine_data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        SpineDataAdapter spineDataAdapter = new SpineDataAdapter(context, viewGroup);
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-01 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-02 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-03 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-01 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-02 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-03 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-01 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-02 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-03 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-01 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-02 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-03 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-01 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-02 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-03 17:02:12"));

        //apapter 수정 및 변경시 개별 아이템의 인덱스를 가지고 변경한뒤에 변경 사항을 반영하기 위해 changed 메서드를 실행함
        /*
        spineDataAdapter.getItem(0);
        spineDataAdapter.notifyItemChanged(0);
        spineDataAdapter.notifyItemInserted(0);
        spineDataAdapter.notifyItemRemoved(0);
        spineDataAdapter.notifyItemMoved(0,1);*/
        //전체수정
        /*spineDataAdapter.notifyDataSetChanged();*/

        recyclerView.setAdapter(spineDataAdapter);

        // 아이템간 공백 추가
        RecyclerDecoration spaceDecoration = new RecyclerDecoration("h",-10,0);
        recyclerView.addItemDecoration(spaceDecoration);

    }

}
