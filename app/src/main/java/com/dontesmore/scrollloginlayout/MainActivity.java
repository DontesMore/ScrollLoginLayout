package com.dontesmore.scrollloginlayout;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private ResizeRelativeLayout mResizeRelativeLayout;
    private ScrollView mScrollView;
    private int times;
    private LinearLayout mForgetLayout;
    private Handler mHandler;

    private final int KEYBOARD_SHOW = 1001;
    private final int KEYBOARD_HIDDEN = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners();


    }

    private void initView() {
        mResizeRelativeLayout = (ResizeRelativeLayout) findViewById(R.id.login_page);
        mScrollView = (ScrollView) findViewById(R.id.login_scrollview);
        mForgetLayout = (LinearLayout) findViewById(R.id.forget_linear);
    }

    private void initListeners() {
        mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case KEYBOARD_SHOW:
                        mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        mForgetLayout.setVisibility(View.GONE);
                        break;
                    case KEYBOARD_HIDDEN:
                        mForgetLayout.setVisibility(View.VISIBLE);
                        break;

                    default:
                        break;
                }
            };
        };
        mResizeRelativeLayout.setOnResizeListener(new ResizeRelativeLayout.OnResizeListener() {
            @Override
            public void OnResize(int w, int h, int oldw, int oldh) {
                Message msg = new Message();
                if (h < oldh) {
                    Toast.makeText(MainActivity.this, "软键盘弹出 :" + times++, Toast.LENGTH_SHORT).show();
                    msg.what = KEYBOARD_SHOW;
                } else {
                    Toast.makeText(MainActivity.this, "软键盘隐藏 :" + times++, Toast.LENGTH_SHORT).show();
                    msg.what = KEYBOARD_HIDDEN;
                }
                mHandler.sendMessage(msg);
            }
        });
    }
}
