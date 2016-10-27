package com.cyq.mvshow.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.cyq.mvshow.R;
import com.cyq.mvshow.utils.UIUtils;


/**
 * Created by win7 on 2015/12/9.
 */
public class HdAlertDialogBulider extends AlertDialog.Builder {

    public HdAlertDialogBulider(Context context) {
        super(context);
    }

    public HdAlertDialogBulider(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public AlertDialog show() {
        AlertDialog alertDialog = super.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getContext().getResources().getColor(R.color.yellow_special));
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getContext().getResources().getColor(R.color.yellow_special));
        alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(getContext().getResources().getColor(R.color.yellow_special));
        return alertDialog;
    }

    public static void HandleDialogShow(Context context, AlertDialog alertDialog) {
        Typeface typeFace = UIUtils.getO2Typeface();
        alertDialog.show();
        Button positive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positive.setTextColor(context.getResources().getColor(R.color.yellow_special));
        positive.setTypeface(typeFace);
        positive.setTextSize(context.getResources().getDimension(R.dimen.base_textsize_small));
        Button negative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        negative.setTextColor(context.getResources().getColor(R.color.yellow_special));
        negative.setTypeface(typeFace);
        negative.setTextSize(context.getResources().getDimension(R.dimen.base_textsize_small));
        Button neutral = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        neutral.setTextColor(context.getResources().getColor(R.color.yellow_special));
        neutral.setTypeface(typeFace);
        neutral.setTextSize(context.getResources().getDimension(R.dimen.base_textsize_small));
    }
}
