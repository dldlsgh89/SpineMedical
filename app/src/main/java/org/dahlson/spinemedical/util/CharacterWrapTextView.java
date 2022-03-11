package org.dahlson.spinemedical.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


//textview 문자개행 방식을 띄어쓰기 기준에서 글자 기준으로 변경
public class CharacterWrapTextView extends TextView {
    public CharacterWrapTextView(Context context) {
        super(context);
    }

    public CharacterWrapTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CharacterWrapTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override public void setText(CharSequence text, BufferType type) {
        super.setText(text.toString().replace(" ", "\u00A0"), type);
    }
}
