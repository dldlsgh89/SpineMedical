package org.dahlson.spinemedical.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerDecoration extends RecyclerView.ItemDecoration {

    private final int divHeight;
    private final int divWidth;
    private final String type;

    public RecyclerDecoration(String type, int divHeight, int divWidth) {
        this.type = type;
        if(type.equals("h")){
            this.divHeight = divHeight;
            this.divWidth = 0;
        }else if(type.equals("w")){
            this.divHeight = 0;
            this.divWidth = divWidth;
        }else{
            this.divHeight = divHeight;
            this.divWidth = divWidth;
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)

            if(type.equals("h")){
                outRect.bottom = divHeight;
            }else if(type.equals("w")){
                outRect.right = divWidth;
            }else{
                outRect.right = divWidth;
                outRect.right = divHeight;
            }
    }
}