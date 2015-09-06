package com.dontesmore.scrollloginlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 重写RelativeLayout布局，实现压缩模式下软键盘弹�?隐藏的监�? <br>
 *
 * @创建 dontesmore
 * @版本 1.0.0 2015/5/8 14:27 <br>
 */
public class ResizeRelativeLayout extends RelativeLayout{

    private OnResizeListener mListener;

    public ResizeRelativeLayout(Context context) {
        super(context);
    }

    public interface OnResizeListener{
        void OnResize(int w, int h, int oldw, int oldh);
    }

    public void setOnResizeListener(OnResizeListener l){
        mListener = l;
    }

    public ResizeRelativeLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mListener != null){
            mListener.OnResize(w, h, oldw, oldh);
        }

    }
}
