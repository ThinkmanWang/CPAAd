package com.thinkman.positiontest;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thinkman.thinkutils.RandomUtils;
import com.thinkman.thinkutils.ThinkLog;
import com.thinkman.thinkwebviewutils.javascript.PositionUtils;
import com.thinkman.thinkwebviewutils.javascript.WebScreenInfoUtils;
import com.thinkman.thinkwebviewutils.view.ThinkWebView;
import com.thinkman.thinkwebviewutils.view.utils.TouchEventUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.wv_main)
    public ThinkWebView m_wvMain;

    public static final String URL = "https://se.nuomi.com/phasterse/middle/smindex?version=2.0.0&format=1&eid=100850&query=%E5%AE%B6%E8%A3%85&nmtradeid=2369&from=cps&us=cpstpsj413&nmcid=cpstpsj413&cid=cpstpsj413&prod=nmwbdl&ip=222.95.250.239&gps=&nmcityid=700010000&nuomisid=1007&fegps=118.8000587%2C31.948233300000002";
    //    public static final String URL = "http://config.huina365.com:8545/status/index.do";
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ThinkLog.debug("THINKMAN", "Hello World");

        initWebView();
        m_wvMain.loadUrl(URL);
    }

    public void onBackPressed() {
        if (m_wvMain.canGoBack()) {
            m_wvMain.goBack();
        } else {
            return;
        }
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

        WebSettings webSettings = this.m_wvMain.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setGeolocationEnabled(false);
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                this.m_wvMain.getSettings().setMixedContentMode(0);
            } catch (Exception ex) {

            }
        }

        webSettings.setBuiltInZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
//        webSettings.supportMultipleWindows();
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLoadsImagesAutomatically(true);

        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        this.m_wvMain.addJavascriptInterface(new WebScreenInfoUtils(), "screenInfoUtils");
        this.m_wvMain.addJavascriptInterface(new PositionUtils(), "positionUtils");
    }

    class MyViewClient extends WebViewClient {
        public MyViewClient(Context paramContext) {

        }

        public void onLoadResource(WebView paramWebView, String paramString) {

        }

        public void onPageFinished(WebView paramWebView, String paramString) {
            super.onPageFinished(paramWebView, paramString);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    WebScreenInfoUtils.collectScreenInfoJs(m_wvMain, new WebScreenInfoUtils.OnWebScreenInfoListener() {
                        @Override
                        public void onGetScreenInfo(int nWidth, int nHeight) {
                            ThinkLog.debug("THINKMAN", "[" + nWidth + ":" + nHeight + "]");
                        }
                    });

                    PositionUtils.findFirstVisibleElementByClassName(m_wvMain, "item-info", new PositionUtils.PositionListener() {
                        @Override
                        public void onPositionGot(int nLeft, int nTop, int nWidth, int nHeight) {
                            String szPos = String.format("(%d, %d, %d, %d)", nLeft, nTop, nWidth, nHeight);
                            ThinkLog.debug("THINKMAN", szPos);

                            int nX = nLeft + RandomUtils.random(0, nWidth);
                            int nY = nTop + RandomUtils.random(0, nHeight);

                            ThinkLog.debug("THINKMAN", "click at (" + nX + "," + nY + ")");
                            TouchEventUtils.dispatchClieckEvent(m_wvMain, nX, nY);
                        }
                    });

                }
            }, 3000);

//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    TouchEventUtils.scrollUp(m_wvMain);
//                }
//            }, 4000);
//
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    TouchEventUtils.scrollDown(m_wvMain);
//                }
//            }, 5000);

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
            ThinkLog.debug("THINKMAN", paramWebResourceRequest.getUrl().toString());
            return super.shouldInterceptRequest(paramWebView, paramWebResourceRequest);
        }

        public boolean shouldOverrideUrlLoading(WebView paramWebView, WebResourceRequest paramWebResourceRequest)
        {
            return super.shouldOverrideUrlLoading(paramWebView, paramWebResourceRequest);
        }

        public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
            ThinkLog.debug("THINKMAN", paramString);
            return false;
        }
    }
}
