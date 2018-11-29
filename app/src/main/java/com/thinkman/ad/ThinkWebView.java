package com.thinkman.ad;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.webkit.WebView;

public class ThinkWebView extends WebView {
    public ThinkWebView(Context context) {
        super(context);
    }

    public ThinkWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThinkWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
        return super.dispatchTouchEvent(paramMotionEvent);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
    {
        super.onFocusChanged(paramBoolean, paramInt, paramRect);
    }

    public void onWindowFocusChanged(boolean paramBoolean)
    {
        super.onWindowFocusChanged(paramBoolean);
    }

    protected void onWindowVisibilityChanged(int paramInt)
    {
        super.onWindowVisibilityChanged(paramInt);
    }

    public void setLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
        super.setLayoutParams(paramLayoutParams);
    }
}
