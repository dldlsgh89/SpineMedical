package org.dahlson.spinemedical;

public class MessageAdapterRollback{
//public class MessageAdapterRollback extends RecyclerView.Adapter<MessageAdapterRollback.ViewHolder> implements OnMessageItemClickListener{

    /*private Context context;
    private ViewGroup viewGroup;
    int viewType;
    OnMessageItemClickListener listener;
    ArrayList<MessageModel> items = new ArrayList<MessageModel>();
    ArrayList<DocterModel> items2 = new ArrayList<DocterModel>();

    //onCreateViewHolder 호출전 호출하여 viewType 리턴
    @Override
    public int getItemViewType(int position){
        return items.get(position).getViewType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MessageAdapterRollback.ViewHolder newViewHolder;
        View itemView;
        this.viewType = viewType;
        if(viewType == 1){
            Log.d("spinemedical", "onCreateViewHolder :" + viewType);
            itemView = inflater.inflate(R.layout.message_item, parent, false);
        }else if(viewType == 2){
            Log.d("spinemedical", "onCreateViewHolder :" + viewType);
            itemView = inflater.inflate(R.layout.new_message_item, parent, false);
        }else{
            itemView = null;
        }

        return new ViewHolder(itemView, this, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object item;
        if(viewType == 1){
            Log.d("spinemedical", "onBindViewHolder :" + viewType);
            item = items.get(position);
        }else if(viewType ==2){
            Log.d("spinemedical", "onBindViewHolder :" + viewType);
            item = items2.get(position);
        }else{
            item = null;
        }
        //MessageModel item = items.get(position);
     *//*   if(viewType == 1){
            holder.setItem(item);
        }else if(viewType == 2){*//*
            holder.setItem(item, viewType);
        //}

    }

    @Override
    public int getItemCount() {
        int returnSize = 0;
        if(viewType == 1){
            Log.d("spinemedical", "getItemCount :" + viewType);
            returnSize = items.size();
        }else if(viewType ==2) {
            Log.d("spinemedical", "getItemCount :" + viewType);
            returnSize =  items2.size();
        }
        return returnSize;
    }


    public void addItem(MessageModel item, int viewType){
        Log.d("spinemedical", "addItem :" + viewType);
        items.add(item);
    }

    public void addItem(DocterModel item){
        Log.d("spinemedical", "addItem :" + viewType);
        items2.add(item);
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

        TextView doctorName;
        TextView hospitalName;
        //int viewType;

        public ViewHolder(View itemView, final OnMessageItemClickListener listener, int viewType){
            super(itemView);
            //this.viewType = viewType;
            if(viewType == 1){
                Log.d("spinemedical", "ViewHolder :" + viewType);
                imageView = itemView.findViewById(R.id.message_img);
                fromName = itemView.findViewById(R.id.message_title);
                content = itemView.findViewById(R.id.message_content);
                insert_dt = itemView.findViewById(R.id.message_date);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        if(listener != null){
                            listener.onItemClick(ViewHolder.this, view, position);
                        }
                    }
                });
            }else if(viewType == 2){
                Log.d("spinemedical", "ViewHolder :" + viewType);
                imageView = itemView.findViewById(R.id.message_img);
                doctorName = itemView.findViewById(R.id.doctor_name);
                hospitalName = itemView.findViewById(R.id.hospital_name);
            }
        }

        public void setItem(Object items, int viewType){
        //public void setItem(MessageModel item){
            if(viewType == 1){
                Log.d("spinemedical", "ViewHolder :" + viewType);
                MessageModel item = (MessageModel) items;
                fromName.setText(item.getFromName().toString());
                content.setText(String.valueOf(item.getContent()));
                insert_dt.setText(String.valueOf(item.getInsertDt()));
                new DownloadFilesTask(imageView, item.getImgUrl()).execute();
            }else if(viewType == 2){
                Log.d("spinemedical", "ViewHolder :" + viewType);
                DocterModel item = (DocterModel) items;
                doctorName.setText(String.valueOf(item.getDoctorName()));
                hospitalName.setText(String.valueOf(item.getHospitalName()));
                new DownloadFilesTask(imageView, item.getImgUrl()).execute();
            }
        }

    }

   *//* public class ItemView extends LinearLayout {
        public ItemView(Context context, @LayoutRes int resource){
            super(context);

            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(resource, this, true);
        }
    }*//*


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
            *//*Bitmap bmp = null;
            try {
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;*//*

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

    */
    /*주의사항
    1.  인터넷 관련 작업을 할 때는 인터넷 권한을 허용해줘야합니다.
    <uses-permission android:name="android.permission.INTERNET" />
    2. 네트워크 작업을 할때는 Thread나 AsyncTask로 처리해야합니다.
    3. Url 이미지를 로드하는 Thread가 작업 할 때(doInBackground()doInBackground(String... strings))
    UI관련 기능을 가지고 있는 메인 Thread는 대기를 하고,
    완료가 되었을 때(onPostExecuteonPostExecute(Bitmap result))에서 처리해줘야합니다.*/
}
