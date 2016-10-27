package com.cyq.mvshow.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.cyq.mvshow.utils.PhoneUtils;
import com.cyq.mvshow.utils.UIUtils;


public class O2FontTextView extends TextView {
     {
        isInEditMode();
    }
    public O2FontTextView(Context context) {
        super(context);
        if (isInEditMode()){
            return;
        }
        if (PhoneUtils.isZh()) {
            setTypeface(UIUtils.getO2Typeface());
        } else {
            setTypeface(UIUtils.getO2BabelSansBoldTypeface());
        }
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    public O2FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()){
            return;
        }
        if (PhoneUtils.isZh()) {
            setTypeface(UIUtils.getO2Typeface());
        } else {
            setTypeface(UIUtils.getO2BabelSansBoldTypeface());
        }
    }
}
