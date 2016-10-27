package com.cyq.mvshow.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.cyq.mvshow.MyApplication;
import com.cyq.mvshow.R;
import com.github.johnpersano.supertoasts.SuperCardToast;
import com.github.johnpersano.supertoasts.SuperToast;

import java.util.List;

public class UIUtils {
    public static final String mWVGA = "800*480";
    public static final String mxxHD = "1920*1080";
    public static final String mxHD = "1280*720";
    private static Typeface mTypeFace;

    public static void toastShort(Activity activity, String info) {
        SuperToast(activity, info, true);
    }

    public static void toastShortFailed(Activity activity, String info) {
        SuperToast(activity, info, true);
    }

    public static void toastShortFailed(Activity activity, int resId) {
        String info = activity.getString(resId);
        toastShortFailed(activity, info);
    }

    public static void toastShort(Activity activity, int resId) {
        String info = activity.getString(resId);

        SuperToast(activity, info, true);
    }

    public static void toastLong(Activity activity, String info) {
        SuperToast(activity, info, false);
    }

    public static void toastLong(Activity activity, int resId) {
        String info = activity.getString(resId);

        SuperToast(activity, info, false);
    }

    public static void SuperToast(Activity activity, String info, boolean isShort) {
        SuperToast(activity, info, isShort, true);
    }

    public static void SuperToast(Activity activity, String info, boolean isShort, boolean isFailed) {
        if (!isFailed)
            return;

        SuperCardToast toast = new SuperCardToast(activity, SuperToast.Type.STANDARD);
        toast.setAnimations(SuperToast.Animations.POPUP);
        toast.setText(info);
        toast.setDuration(isShort ? SuperToast.Duration.SHORT : SuperToast.Duration.LONG);
        toast.setBackground(isFailed ? R.color.red : R.color.yellow_special);
        toast.setTextColor(Color.WHITE);
        toast.setTextSize(SuperToast.TextSize.MEDIUM);
        toast.setTouchToDismiss(true);
        toast.show();
    }

    public static String getScreenResolution(Activity activity) {
        // 方法1 Android获得屏幕的宽和高
        getScreenWidthPixels(activity);
        int widthPixels = getScreenWidthPixels(activity);
        int heightPixels = getScreenHeightPixels(activity);  // 屏幕高度（像素）
        if (heightPixels <= 1000 && heightPixels >= 750  //当屏幕分辨率在 960 到 750*450 之间时都按照
                && widthPixels >= 450 && widthPixels <= 540)
            return mWVGA;
        if (heightPixels >= 1700 && widthPixels >= 960)
            return mxxHD;

        return mxHD;
    }

    public static int getScreenWidthPixels(Activity mActivity) {
        DisplayMetrics metric = getScreenMetrics(mActivity);
        int widthPixels = metric.widthPixels;  // 屏幕宽度（像素）
        return widthPixels;
    }

    public static int getScreenHeightPixels(Activity mActivity) {
        DisplayMetrics metric = getScreenMetrics(mActivity);
        int heightPixels = metric.heightPixels;  // 屏幕高度（像素）
        return heightPixels;
    }

    public static int getScreenDPI(Activity mActivity) {
        DisplayMetrics metric = getScreenMetrics(mActivity);
        int dpi = metric.densityDpi;
        return dpi;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getScreenDensity(Activity mActivity) {
        DisplayMetrics metric = getScreenMetrics(mActivity);
        float density = metric.density;
        return density;
    }

    public static DisplayMetrics getScreenMetrics(Activity mActivity) {
        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    /**
     * lan字体，中文时用的字体
     *
     * @return
     */
    public static synchronized Typeface getO2Typeface() {
        if (mTypeFace == null) {
            Context context = MyApplication.getInstance().getApplicationContext();

            mTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/lan.ttf");
        }
        return mTypeFace;
    }

    /**
     * Spotify字体，暂时没用
     *
     * @return
     */
    public static synchronized Typeface getO2spoticonRegularTypeface() {
        if (mTypeFace == null) {
            Context context = MyApplication.getInstance().getApplicationContext();

            mTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/GMSansUI_Regular.ttf");
        }
        return mTypeFace;
    }

    /**
     * Spotify字体,非中文时用的字体
     *
     * @return
     */
    public static synchronized Typeface getO2spoticonMediumTypeface() {
        if (mTypeFace == null) {
            Context context = MyApplication.getInstance().getApplicationContext();

            mTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/GMSansUI_Medium.ttf");
        }
        return mTypeFace;
    }

    /**
     * BabelSans-Bold字体,非中文时用的字体
     *
     * @return
     */
    public static synchronized Typeface getO2BabelSansBoldTypeface() {
        if (mTypeFace == null) {
            Context context = MyApplication.getInstance().getApplicationContext();

            mTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/BabelSans-Bold.ttf");
        }
        return mTypeFace;
    }

    public static void blur(Context context, Bitmap bkg, View view) {
        try {
            float scaleFactor = 8;
            float radius = 20;
            radius = 2;

            int width = view.getWidth();
            int height = view.getHeight();
            if (width == 0 || height == 0) {
                view.measure(0, 0);
                width = view.getMeasuredWidth();
                height = view.getMeasuredHeight();
            }
            int widthAmpFactor = (int) (scaleFactor * (getScreenHeightPixels((Activity) context) / bkg.getHeight())) + 1;
            int heightAmpFactor = (int) (scaleFactor * (getScreenWidthPixels((Activity) context) / bkg.getWidth())) + 1;
            Bitmap overlay = Bitmap.createBitmap(width / heightAmpFactor, height / widthAmpFactor, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(overlay);
            canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
            canvas.scale(1 / scaleFactor, 1 / scaleFactor);
            Paint paint = new Paint();
            paint.setFlags(Paint.FILTER_BITMAP_FLAG);
            canvas.drawBitmap(bkg, 0, 0, paint);
            overlay = FastBlur.doBlur(overlay, (int) radius, true);
            Drawable drawable = new BitmapDrawable(context.getResources(), overlay);
            view.setBackgroundDrawable(drawable);
        } catch (Exception e) {
            Log.w("BLUR", "failed: " + e.getLocalizedMessage());
        }
    }


    /**
     * 动态设置ListView组建的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {

            return;

        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0);

            totalHeight += listItem.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        // params.height += 5;// if without this statement,the listview will be

        // listView.getDividerHeight()获取子项间分隔符占用的高度

        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);
    }

    /**
     * 动态设置ListView组建的高度,并设置最多现实的item的个数
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView, int maxCount) {

        ListAdapter listAdapter = listView.getAdapter();
        int count = listAdapter.getCount();
        if (count > maxCount) {
            count = maxCount;
        }

        if (listAdapter == null) {

            return;

        }

        int totalHeight = 0;

        for (int i = 0; i < count; i++) {

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0);

            totalHeight += listItem.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (count - 1));

        listView.setLayoutParams(params);
    }

    /**
     * 根据屏幕的宽度设置图片的大小，调用完成后这个图片的大小就成了一定比例的正方形
     *
     * @param activity
     * @param imageView
     * @param scale
     */
    public static void setImageSizeByScreen(Activity activity, ImageView imageView, double scale) {
        ViewGroup.LayoutParams tipImageParams = imageView.getLayoutParams();
        tipImageParams.width = (int) (getScreenWidthPixels(activity) * scale);
        tipImageParams.height = tipImageParams.width;
        imageView.setLayoutParams(tipImageParams);
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }
}
