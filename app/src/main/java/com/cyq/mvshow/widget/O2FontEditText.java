package com.cyq.mvshow.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.cyq.mvshow.utils.PhoneUtils;
import com.cyq.mvshow.utils.UIUtils;


public class O2FontEditText extends EditText {
    public O2FontEditText(Context context) {
        super(context);
        if (!isInEditMode()) {
            if (PhoneUtils.isZh()) {
                setTypeface(UIUtils.getO2Typeface());
            } else {
                setTypeface(UIUtils.getO2BabelSansBoldTypeface());
            }
        }
    }

    public O2FontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            if (PhoneUtils.isZh()) {
                setTypeface(UIUtils.getO2Typeface());
            } else {
                setTypeface(UIUtils.getO2BabelSansBoldTypeface());
            }

        }
    }
}
