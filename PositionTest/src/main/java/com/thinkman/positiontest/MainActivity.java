package com.thinkman.positiontest;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
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
import butterknife.OnClick;

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

    @OnClick(R.id.btn_call_js)
    public void callJs() {

//        String szJS = "javascript: (function() { return window.scrollY; })()";
        String szJS = "javascript: (function() { return window.scrollY; })()";
        m_wvMain.evaluateJavascript(szJS, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                ThinkLog.debug("THINKMAN", value);
            }
        });

//        m_wvMain.writeValToLocalData("FXXK", new ValueCallback<String>() {
//            @Override
//            public void onReceiveValue(String value) {
//                ThinkLog.debug("THINKMAN", value);
//            }
//        });

//        m_wvMain.getLogId(new ValueCallback<String>() {
//            @Override
//            public void onReceiveValue(String value) {
//                ThinkLog.debug("THINKMAN", value);
//            }
//        });

//        final String JS = "window.location.href = 'https://www.baidu.com'";
//        m_wvMain.evaluateJavascript("window.location.href = 'https://www.baidu.com'", new ValueCallback<String>() {
//            @Override
//            public void onReceiveValue(String value) {
//                ThinkLog.debug("THINKMAN", value);
//            }
//        });
//
//
//
//        String szText = "长期";
//        m_wvMain.findFirstVisibleElementByClassText("item-info", szText, new ValueCallback<String>() {
//            @Override
//            public void onReceiveValue(String value) {
//                ThinkLog.debug("THINKMAN", value);
//            }
//        });
    }

    private void initWebView() {
//        this.m_wvMain.setWebViewClient(new WebViewClient());
        WebView.setWebContentsDebuggingEnabled(true);
        m_wvMain.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

                WebView.HitTestResult hitTestResult = view.getHitTestResult();
                if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                    view.loadUrl(url);
                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        this.m_wvMain.setClickable(true);
//        this.m_wvMain.setWebChromeClient(new WebChromeClient());

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

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                ThinkLog.debug("THINKMAN", consoleMessage.message() + " -- From line "
                        + consoleMessage.lineNumber() + " of "
                        + consoleMessage.sourceId());
                return super.onConsoleMessage(consoleMessage);
            }
        });

        WebSettings webSettings = this.m_wvMain.getSettings();
        webSettings.setSupportZoom(false);
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                this.m_wvMain.getSettings().setMixedContentMode(0);
            } catch (Exception ex) {

            }
        }

        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.supportMultipleWindows();
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

        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setDatabaseEnabled(true);
        webSettings.setGeolocationEnabled(true);


    }
}
