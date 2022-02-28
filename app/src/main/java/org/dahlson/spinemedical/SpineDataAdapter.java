package org.dahlson.spinemedical;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.SpineDataModel;

import java.util.ArrayList;

public class SpineDataAdapter extends RecyclerView.Adapter<SpineDataAdapter.ViewHolder> {

    private Context context;
    private ViewGroup viewGroup;

    ArrayList<SpineDataModel> items = new ArrayList<SpineDataModel>();

    public SpineDataAdapter(Context context, ViewGroup viewGroup) {
        this.context = context;
        this.viewGroup = viewGroup;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*ItemView view = null;
        view = new ItemView(parent.getContext(), R.layout.spine_data_item);
        ViewHolder itemView = new ViewHolder(view);*/

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.spine_data_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpineDataModel item = items.get(position);
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


    public void addItem(SpineDataModel item){
        items.add(item);
    }

    public void setItems(ArrayList<SpineDataModel> items){
        this.items = items;
    }

    public SpineDataModel getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, SpineDataModel spineDataModel){
        items.set(position, spineDataModel);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;

        public ViewHolder(View itemView){
            super(itemView);

            textView1 = itemView.findViewById(R.id.idex);
            textView2 = itemView.findViewById(R.id.temperature);
            textView3 = itemView.findViewById(R.id.pressure);
            textView4 = itemView.findViewById(R.id.wear);
            textView5 = itemView.findViewById(R.id.date_dt);
            textView6 = itemView.findViewById(R.id.time_dt);
        }

        public void setItem(SpineDataModel item){
            textView1.setText(String.valueOf(item.getIdex()));
            textView2.setText(String.valueOf(item.getTemperature()));
            textView3.setText(String.valueOf(item.getPressure()));
            textView4.setText(String.valueOf(item.getWear()));
            String[] date_array = item.getInsert_dt().split(" ");
            textView5.setText(date_array[0]);
            textView6.setText(date_array[1]);
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
