package org.dahlson.spinemedical;

import android.Manifest;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import org.dahlson.spinemedical.util.NumberUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class PairingFragment extends Fragment implements MainActivity.onKeyBackPressedListener{

    ViewGroup viewGroup;
    ListView listView;

    //블루투스 관련 코드
    BluetoothAdapter btAdapter;
    private final static int REQUEST_ENABLE_BT = 1;
    Set<BluetoothDevice> pairedDevices;
    ArrayAdapter<String> btArrayAdapter;
    ArrayList<String> deviceAddressArray;

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
    public static PairingFragment newInstance() {
        return new PairingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("spinemedical", "JoinFragment start");
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_join, container, false);

        //Spinner spinner = viewGroup.findViewById(R.id.spinner_hospital);
        Context context = getContext();

        listView = (ListView) viewGroup.findViewById(R.id.listview);

        //블루투스 관련 코드
        // Get permission
        String[] permission_list = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions(getActivity(), permission_list,  1);

        // Enable bluetooth
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!btAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // show paired devices
        btArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
        deviceAddressArray = new ArrayList<>();

        btArrayAdapter.clear();
        if(deviceAddressArray!=null && !deviceAddressArray.isEmpty()){ deviceAddressArray.clear(); }
        pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                btArrayAdapter.add(deviceName);
                deviceAddressArray.add(deviceHardwareAddress);
            }
        }

        listView.setAdapter(btArrayAdapter);


        return viewGroup;
    }


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
