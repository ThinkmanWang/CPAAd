package com.thinkman.positiontest;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thinkman.thinkutils.ThinkLog;
import com.thinkman.thinkwebviewutils.javascript.WebScreenInfoCollector;
import com.thinkman.thinkwebviewutils.view.ThinkWebView;
import com.thinkman.thinkwebviewutils.view.ThinkWebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PositionTest extends AppCompatActivity {

    @BindView(R.id.wv_main)
    public ThinkWebView m_wvMain;

    public static final String URL = "http://i.hao61.net/jump/pejt-1";

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_position_test);

        ButterKnife.bind(this);

        ThinkLog.debug("THINKMAN", "Hello World");

        initWebView();
        m_wvMain.loadUrl(URL);
    }

    private void initWebView() {
        this.m_wvMain.setWebViewClient(new MyViewClient(this));

        this.m_wvMain.setWebChromeClient(new WebChromeClient() {
            public void onHideCustomView() {}

            public boolean onJsAlert(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult) {
                return super.onJsAlert(null, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
            }

            public boolean onJsConfirm(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult) {
                return super.onJsConfirm(paramAnonymousWebView, paramAnonymousString1, paramAnonymousString2, paramAnonymousJsResult);
            }

            public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString) {
                super.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
            }
        });

        WebSettings localWebSettings = this.m_wvMain.getSettings();
        localWebSettings.setSupportZoom(false);
        localWebSettings.setBuiltInZoomControls(false);
        localWebSettings.setUseWideViewPort(true);
        localWebSettings.setSupportMultipleWindows(false);
        localWebSettings.setDomStorageEnabled(true);
        localWebSettings.setJavaScriptEnabled(true);
        localWebSettings.setGeolocationEnabled(false);
        localWebSettings.setLoadWithOverviewMode(true);
        localWebSettings.setSupportMultipleWindows(true);
        localWebSettings.setLoadsImagesAutomatically(true);

        if (Build.VERSION.SDK_INT >= 21) {
            try {
                this.m_wvMain.getSettings().setMixedContentMode(0);
            } catch (Exception ex) {

            }
        }

        this.m_wvMain.addJavascriptInterface(new WebScreenInfoCollector(), "screenInfoCollector");
    }

    class MyViewClient extends WebViewClient {
        public MyViewClient(Context paramContext) {

        }

        public void onLoadResource(WebView paramWebView, String paramString) {

        }

        public void onPageFinished(WebView paramWebView, String paramString) {
            super.onPageFinished(paramWebView, paramString);

            if (true) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        WebScreenInfoCollector.collectScreenInfoJs(m_wvMain, new WebScreenInfoCollector.OnWebScreenInfoListener() {
                            @Override
                            public void onGetScreenInfo(int nWidth, int nHeight) {
                                ThinkLog.debug("THINKMAN", "[" + nWidth + ":" + nHeight + "]");
                            }
                        });
                    }
                }, 1000);
            }
        }

        public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {
            ThinkLog.debug("THINKMAN", paramString);
        }

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
}
