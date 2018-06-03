package com.higo.tinklabstest.base;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.higo.tinklabstest.R;
import com.higo.tinklabstest.utils.FontManager;
import com.shamanland.fonticon.FontIconTypefaceHolder;

/**
 *
 * @author sharkliu
 * @version 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Resources res;
    protected BaseApplication baseApplication;
    InputMethodManager inputMethodManager;//软键盘

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        res = this.getApplicationContext().getResources();

        baseApplication = (BaseApplication) this.getApplication();

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        FontIconTypefaceHolder.init(getAssets(), FontManager.FONTAWESOME);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {//获取焦点
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);//隐藏软键盘
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        // 强制竖屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
