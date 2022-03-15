package org.dahlson.spinemedical;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.dahlson.spinemedical.util.NumberUtil;

import java.util.Calendar;
import java.util.List;

public class PatientFragment extends Fragment implements MoreActivity.onKeyBackPressedListener {

    ViewGroup viewGroup;
    ArrayAdapter<String> adapter;
    private OnBackPressedCallback callback;

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
        Log.d("spinemedical","PatientFragment start");

        //화면에 들어왔을때 유저의 기본정보를 받아오는 로직 필요
        String gender; //성별
        String birthday; //생년월일
        String patientName; //환자명
        String hospitalName; //진료병원
        String doctorName; //담당의
        String email; //이메일
        String medicalNum; //진료번호
        String group; //분류
        String type; //유형
        String lumbarAngle; //요추각도 입력
        String chestAngle; //흉추각도 입력
        String stanTempe; //온도기준 입력
        String stanPree; //압력기준 입력
        String stagel; //단계
        RadioGroup genderRadioGroup = viewGroup.findViewById(R.id.gender);
        TextView birthText = viewGroup.findViewById(R.id.birth_text);
        EditText patientNameText = viewGroup.findViewById(R.id.patient_name);
        EditText hospitalNameText = viewGroup.findViewById(R.id.hospital_name);
        EditText doctorNameText = viewGroup.findViewById(R.id.doctor_name);
        EditText emailText = viewGroup.findViewById(R.id.email);
        Spinner spinnerGroup = viewGroup.findViewById(R.id.spinner_group);
        Spinner spinnerType = viewGroup.findViewById(R.id.spinner_type);
        EditText lumbarAngleText = viewGroup.findViewById(R.id.lumbar_angle);
        EditText chestAngleText = viewGroup.findViewById(R.id.chest_angle);
        EditText stanTempeText = viewGroup.findViewById(R.id.stan_tempe);
        EditText stanPressText = viewGroup.findViewById(R.id.stan_press);
        Spinner spinnerStage = viewGroup.findViewById(R.id.spinner_stage);


