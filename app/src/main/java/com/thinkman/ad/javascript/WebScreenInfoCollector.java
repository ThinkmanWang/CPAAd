package com.thinkman.ad.javascript;

import android.webkit.JavascriptInterface;

public class WebScreenInfoCollector {

    public static int sScreenInnerHeight;
    public static int sScreenInnerWidth;
    public static int sScrollX;
    public static int sScrollY;

    public static String collectScreenInfoJs()
    {
        return "javascript: (function() {\n" +
                "    screenInfoCollector.setInnerScreenInfo(document.documentElement.clientWidth, document.documentElement.clientHeight);\n" +
//                "    alert('clientHeight ' + document.documentElement.clientHeight);\n" +
//                "    alert('height ' + window.screen.height);\n" +
//                "    alert('availHeight ' + window.screen.availHeight);\n" +
                "})()";
    }

    public static String collectScrollInfoJs()
    {
        return "javascript: (function() {\n" +
                "    var item = document.querySelector('.trade-detail-main');\n" +
                "    if (item) {\n" +
                "        screenInfoCollector.setScrollInfo(item.scrollLeft, item.scrollTop);\n" +
                "    }\n" +
                "})()";
    }

    @JavascriptInterface
    public void setInnerScreenInfo(int paramInt1, int paramInt2)
    {
        sScreenInnerWidth = paramInt1;
        sScreenInnerHeight = paramInt2;
    }

    @JavascriptInterface
    public void setScrollInfo(int paramInt1, int paramInt2)
    {
        sScrollX = paramInt1;
        sScrollY = paramInt2;
    }

    public interface OnWebScreenInfoListener {
        void onInnerScreenInfo(int nWidth, int nHeight);
        void onScrollInfo(int nScrollX, int nScrollY);
    }
}
