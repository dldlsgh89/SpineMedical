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
    //Boolean trueFalse = true;
    ArrayList<MessageModel> items = new ArrayList<MessageModel>();

    //onCreateViewHolder 호출전 호출하여 viewType 리턴
    @Override
    public int getItemViewType(int position){
        return items.get(position).getViewType();
    }

    //뷰홀더 생성해주면서 뷰홀더에 담길 화면(item_view) 객체에 연결 - 보통 13~15번만 호출됨.
    @NonNull
    @Override
    public MessageReceiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MessageReceiveAdapter.ViewHolder newViewHolder;
        Log.d("spinemedical", "onCreateViewHolder - viewType :" + viewType);

        View itemView;
        if(viewType == 1){
            itemView = inflater.inflate(R.layout.receive_message1_item, parent, false);
        }else if(viewType == 2){
            itemView = inflater.inflate(R.layout.receive_message2_item, parent, false);
        }else if(viewType == 3){
            itemView = inflater.inflate(R.layout.send_message1_item, parent, false);
        }else if(viewType == 4){
            itemView = inflater.inflate(R.layout.send_message2_item, parent, false);
        }else{
            itemView = null;
        }


        //newViewHolder = new MessageReceiveAdapter.ViewHolder(itemView, trueFalse);
        newViewHolder = new MessageReceiveAdapter.ViewHolder(itemView, viewType);


        return newViewHolder;
    }

    //생성된 뷰홀더에 데이터를 바인딩해주는 함수
    @Override
    public void onBindViewHolder(@NonNull MessageReceiveAdapter.ViewHolder holder, int position) {

        /*int layoutParamsWidth = holder.testLinear.getLayoutParams().width;
        Log.d("spinemedical", "MessageViewAdapter - layoutParams.width : " + layoutParamsWidth);*/
        /*Log.d("spinemedical", "MessageViewAdapter - newViewHolder.testLinear.getDisplay():" + newViewHolder.testLinear.getDisplay());
        Log.d("spinemedical", "MessageViewAdapter - newViewHolder.testLinear.getLayoutParams().width:" + newViewHolder.testLinear.getLayoutParams().width);*/
       /* layoutParams.height = layoutParams.width;
        holder.itemView.requestLayout();*/

        MessageModel item = items.get(position);
        holder.setItem(item);
        //holder.setItem(item, trueFalse);
        Log.d("spinemedical", "position :" + position);
        Log.d("spinemedical", "items.size() :" + items.size());
        /*if(position == items.size()){
            holder.itemView.
        }*/
        //trueFalse = false;
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


    //화면에 나타날 아이템 객체를 기억하고 있는 홀딩 객체. 해당 홀딩 객체의 아이템별 값만 계속 변경해주면서 아이템을 출력하는게 recyclerView의 원리
    static class ViewHolder extends RecyclerView.ViewHolder{
        //LinearLayout testLinear;
        ImageView imageView;
        TextView fromName;
        TextView content;
        TextView insert_dt;
        int viewType;

        //public ViewHolder(@NonNull View itemView, boolean check) {
        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            //testLinear = itemView.findViewById(R.id.testLinear);
            //if(check){
                this.viewType = viewType;
                imageView = itemView.findViewById(R.id.message_img);
                fromName = itemView.findViewById(R.id.message_name);
                content = itemView.findViewById(R.id.message_content);
                insert_dt = itemView.findViewById(R.id.message_date);
           /* }else{
                content = itemView.findViewById(R.id.message_content);
                insert_dt = itemView.findViewById(R.id.message_date);
            }*/
        }

        //public void setItem(MessageModel item , boolean check){
        public void setItem(MessageModel item){
            //if(check){
                Log.d("spinemedical", "item.getFromName() : " + item.getFromName());
                fromName.setText(item.getFromName().toString());
                content.setText(String.valueOf(item.getContent()));
                insert_dt.setText(String.valueOf(item.getInsertDt()));
                Log.d("spinemedical", "item.getImg_url() :" + item.getImgUrl());
                //imageView.setImageURI(Uri.parse(item.getImg_url()));
                new MessageReceiveAdapter.DownloadFilesTask(imageView, item.getImgUrl()).execute();
                //imageView.setImageResource(item.getImg_url());
            /*}else{
                content.setText(String.valueOf(item.getContent()));
                insert_dt.setText(String.valueOf(item.getInsert_dt()));
            }*/
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
