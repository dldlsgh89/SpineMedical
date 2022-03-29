package org.dahlson.spinemedical;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.DoctorModel;
import org.dahlson.spinemedical.model.SpineDataModel;

import java.util.ArrayList;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> implements OnDoctorItemClickListener{

    private Context context;
    private ViewGroup viewGroup;
    OnDoctorItemClickListener listener;
    ArrayList<DoctorModel> items = new ArrayList<DoctorModel>();

    public DoctorListAdapter(Context context, ViewGroup viewGroup) {
        this.context = context;
        this.viewGroup = viewGroup;
    }

    @NonNull
    @Override
    public DoctorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*ItemView view = null;
        view = new ItemView(parent.getContext(), R.layout.spine_data_item);
        ViewHolder itemView = new ViewHolder(view);*/

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.doctor_list_item, parent, false);
        return new DoctorListAdapter.ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorListAdapter.ViewHolder holder, int position) {
        DoctorModel item = items.get(position);
        holder.setItem(item);
        Log.d("onBindViewHolder", "position :" + position);
        Log.d("onBindViewHolder", "items.size() :" + items.size());
        /*if(position == items.size()){
            holder.itemView.
        }*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void addItem(DoctorModel item){
        items.add(item);
    }

    public void setItems(ArrayList<DoctorModel> items){
        this.items = items;
    }

    public DoctorModel getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, DoctorModel doctorModel){
        items.set(position, doctorModel);
    }

    //각 아이템을 선택했을때 이벤트를 처리하기 위한 메서드
    public void setOnItemClickListener(OnDoctorItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, TextView view) {
        if(listener != null){
            listener.onItemClick(holder, view);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;

        public ViewHolder(View itemView, final OnDoctorItemClickListener listener){
            super(itemView);
            textView1 = itemView.findViewById(R.id.doctor_name);
            textView2 = itemView.findViewById(R.id.hospital_name);
            textView3 = itemView.findViewById(R.id.id);
            textView4 = itemView.findViewById(R.id.choise_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, textView4);
                    }
                }
            });
        }

        public void setItem(DoctorModel item){
            textView1.setText(String.valueOf(item.getDoctorName()));
            textView2.setText(String.valueOf(item.getHospitalName()));
            textView3.setText(String.valueOf(item.getUserId()));
            textView4.setText("연결요청");
        }

    }

    public class ItemView extends LinearLayout {
        public ItemView(Context context, @LayoutRes int resource){
            super(context);

            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(resource, this, true);
        }
    }
}
