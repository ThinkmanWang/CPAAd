package com.thinkman.thinkwebviewutils.javascript;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class PositionUtils {

    public static PositionListener mListener = null;

    public static void findPositionByID(WebView webView, String szID, PositionListener listener) {
        mListener = listener;

        webView.loadUrl("javascript: (function() { " +
                "   var element = document.getElementById(\"" + szID +"\"); " +
                "   positionUtils.positionGot(document.body.scrollLeft + element.offsetLeft, document.body.scrollTop + element.offsetLeft, element.offsetWidth, element.offsetHeight); " +
                "})()");
    }

    public static void findFirstVisibleElementByClassName(WebView webView, String szClsName, PositionListener listener) {
        mListener = listener;

        webView.loadUrl("javascript: (function() { " +
                "   var elementList = document.getElementsByClassName(\"" + szClsName +"\"); " +
                "   for (var i = 0; i < elementList.length; i++) { " +
                "       var rect = elementList[i].getBoundingClientRect(); " +
                "       if (rect.top >= 0) { " +
//                "           alert('FXXK ' + rect.left + ',' + rect.top + ',' + rect.width + ',' + rect.height); " +
                "           positionUtils.positionGot(rect.left, rect.top, rect.width, rect.height); " +
                "           return;" +
                "       } " +
                "   } " +
                "})()");
    }

    @JavascriptInterface
    public void positionGot(int nLeft, int nTop, int nWidth, int nHeight) {
        if (null == mListener) {
            return;
        }

        mListener.onPositionGot(nLeft, nTop, nWidth, nHeight);
    }

    public interface PositionListener {
        public void onPositionGot(int nLeft, int nTop, int nWidth, int nHeight);
    }
}
