package org.dahlson.spinemedical;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.SpineDataModel;
import org.dahlson.spinemedical.util.NumberUtil;
import org.dahlson.spinemedical.util.RecyclerDecoration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SpineHomeContent extends Fragment {

    ViewGroup viewGroup;
    Context context;
    String[] items = {"전체", "착용", "미착용"};
    DatePickerDialog endDatePickerDialog = null;
    DatePickerDialog startDatePickerDialog = null;

    TextView startDt;
    TextView endDt;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home_content, container, false);
        context = getContext();
        Log.d("spinemedical","SpineHomeContent onCreateView start");

        Spinner wear_state = viewGroup.findViewById(R.id.wear_state);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wear_state.setAdapter(adapter);  //스피너에 어댑터 설정하기

        //기본 선택 날짜
        Calendar cal = Calendar.getInstance();
        startDt = viewGroup.findViewById(R.id.start_dt);
        endDt = viewGroup.findViewById(R.id.end_dt);
        
        //기본 종료일 - 오늘 
        int startYear = cal.get(Calendar.YEAR);
        int startMonth = cal.get(Calendar.MONTH)+1;
        int startDate = cal.get(Calendar.DATE);

        //기본 종료일 - 오늘부터 3개월 이전
        cal.add(Calendar.MONTH, -3);
        int endYear = cal.get(Calendar.YEAR);
        int endMonth = cal.get(Calendar.MONTH)+1;
        int endDate = cal.get(Calendar.DATE);
        
        endDt.setText(startYear + "-" + NumberUtil.smallerThanTen(startMonth) + "-" + NumberUtil.smallerThanTen(startDate));
        startDt.setText(endYear + "-" + NumberUtil.smallerThanTen(endMonth) + "-" + NumberUtil.smallerThanTen(endDate));

        //조회 시작일
        startDt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //날짜 선택시 textView 입력
                DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                        startDt.setText(String.format("%d-%s-%s", yy, NumberUtil.smallerThanTen(mm+1) ,NumberUtil.smallerThanTen(dd)));
                    }
                };

                //날짜 선택 다이얼로그에 선택된 날짜
                //textview에 선택된 날짜가 있을때 - 해당 날짜 선택되어 오픈
                //선택된 날짜가 없을때 기본 날짜 선택되어 오픈
                if(startDatePickerDialog != null){
                    String strStartDt = String.valueOf(startDt.getText());
                    String[] arrayStartDt = strStartDt.split("-");
                    int startYear = Integer.parseInt(arrayStartDt[0]);
                    int startMonth = Integer.parseInt(arrayStartDt[1]);
                    int startDate = Integer.parseInt(arrayStartDt[2]);
                    startDatePickerDialog = new DatePickerDialog(getActivity(), mDateSetListener, startYear, startMonth-1, startDate);
                }else{
                    startDatePickerDialog =  new DatePickerDialog(getActivity(), mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                }
                //날짜 선택 제한 -- 종료일 이후로 선택 못함
                String strEndDt = String.valueOf(endDt.getText());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                try {
                    date = format.parse(strEndDt);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                startDatePickerDialog.getDatePicker().setMaxDate(date.getTime());

                startDatePickerDialog.show();
            }
        });

        //조회 종료일
        endDt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                        endDt.setText(String.format("%d-%s-%s", yy, NumberUtil.smallerThanTen(mm+1) ,NumberUtil.smallerThanTen(dd)));
                    }
                };
                if(endDatePickerDialog != null){
                    String strEndDt = String.valueOf(endDt.getText());
                    String[] arrayEndDt = strEndDt.split("-");
                    int endYear = Integer.parseInt(arrayEndDt[0]);
                    int endMonth = Integer.parseInt(arrayEndDt[1]);
                    int endDate = Integer.parseInt(arrayEndDt[2]);
                    endDatePickerDialog = new DatePickerDialog(getActivity(), mDateSetListener, endYear, endMonth-1, endDate);
                }else{
                    endDatePickerDialog =  new DatePickerDialog(getActivity(), mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                }
                //날짜 선택 제한 -- 시작일 이전 선택 못함. 오늘 이후 선택 못함
                String strStartDt = String.valueOf(startDt.getText());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                try {
                    date = format.parse(strStartDt);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                endDatePickerDialog.getDatePicker().setMinDate(date.getTime());
                endDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

                endDatePickerDialog.show();
            }
        });
        
        //데이터 조회 시작
        Button button = viewGroup.findViewById(R.id.select_spine_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStartDt = String.valueOf(startDt.getText());  //조회 시작일
                String strEndDt = String.valueOf(endDt.getText());  //조회 종료일
                String strWearStete = wear_state.getSelectedItem().toString(); //착용 유무

                //action
            }
        });


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
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-01 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-01 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 0, "2022-01-02 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-02 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-02 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 0, "2022-01-03 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-03 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-03 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 0, "2022-01-04 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-04 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-04 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-05 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-05 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 0, "2022-01-05 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-06 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-06 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-06 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 0, "2022-01-07 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-07 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-07 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 0, "2022-01-08 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-08 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-08 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 0, "2022-01-09 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-09 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-09 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-10 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-11 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 0, "2022-01-12 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-13 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-13 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-13 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 0, "2022-01-14 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-14 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-14 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 0, "2022-01-15 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-15 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-15 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 0, "2022-01-16 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-16 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 1, "2022-01-16 17:02:12"));
        spineDataAdapter.addItem(new SpineDataModel(0, 20.3, 0.5, 1, "2022-01-17 13:20:32"));
        spineDataAdapter.addItem(new SpineDataModel(0, 21.5, 0.3, 1, "2022-01-17 14:20:00"));
        spineDataAdapter.addItem(new SpineDataModel(0, 24.4, 0.7, 0, "2022-01-17 17:02:12"));

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
