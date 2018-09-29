package com.ahapp.huantianxidi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/6/30.
 */

public class MyFreshScrollview extends ScrollView {

    private ScrollViewListener scrollViewListener = null;

    public MyFreshScrollview(Context context) {
        super(context);
    }

    public MyFreshScrollview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyFreshScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface ScrollViewListener {
        void onScrollChanged(ScrollView scroll, int x, int y, int oldx, int oldy);
    }
}