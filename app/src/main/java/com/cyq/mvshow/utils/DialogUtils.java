package com.cyq.mvshow.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.cyq.mvshow.MyApplication;
import com.cyq.mvshow.R;
import com.cyq.mvshow.server.UpdateWorker;
import com.cyq.mvshow.widget.HdAlertDialogBulider;
import com.cyq.mvshow.widget.ProgressDialog;


/**
 * 用于对dialog的操作
 */
public class DialogUtils {

    private static AlertDialog alertDialog;

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

    /**
     * 弹出提示框，提示用户更新app,打开网址连接
     */
    public static void showUpdateDialog(final Activity activity, String versionName) {

        showAlertDialog(activity, activity.getString(R.string.hint), activity.getString(R.string.update_hint) + " " + versionName, 0, new DialogListener() {
            @Override
            public void onPositive() {
                //隐式打开浏览器
                Uri uri = Uri.parse(UpdateWorker.DOWNlOAD_URL);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
                //使用手机自带的浏览器
//                            Intent intent = new Intent();
//                            intent.setAction("android.intent.action.VIEW");
//                            Uri content_url = Uri.parse(checkAppVersionRespone.latestapp.applink);
//                            intent.setData(content_url);
//                            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
//                            startActivity(intent);
            }

            @Override
            public void onNegative() {

            }
        });
    }

    public static void showAlertDialog(Activity activity, String title, String contentText, int icon, final DialogListener dialogListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);  //先得到构造器
        builder.setTitle(title); //设置标题
        if (contentText != null) {
            builder.setMessage(contentText); //设置内容
        }
        if (icon != 0) {
            builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        }
        builder.setPositiveButton(activity.getString(R.string.confirm), new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                dialogListener.onPositive();
            }
        });
        builder.setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialogListener.onNegative();
            }
        });
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    public interface DialogListener {
        public void onPositive();

        public void onNegative();
    }

}
