package com.cyq.mvshow.utils;

import android.app.Activity;
import android.content.DialogInterface;

import com.cyq.mvshow.MyApplication;
import com.cyq.mvshow.R;
import com.cyq.mvshow.widget.HdAlertDialogBulider;
import com.cyq.mvshow.widget.ProgressDialog;


/**
 * 用于对dialog的操作
 */
public class DialogUtils {
    private static ProgressDialog progressDialog;

    public static void showProgressDialogWithoutRate(Activity activity) {

        if (UIUtils.isForeground(activity, activity.getClass().getName())) {
            dismissProgressDialogWithoutRate();
            progressDialog = new ProgressDialog(activity, false);
            progressDialog.show();
        }

    }

    public static void dismissProgressDialogWithoutRate() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }



    public static void alarmAppExit(Activity activity) {
        DialogInterface.OnClickListener confirmListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MyApplication.getInstance().onTerminate();
            }
        };

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        };

        android.support.v7.app.AlertDialog alertDialog = new HdAlertDialogBulider(activity)
                .setTitle(R.string.kindly_hint)
                .setMessage(R.string.exit_app_hint)
                .setPositiveButton(R.string.confirm, confirmListener)
                .setNegativeButton(R.string.cancel, cancelListener)
                .show();
    }

}
