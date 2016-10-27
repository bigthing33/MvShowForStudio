package com.cyq.mvshow.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.cyq.mvshow.R;


public class O2FontPasswordEditText extends O2FontEditText {
    /**
     * EditText右侧的图标
     */
    protected Drawable mRightDrawable;
    protected Context mContext;

    public O2FontPasswordEditText(Context context) {
        super(context);
        init(context);

    }

    public O2FontPasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext=context;
        initPasswordEditText(context);
    }

    private void initPasswordEditText(Context context) {
        mRightDrawable = getCompoundDrawables()[2];

        if (mRightDrawable == null) {
            //这里当没有设置右侧图标时你可以给它设置个默认的右侧图标，当然根据你的项目需求来
            return;
        }

        //这里当设置了右侧图标时，我们对图标做了一些自定义设置，你也可以做其他设置
        mRightDrawable.setBounds(0, 0, mRightDrawable.getIntrinsicWidth(), mRightDrawable.getIntrinsicHeight());

    }

    public void setRightPicOnclickListener(RightPicOnclickListener rightPicOnclickListener) {
        this.rightPicOnclickListener = rightPicOnclickListener;
    }

    public interface RightPicOnclickListener {
        void rightPicClick();
    }

    private RightPicOnclickListener rightPicOnclickListener;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    //设置点击EditText右侧图标EditText失去焦点，
                    // 防止点击EditText右侧图标EditText获得焦点，软键盘弹出
                    setFocusableInTouchMode(false);
                    setFocusable(false);

                    //点击EditText右侧图标事件接口回调
                    if (rightPicOnclickListener != null) {
                        rightPicOnclickListener.rightPicClick();
                    }
                    int cursorPosition = length();
                    if (getInputType()==(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                        setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        setCompoundDrawablesWithIntrinsicBounds (null,null, ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher),null);
                    }else{
                        setCompoundDrawablesWithIntrinsicBounds (null,null, ContextCompat.getDrawable(mContext,R.mipmap.ic_launcher),null);
                        setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                    setSelection(cursorPosition);
                } else {
                    //设置点击EditText输入区域，EditText请求焦点，软键盘弹出，EditText可编辑
                    setFocusableInTouchMode(true);
                    setFocusable(true);
                }
            }
        }
        return super.onTouchEvent(event);
    }

//    /**
//     * 设置晃动动画
//     */
//    public void setShakeAnimation() {
//        this.setAnimation(shakeAnimation(5));
//    }
//
//    /**
//     * 晃动动画
//     *
//     * @param counts 1秒钟晃动多少下
//     * @return
//     */
//    public static Animation shakeAnimation(int counts) {
//        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
//        translateAnimation.setInterpolator(new CycleInterpolator(counts));
//        translateAnimation.setDuration(1000);
//        return translateAnimation;
//    }
}
