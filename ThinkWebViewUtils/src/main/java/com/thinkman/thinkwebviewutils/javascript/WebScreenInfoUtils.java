package com.thinkman.thinkwebviewutils.javascript;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebScreenInfoUtils {

    public static OnWebScreenInfoListener mListener = null;

    public static void collectScreenInfoJs(WebView webView, OnWebScreenInfoListener listener)
    {
        mListener = listener;

        webView.loadUrl("javascript: (function() {\n" +
                "    screenInfoUtils.setInnerScreenInfo(document.documentElement.clientWidth, document.documentElement.clientHeight);\n" +
                "})()");
    }

    @JavascriptInterface
    public void setInnerScreenInfo(int nWidth, int nHeight)
    {

        if (null == mListener) {
            return;
        }

        mListener.onGetScreenInfo(nWidth, nHeight);
    }

    public interface OnWebScreenInfoListener {
        void onGetScreenInfo(int nWidth, int nHeight);
    }
}
