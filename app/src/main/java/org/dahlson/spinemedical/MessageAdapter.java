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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> implements OnMessageItemClickListener{

    private Context context;
    private ViewGroup viewGroup;
    int viewType;
    OnMessageItemClickListener listener;
    ArrayList<MessageModel> items = new ArrayList<MessageModel>();

    //onCreateViewHolder 호출전 호출하여 viewType 리턴
    @Override
    public int getItemViewType(int position){
        return items.get(position).getViewType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MessageAdapter.ViewHolder newViewHolder;
        Log.d("spinemedical", "onCreateViewHolder - viewType :" + viewType);

        View itemView;
        if(viewType == 5){
            itemView = inflater.inflate(R.layout.message_item, parent, false);
        }else if(viewType == 6){
            itemView = inflater.inflate(R.layout.new_message_item, parent, false);
        }else{
            itemView = null;
        }


        //newViewHolder = new MessageReceiveAdapter.ViewHolder(itemView, trueFalse);
        newViewHolder = new MessageAdapter.ViewHolder(itemView,this, viewType);

        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageModel item = items.get(position);
        holder.setItem(item);
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

    //각 아이템을 선택했을때 이벤트를 처리하기 위한 메서드
    public void setOnItemClickListener(OnMessageItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView fromName;
        TextView content;
        TextView insert_dt;
        int viewType;

        TextView hospitalName;
        TextView doctorName;


        public ViewHolder(View itemView, final OnMessageItemClickListener listener, int viewType){
            super(itemView);
            this.viewType = viewType;
            imageView = itemView.findViewById(R.id.message_img);
            if(viewType == 5){
                fromName = itemView.findViewById(R.id.message_title);
                content = itemView.findViewById(R.id.message_content);
                insert_dt = itemView.findViewById(R.id.message_date);
            }else{
                hospitalName = itemView.findViewById(R.id.doctor_name);
                doctorName = itemView.findViewById(R.id.hospital_name);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }
        public void setItem(MessageModel item){
            Log.d("spinemedical", "setItem - start");
            if(viewType == 5){
                fromName.setText(item.getFromName().toString());
                content.setText(String.valueOf(item.getContent()));
                insert_dt.setText(String.valueOf(item.getInsertDt()));
            }else{
                doctorName.setText(String.valueOf(item.getDoctorName()));
                hospitalName.setText(String.valueOf(item.getHospitalName()));
            }
            //imageView.setImageURI(Uri.parse(item.getImg_url()));
            new DownloadFilesTask(imageView, item.getImgUrl()).execute();
            //imageView.setImageResource(item.getImg_url());
        }

    }

   /* public class ItemView extends LinearLayout {
        public ItemView(Context context, @LayoutRes int resource){
            super(context);

            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(resource, this, true);
        }
    }*/


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
    /*주의사항
    1.  인터넷 관련 작업을 할 때는 인터넷 권한을 허용해줘야합니다.
    <uses-permission android:name="android.permission.INTERNET" />
    2. 네트워크 작업을 할때는 Thread나 AsyncTask로 처리해야합니다.
    3. Url 이미지를 로드하는 Thread가 작업 할 때(doInBackground()doInBackground(String... strings))
    UI관련 기능을 가지고 있는 메인 Thread는 대기를 하고,
    완료가 되었을 때(onPostExecuteonPostExecute(Bitmap result))에서 처리해줘야합니다.*/
}
