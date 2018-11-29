package com.thinkman.ad.javascript;

import android.webkit.JavascriptInterface;

public class WebScreenInfoCollector {
    public static int sScreenInnerHeight;
    public static int sScreenInnerWidth;
    public static int sScrollX;
    public static int sScrollY;

    public static String collectScreenInfoJs()
    {
        return "javascript:(function() {   screenInfoCollector.setInnerScreenInfo(document.documentElement.clientWidth, document.documentElement.clientHeight);   alert('clientHeight ' + document.documentElement.clientHeight);   alert('height ' + window.screen.height);   alert('availHeight ' + window.screen.availHeight);})()";
    }

    public static String collectScrollInfoJs()
    {
        return "javascript:(function() {   var item = document.querySelector('.trade-detail-main');   if (item) {       screenInfoCollector.setScrollInfo(item.scrollLeft, item.scrollTop);   }})()";
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
}
