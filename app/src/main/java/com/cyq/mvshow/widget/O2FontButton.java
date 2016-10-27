package com.cyq.mvshow.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.cyq.mvshow.utils.PhoneUtils;
import com.cyq.mvshow.utils.UIUtils;


public class O2FontButton extends Button {

    public O2FontButton(Context context, AttributeSet attrs) {
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
