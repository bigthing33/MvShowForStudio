package com.cyq.mvshow.widget;

import android.app.Dialog;
import android.content.Context;

import com.cyq.mvshow.R;


public class ProgressDialog extends Dialog {

    public ProgressDialog(Context context, boolean isTouchOutsideTouchOutsideCancelable) {
        super(context, R.style.theme_dialog_alert);

        setContentView(R.layout.window_layout);
        setCanceledOnTouchOutside(isTouchOutsideTouchOutsideCancelable);
        setCancelable(true);
    }

}

