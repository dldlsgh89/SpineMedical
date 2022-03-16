package org.dahlson.spinemedical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class NewMessageActivity extends BaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onBackPressed() {
        Log.d("spinemedical","MessageViewActivity onBackPressed start");
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
        //MainActivity를 종료(메모리에서 제거)
        finish();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_message_view;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.message_tab;
    }
}
