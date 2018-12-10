package com.thinkman.thinkwebviewutils.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
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

    public void writeValToLocalData(String szData, ValueCallback<String> resultCallback) {
        this.evaluateJavascript("javascript: (function() { " +
                        "   window.localData = '" + szData + "'; " +
                        "   return window.localData; " +
                        "})()"
                , resultCallback);
    }

    public void getLogId(ValueCallback<String> resultCallback) {
        this.evaluateJavascript("javascript: (function() { " +
                        "   return window.localData; " +
//                        "   return window.localData.logid;" +
                "})()"
                , resultCallback);
    }

    public void findFirstVisibleElementByClassText(String szClsName, String szText, ValueCallback<String> resultCallback) {
        this.evaluateJavascript("javascript: (function() { " +
                "   var elementList = document.getElementsByClassName(\"" + szClsName +"\"); " +
                "   for (var i = 0; i < elementList.length; i++) { " +
                "       var szText = elementList[i].innerText; " +
                "       var nIndex = szText.indexOf(\"" + szText + "\"); " +
                "       if (-1 == nIndex) { " +
                "           continue; " +
                "       } " +
                "       " +
                "       var rect = elementList[i].getBoundingClientRect(); " +
                "       if (rect.top >= 0) { " +
                "           return {'left': parseInt(rect.left), 'top': parseInt(rect.top), 'width': parseInt(rect.width), 'height': parseInt(rect.height)}; " +
                "       } " +
                "   } " +
                "   return {'left': 0, 'top': 0, 'width': 0, 'height': 0}; " +
                "})()", resultCallback);
    }

    public void findFirstVisibleElementByClassName(String szClsName, ValueCallback<String> resultCallback) {
        this.evaluateJavascript("javascript: (function() { " +
                "   var elementList = document.getElementsByClassName(\"" + szClsName +"\"); " +
                "   for (var i = 0; i < elementList.length; i++) { " +
                "       var rect = elementList[i].getBoundingClientRect(); " +
                "       if (rect.top >= 0) { " +
                "           return {'left': parseInt(rect.left), 'top': parseInt(rect.top), 'width': parseInt(rect.width), 'height': parseInt(rect.height)};" +
                "       } " +
                "   } " +
                "   return {'left': 0, 'top': 0, 'width': 0, 'height': 0}; " +
                "})()", resultCallback);
    }
}