        //생년월일 선택
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            //달력 선택시 textview에 셋팅
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String select_birth = String.valueOf(year) + "-" + NumberUtil.smallerThanTen(month+1) + "-" + NumberUtil.smallerThanTen(dayOfMonth);
                birthText.setText(select_birth);
            }
        };
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(context, listener, year, month, day);
        LinearLayout birthBtn = viewGroup.findViewById(R.id.birth_btn);
        birthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        //spinner setting
        //병원명 선택
        Spinner spinnerHospital = viewGroup.findViewById(R.id.spinner_hospital);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, hospital_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHospital.setAdapter(adapter);  //스피너에 어댑터 설정하기
        //직접입력 선택시
        spinnerHospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //스피너 리스너 설정
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hospitalNameText.setHint(items[position]);
                if(items[position].equals("직접입력")){
                    hospitalNameText.setEnabled(true);
                }else{
                    hospitalNameText.setEnabled(false);
                    hospitalNameText.setText("");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                hospitalNameText.setText("");
            }
        });

        //담당의명
        Spinner spinnerDoctor = viewGroup.findViewById(R.id.spinner_doctor);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, doctor_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctor.setAdapter(adapter);  //스피너에 어댑터 설정하기
        //직접입력 선택시
        spinnerDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //스피너 리스너 설정
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctorNameText.setHint(items[position]);
                if(items[position].equals("직접입력")){
                    doctorNameText.setEnabled(true);
                }else{
                    doctorNameText.setEnabled(false);
                    doctorNameText.setText("");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                doctorNameText.setText("");
            }
        });

        //분류
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, group_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(adapter);  //스피너에 어댑터 설정하기

        //유형
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, type_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);  //스피너에 어댑터 설정하기

        //단계
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, stage_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStage.setAdapter(adapter);  //스피너에 어댑터 설정하기

        //수정 버튼 클릭시
        Button changeBtn = viewGroup.findViewById(R.id.change_btn);
        changeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int genderNum = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)genderRadioGroup.findViewById(genderNum);
                String strGender = String.valueOf(radioButton.getText());
                String strBirth = String.valueOf(birthText.getText());
                String strPatientName = String.valueOf(patientNameText.getText());
                String strHospitalName = String.valueOf(hospitalNameText.getText());
                String strDoctorName = String.valueOf(doctorNameText.getText());
                String strEmail = String.valueOf(emailText.getText());
                String strGroup = spinnerGroup.getSelectedItem().toString();
                String strType = spinnerType.getSelectedItem().toString();
                String strLumbarAngle = String.valueOf(lumbarAngleText.getText());
                String strChestAngle =  String.valueOf(chestAngleText.getText());
                String strStanTempe = String.valueOf(stanTempeText.getText());
                String strStanPress = String.valueOf(stanPressText.getText());
                String strStage = spinnerStage.getSelectedItem().toString();

                boolean formCheck = true;
                if(strGender == null || strGender.equals("")){
                    formCheck = false;
                }
                if(strBirth == null || strBirth.equals("")){
                    formCheck = false;
                }
                if(strPatientName == null || strPatientName.equals("")){
                    formCheck = false;
                }
                if(strHospitalName == null || strHospitalName.equals("")){
                    formCheck = false;
                }
                if(strDoctorName == null || strDoctorName.equals("")){
                    formCheck = false;
                }
                if(strEmail == null || strEmail.equals("")){
                    formCheck = false;
                }
                if(strGroup == null || strGroup.equals("") || strGroup.equals("선택")){
                    formCheck = false;
                }
                if(strType == null || strType.equals("") || strType.equals("선택")){
                    formCheck = false;
                }
                if(strLumbarAngle == null || strLumbarAngle.equals("")){
                    formCheck = false;
                }
                if(strChestAngle == null || strChestAngle.equals("")){
                    formCheck = false;
                }
                if(strStanTempe == null || strStanTempe.equals("")){
                    formCheck = false;
                }
                if(strStanPress == null || strStanPress.equals("")){
                    formCheck = false;
                }
                if(strStage == null || strStage.equals("") || strStage.equals("선택")){
                    formCheck = false;
                }


                Log.d("spinemedical","strGender : " + strGender);
                Log.d("spinemedical","strBirth : " + strBirth);
                Log.d("spinemedical","strPatientName : " + strPatientName);
                Log.d("spinemedical","strHospitalName : " + strHospitalName);
                Log.d("spinemedical","strDoctorName : " + strDoctorName);
                Log.d("spinemedical","strEmail : " + strEmail);
                Log.d("spinemedical","strGroup : " + strGroup);
                Log.d("spinemedical","strType : " + strType);
                Log.d("spinemedical","strLumbarAngle : " + strLumbarAngle);
                Log.d("spinemedical","strChestAngle : " + strChestAngle);
                Log.d("spinemedical","strStanTempe : " + strStanTempe);
                Log.d("spinemedical","strStanPress : " + strStanPress);
                Log.d("spinemedical","strStage : " + strStage);
                Dialog dialog = new Dialog(getActivity());
                //입력폼 체크
                if(formCheck){
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_dialog);
                    TextView message = dialog.findViewById(R.id.message);
                    message.setText("환자정보를 수정하시겠습니까?");
                    Button okButton = dialog.findViewById(R.id.okButton);
                    Button cancelButton = dialog.findViewById(R.id.cancelButton);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //action
                            //수정된 환자 정보 서버로 넘겨주는 로직 필요
                            dialog.setContentView(R.layout.custom_dialog2);
                            TextView message = dialog.findViewById(R.id.message);
                            message.setText("수정되었습니다.");
                            Button okButton = dialog.findViewById(R.id.okButton);
                            okButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ((MoreActivity)getActivity()).moreMainFragment(MoreMainFragment.newInstance(), "");
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }else{
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_dialog2);
                    TextView message = dialog.findViewById(R.id.message);
                    message.setText("입력되지 않은 정보가 있습니다.");
                    Button okButton = dialog.findViewById(R.id.okButton);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
                dialog.show();
            }
        });

        return viewGroup;
    }

    //BackStack 으로 뒤로가기 버튼 누르면 전 화면으로 이동하기 위함
    @Override
    public void onBackKey() {
        Log.d("spinemedical","PatientFragment onBackKey start");
        MoreActivity activity = (MoreActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        //getActivity().getSupportFragmentManager().beginTransaction().remove(patientFragment).commit();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("spinemedical","PatientFragment onAttach start");
        super.onAttach(context);
        ((MoreActivity)context).setOnKeyBackPressedListener(this);
    }
}
