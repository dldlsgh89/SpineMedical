package org.dahlson.spinemedical;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


//AsyncTask는 thread를 사용할 일이 있을때 편리하게 이용할 수 있도록 안드로이드에서 지원하고 있는 클래스임
//해당 코드는 AsyncTask를 이용해서 ProgressDialog를 테스트하기 위해 커스텀한 것일뿐 엄밀히 말해서 ProgressDialog의 코드는 아님
public class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog asyncDialog;
        private Dialog endDialog;
        
        public CheckTypesTask(Context context){
            this.asyncDialog = new ProgressDialog(context);
            this.endDialog = new Dialog(context);
        }

        public void progressRunning(){
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("데이터 전송중입니다...");
            // show dialog
            asyncDialog.show();
        }

        public void endDialog(){
            endDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            endDialog.setContentView(R.layout.custom_dialog2);
            TextView textView = endDialog.findViewById(R.id.message);
            textView.setText("데이터 전송을 완료하였습니다.");
            Button okButton = endDialog.findViewById(R.id.okButton);

            okButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    endDialog.dismiss();
                }
            });

            endDialog.show();
        }

        //작업시작(ProgressDialog 객체를 생성하고 시작)
        @Override
        protected void onPreExecute() {
            progressRunning();
            super.onPreExecute();
        }


        //진행중(ProgressDialog 의 진행 정보를 표현)
        @Override
        protected Void doInBackground(Void... arg0) {
                try {
                    //쓰레드 종료 시점을 정할 수 있음.
                    for (int i = 0; i < 10; i++) {
                        //asyncDialog.setProgress(i * 30);
                        //쓰레드 일시중지 메서드
                        Thread.sleep(500);
                    }
                    //쓰레드 종료 메서드
                    Thread.interrupted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
        }

        //종료(ProgressDialog 종료 기능을 구현)
        @Override
        protected void onPostExecute(Void result) {
                asyncDialog.dismiss();
                endDialog();
                super.onPostExecute(result);
        }
}
