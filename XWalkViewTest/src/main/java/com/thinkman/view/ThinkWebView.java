package com.thinkman.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.ValueCallback;

import org.xwalk.core.XWalkView;

public class ThinkWebView extends XWalkView {
    public ThinkWebView(Context context) {
        super(context);
    }

    public ThinkWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
                "           return {'left': parseInt(rect.left), 'top': parseInt(rect.top), 'width': parseInt(rect.width), 'height': parseInt(rect.height), 'text': 'FXXK'}; " +
                "       } " +
                "   } " +
                "   return {'left': 0, 'top': 0, 'width': 0, 'height': 0, 'text': ''}; " +
                "})()", resultCallback);
    }

    public void findFirstVisibleElementByClassName(String szClsName, ValueCallback<String> resultCallback) {
        this.evaluateJavascript("javascript: (function() { " +
                "   var elementList = document.getElementsByClassName(\"" + szClsName +"\"); " +
                "   for (var i = 0; i < elementList.length; i++) { " +
                "       var rect = elementList[i].getBoundingClientRect(); " +
                "       if (rect.top >= 0) { " +
                "           return {'left': parseInt(rect.left), 'top': parseInt(rect.top), 'width': parseInt(rect.width), 'height': parseInt(rect.height), 'text': elementList[i].innerText};" +
                "       } " +
                "   } " +
                "   return {'left': 0, 'top': 0, 'width': 0, 'height': 0, 'text': ''}; " +
                "})()", resultCallback);
    }
}
