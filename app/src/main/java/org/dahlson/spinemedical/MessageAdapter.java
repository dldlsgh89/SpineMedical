package org.dahlson.spinemedical;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.MessageModel;
import org.dahlson.spinemedical.model.SpineDataModel;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private Context context;
    private ViewGroup viewGroup;

    ArrayList<MessageModel> items = new ArrayList<MessageModel>();


    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.spine_data_item, parent, false);
        return new MessageAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        MessageModel item = items.get(position);
        holder.setItem(item);
        Log.d("spinemedical", "position :" + position);
        Log.d("spinemedical", "items.size() :" + items.size());
        /*if(position == items.size()){
            holder.itemView.
        }*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void addItem(MessageModel item){
        items.add(item);
    }

    public void setItems(ArrayList<MessageModel> items){
        this.items = items;
    }

    public MessageModel getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, MessageModel messageModel){
        items.set(position, messageModel);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView fromName;
        TextView content;
        TextView insert_dt;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.message_img);
            fromName = itemView.findViewById(R.id.message_title);
            content = itemView.findViewById(R.id.message_content);
            insert_dt = itemView.findViewById(R.id.message_date);
        }

        public void setItem(MessageModel item){
            imageView.setImageDrawable(Drawable.createFromPath(item.getImg_url()));
            fromName.setText(String.valueOf(item.getFromName()));
            content.setText(String.valueOf(item.getContent()));
            insert_dt.setText(String.valueOf(item.getInsert_dt()));
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
