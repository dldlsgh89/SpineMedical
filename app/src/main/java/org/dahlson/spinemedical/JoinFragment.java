package org.dahlson.spinemedical;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.dahlson.spinemedical.util.NumberUtil;

import java.util.Calendar;
import java.util.Date;

public class JoinFragment extends Fragment implements MainActivity.onKeyBackPressedListener{

    String arguments;
    String[] items = {"선택", "직접입력"};
    ViewGroup viewGroup;
    String strBirthday;

    /**
     * 헤더 inflation
     */
    private void inflateHeader()
    {
        // inflation 대상을 포함시키는 레이아웃
        LinearLayout headerLayout = (LinearLayout)getActivity().findViewById(R.id.header);
        TextView textView = headerLayout.findViewById(R.id.title_text);
        textView.setText("환자회원가입");
    }
    // 각각의 Fragment마다 Instance를 반환해 줄 메소드를 생성합니다.
    public static JoinFragment newInstance() {
        return new JoinFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("spinemedical", "JoinFragment start");
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_join, container, false);

        //Spinner spinner = viewGroup.findViewById(R.id.spinner_hospital);
        Context context = getContext();

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);  //스피너에 어댑터 설정하기
        TextView hospitalName = viewGroup.findViewById(R.id.hospital_name);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //스피너 리스너 설정
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hospitalName.setHint(items[position]);
                if(items[position].equals("직접입력")){
                    hospitalName.setEnabled(true);
                }else{
                    hospitalName.setEnabled(false);
                    hospitalName.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                hospitalName.setText("");
            }
        });

        Spinner spinner2 = viewGroup.findViewById(R.id.spinner_doctor);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);  //스피너에 어댑터 설정하기
        TextView doctorName = viewGroup.findViewById(R.id.doctor_name);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //스피너 리스너 설정
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctorName.setHint(items[position]);
                if(items[position].equals("직접입력")){
                    doctorName.setEnabled(true);
                }else{
                    doctorName.setEnabled(false);
                    hospitalName.setText("");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                doctorName.setText("");
            }
        });*/

        CalendarView birthday = viewGroup.findViewById(R.id.birthday);
        Date date = new Date(birthday.getDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String monthZero = NumberUtil.smallerThanTen(calendar.get(Calendar.MONTH)+1);
        String dayZero =  NumberUtil.smallerThanTen(calendar.get(Calendar.DAY_OF_MONTH));

        strBirthday = String.valueOf(calendar.get(Calendar.YEAR))
                + "-" + monthZero
                + "-" + dayZero;

        Button joinBtn = viewGroup.findViewById(R.id.join_btn);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());

                EditText deviceNum = viewGroup.findViewById(R.id.device_num);
                EditText loginId = viewGroup.findViewById(R.id.login_id);
                EditText loginPw = viewGroup.findViewById(R.id.login_pw);
                EditText pwCheck = viewGroup.findViewById(R.id.pw_check);
                EditText patientName = viewGroup.findViewById(R.id.patient_name);
                /*EditText hospitalName = viewGroup.findViewById(R.id.hospital_name);
                EditText doctorName = viewGroup.findViewById(R.id.doctor_name);*/

                RadioGroup genderRadioBtn = viewGroup.findViewById(R.id.radio_btn_gender);
                int genderNum = genderRadioBtn.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)genderRadioBtn.findViewById(genderNum);
                radioButton.getText();
                //Log.d("spinemedical", "radioButton.getText() : " + radioButton.getText());
                //int idx = radio_btn_gender.indexOfChild(radioButton);

                birthday.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        String monthZero = NumberUtil.smallerThanTen(month+1);
                        String dayZero =  NumberUtil.smallerThanTen(dayOfMonth);
                        strBirthday = String.valueOf(year) + "-" + monthZero + "-" + dayZero;
                        //Log.d("spinemedical", "strbirthday : " + strbirthday);
                    }
                });

                EditText email = viewGroup.findViewById(R.id.email);

                boolean formCheck = true;
                String strDeviceNum = String.valueOf(deviceNum.getText()); //기기번호
                String strLoginId = String.valueOf(loginId.getText()); //로그인 아이디
                String strLoginPw= String.valueOf(loginPw.getText()); //패스워드
                String strPwCheck= String.valueOf(pwCheck.getText()); //패스워드 확인
                String strPatientName= String.valueOf(patientName.getText()); //환자이름
                /*String strHospitalName= String.valueOf(hospitalName.getText()); //병원이름
                String strDoctorName= String.valueOf(doctorName.getText()); //담당의이름*/
                //strBirthday; 생년월일
                String strEmail= String.valueOf(email.getText()); //이메일
                String gender = radioButton.getText().toString(); //성별


                if(strDeviceNum == null || strDeviceNum.equals("")){
                    formCheck = false;
                }
                if(strLoginId == null || strLoginId.equals("")){
                    formCheck = false;
                }
                if(strLoginPw == null || strLoginPw.equals("")){
                    formCheck = false;
                }
                if(strPwCheck == null || strPwCheck.equals("")){
                    formCheck = false;
                }
                if(strPatientName == null || strPatientName.equals("")){
                    formCheck = false;
                }
                /*if(strHospitalName == null || strHospitalName.equals("")){
                    formCheck = false;
                }
                if(strDoctorName == null || strDoctorName.equals("")){
                    formCheck = false;
                }*/
                if(strEmail == null || strEmail.equals("")){
                    formCheck = false;
                }



                //입력폼 체크
                if(formCheck){
                    //action
                    //개별 input 별 유효성검사 추가 필요
                   /* strDeviceNum; //기기번호
                    strLoginId; //로그인 아이디
                    strLoginPw; //패스워드
                    strPwCheck; //패스워드 확인
                    strPatientName; //환자이름
                    strHospitalName; //병원이름
                    strDoctorName; //담당의이름
                    strBirthday; 생년월일
                    strEmail; //이메일
                    gender; //성별*/
                   /* Log.d("spinemedical", "strDeviceNum : " + strDeviceNum);
                    Log.d("spinemedical", "strLoginId : " + strLoginId);
                    Log.d("spinemedical", "strLoginPw : " + strLoginPw);
                    Log.d("spinemedical", "strPwCheck : " + strPwCheck);
                    Log.d("spinemedical", "strPatientName : " + strPatientName);
                    Log.d("spinemedical", "strHospitalName : " + strHospitalName);
                    Log.d("spinemedical", "strDoctorName : " + strDoctorName);
                    Log.d("spinemedical", "strBirthday : " + strBirthday);
                    Log.d("spinemedical", "strEmail : " + strEmail);
                    Log.d("spinemedical", "gender : " + gender);*/
                    //새로운 비밀번호 체크
                    if(!strLoginPw.equals(strPwCheck)){
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_dialog2);
                        TextView message = dialog.findViewById(R.id.message);
                        message.setText("비밀번호가 일치하지 않습니다.");
                        Button okButton = dialog.findViewById(R.id.okButton);
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }else{
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_dialog);
                        TextView message = dialog.findViewById(R.id.message);
                        message.setText("가입하시겠습니까?");
                        Button okButton = dialog.findViewById(R.id.okButton);
                        Button cancelButton = dialog.findViewById(R.id.cancelButton);
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //가입환자 정보 서버로 넘겨주는 로직 필요
                                dialog.setContentView(R.layout.custom_dialog2);
                                TextView message = dialog.findViewById(R.id.message);
                                message.setText("가입되었습니다.");
                                Button okButton = dialog.findViewById(R.id.okButton);
                                okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ((MainActivity)getActivity()).replaceLoginFragment(LoginFragment.newInstance());
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
                    }
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

    /*public void setArguments(String arguments) {
        this.arguments = arguments;
    }*/

    //BackStack 으로 뒤로가기 버튼 누르면 전 화면으로 이동하기 위함
    @Override
    public void onBackKey() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)context).setOnKeyBackPressedListener(this);
    }
}
