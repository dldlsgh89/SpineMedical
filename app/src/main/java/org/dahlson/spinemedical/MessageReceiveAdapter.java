package org.dahlson.spinemedical;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.dahlson.spinemedical.model.MessageModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MessageReceiveAdapter extends RecyclerView.Adapter<MessageReceiveAdapter.ViewHolder> {

    private Context context;
    private ViewGroup viewGroup;
    Boolean trueFalse = true;
    ArrayList<MessageModel> items = new ArrayList<MessageModel>();


    @Override
    public int getItemViewType(int position){
        return items.get(position).getView_type();
    }


    @NonNull
    @Override
    public MessageReceiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MessageReceiveAdapter.ViewHolder newViewHolder;
        View itemView;
        if(trueFalse){
            itemView = inflater.inflate(R.layout.receive_message1_item, parent, false);
        }else{
            itemView = inflater.inflate(R.layout.receive_message2_item, parent, false);
        }

        newViewHolder = new MessageReceiveAdapter.ViewHolder(itemView,trueFalse);

        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageReceiveAdapter.ViewHolder holder, int position) {

        /*int layoutParamsWidth = holder.testLinear.getLayoutParams().width;
        Log.d("spinemedical", "MessageViewAdapter - layoutParams.width : " + layoutParamsWidth);*/
        /*Log.d("spinemedical", "MessageViewAdapter - newViewHolder.testLinear.getDisplay():" + newViewHolder.testLinear.getDisplay());
        Log.d("spinemedical", "MessageViewAdapter - newViewHolder.testLinear.getLayoutParams().width:" + newViewHolder.testLinear.getLayoutParams().width);*/
       /* layoutParams.height = layoutParams.width;
        holder.itemView.requestLayout();*/

        MessageModel item = items.get(position);
        holder.setItem(item, trueFalse);
        Log.d("spinemedical", "position :" + position);
        Log.d("spinemedical", "items.size() :" + items.size());
        /*if(position == items.size()){
            holder.itemView.
        }*/
        trueFalse = false;
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
        //LinearLayout testLinear;
        ImageView imageView;
        TextView fromName;
        TextView content;
        TextView insert_dt;

        public ViewHolder(@NonNull View itemView, boolean check) {
            super(itemView);
            //testLinear = itemView.findViewById(R.id.testLinear);
            if(check){
                imageView = itemView.findViewById(R.id.message_img);
                fromName = itemView.findViewById(R.id.message_name);
                content = itemView.findViewById(R.id.message_content);
                insert_dt = itemView.findViewById(R.id.message_date);
            }else{
                content = itemView.findViewById(R.id.message_content);
                insert_dt = itemView.findViewById(R.id.message_date);
            }
        }

        public void setItem(MessageModel item , boolean check){
            if(check){
                Log.d("spinemedical", "item.getFromName() : " + item.getFromName());
                fromName.setText(item.getFromName().toString());
                content.setText(String.valueOf(item.getContent()));
                insert_dt.setText(String.valueOf(item.getInsert_dt()));
                Log.d("spinemedical", "item.getImg_url() :" + item.getImg_url());
                //imageView.setImageURI(Uri.parse(item.getImg_url()));
                new MessageReceiveAdapter.DownloadFilesTask(imageView, item.getImg_url()).execute();
                //imageView.setImageResource(item.getImg_url());
            }else{
                content.setText(String.valueOf(item.getContent()));
                insert_dt.setText(String.valueOf(item.getInsert_dt()));
            }
        }
    }

    private static class DownloadFilesTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;
        String url;

        protected DownloadFilesTask(ImageView imageView ,String url){
            this.imageView = imageView;
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            //기존
            /*Bitmap bmp = null;
            try {
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;*/

            //수정
            Bitmap bmp = null;
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // doInBackground 에서 받아온 total 값 사용 장소
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }
    }
}
