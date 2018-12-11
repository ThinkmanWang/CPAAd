package com.thinkman.tbsx5test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.thinkman.thinkutils.ThinkLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://se.nuomi.com/phasterse/middle/smindex?version=2.0.0&format=1&eid=100850&query=%E5%AE%B6%E8%A3%85&nmtradeid=2369&from=cps&us=cpstpsj413&nmcid=cpstpsj413&cid=cpstpsj413&prod=nmwbdl&ip=222.95.250.239&gps=&nmcityid=700010000&nuomisid=1007&fegps=118.8000587%2C31.948233300000002";
//    public static final String URL = "http://soft.imtt.qq.com/browser/tes/feedback.html";
    @BindView(R.id.wv_main)
    WebView m_wvMain = null;

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

    @OnClick(R.id.btn_run_js)
    public void runJs() {
        String szJS = "javascript: (function() { return window.localData.logid; })()";
        m_wvMain.evaluateJavascript(szJS, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                ThinkLog.debug("THINKMAN", value);
            }
        });
    }

    public void initWebView() {
        m_wvMain.setWebViewClient(new WebViewClient());
        m_wvMain.setWebChromeClient(new WebChromeClient() {
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

        WebSettings webSettings = m_wvMain.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setGeolocationEnabled(false);
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
    }
}
