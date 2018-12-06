package com.thinkman.thinkwebviewutils.javascript;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebScreenInfoCollector {

//    public static int sScreenInnerHeight;
//    public static int sScreenInnerWidth;
//    public static int sScrollX;
//    public static int sScrollY;

    public static OnWebScreenInfoListener mListener = null;

    public static void collectScreenInfoJs(WebView webView, OnWebScreenInfoListener listener)
    {
        mListener = listener;

        webView.loadUrl("javascript: (function() {\n" +
                "    screenInfoCollector.setInnerScreenInfo(document.documentElement.clientWidth, document.documentElement.clientHeight);\n" +
//                "    alert('clientHeight ' + document.documentElement.clientHeight);\n" +
//                "    alert('height ' + window.screen.height);\n" +
//                "    alert('availHeight ' + window.screen.availHeight);\n" +
                "})()");
    }

//    public static String collectScrollInfoJs()
//    {
//        return "javascript: (function() {\n" +
//                "    var item = document.querySelector('.trade-detail-main');\n" +
//                "    if (item) {\n" +
//                "        screenInfoCollector.setScrollInfo(item.scrollLeft, item.scrollTop);\n" +
//                "    }\n" +
//                "})()";
//    }

    @JavascriptInterface
    public void setInnerScreenInfo(int nWidth, int nHeight)
    {
//        sScreenInnerWidth = paramInt1;
//        sScreenInnerHeight = paramInt2;

        if (null == mListener) {
            return;
        }

        mListener.onGetScreenInfo(nWidth, nHeight);
    }

//    @JavascriptInterface
//    public void setScrollInfo(int paramInt1, int paramInt2)
//    {
//        sScrollX = paramInt1;
//        sScrollY = paramInt2;
//    }

    public interface OnWebScreenInfoListener {
        void onGetScreenInfo(int nWidth, int nHeight);
    }
}
