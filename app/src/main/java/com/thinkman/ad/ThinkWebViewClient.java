package com.thinkman.ad;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thinkman.Constants;

public class ThinkWebViewClient extends WebViewClient {
    public ThinkWebViewClient(Context paramContext) {

    }

    public void onLoadResource(WebView paramWebView, String paramString) {

    }

    public void onPageFinished(WebView paramWebView, String paramString) {
        super.onPageFinished(paramWebView, paramString);

        Log.d(Constants.TAG, paramString);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {}

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2) {
        super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }

    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
        paramSslErrorHandler.proceed();
    }

    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, WebResourceRequest paramWebResourceRequest)
    {
        return super.shouldInterceptRequest(paramWebView, paramWebResourceRequest);
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, WebResourceRequest paramWebResourceRequest)
    {
        return super.shouldOverrideUrlLoading(paramWebView, paramWebResourceRequest);
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
        return false;
    }



}
