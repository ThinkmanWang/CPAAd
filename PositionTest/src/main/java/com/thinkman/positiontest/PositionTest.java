package com.thinkman.positiontest;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.thinkman.thinkutils.ThinkLog;
import com.thinkman.thinkwebviewutils.javascript.WebScreenInfoCollector;
import com.thinkman.thinkwebviewutils.view.ThinkWebView;
import com.thinkman.thinkwebviewutils.view.ThinkWebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PositionTest extends AppCompatActivity {

    @BindView(R.id.wv_main)
    public ThinkWebView m_wvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_test);

        ButterKnife.bind(this);

        ThinkLog.debug("THINKMAN", "Hello World");

        initWebView();
        m_wvMain.loadUrl("http://i.hao61.net/jump/pejt-1");
    }

    private void initWebView() {
        this.m_wvMain.setWebViewClient(new ThinkWebViewClient(this));

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
}
