package com.thinkman.cpaad;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thinkman.ad.javascript.WebScreenInfoCollector;
import com.thinkman.ad.view.ThinkWebView;
import com.thinkman.ad.view.ThinkWebViewClient;
import com.thinkman.ad.view.utils.TouchEventUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.wv_main)
    ThinkWebView m_wvMain = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);

        initWebView();
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

    @OnClick(R.id.btn_load)
    public void onLoadClick() {
        this.m_wvMain.loadUrl("http://config.huina365.com:8545/status/proxy.do");
//        this.m_wvMain.loadUrl("https://m.nuomi.com/webapp/tuan/flowdev?query=%E5%AE%B6%E6%94%BF&nmtradeid=2465&amp;from=CPS&format=3&us=xinyi01&nmcid=xinyi01&amp;cid=xinyi01");
    }

    @OnClick(R.id.btn_scroll_screen)
    public void onScreenInfoClick() {
//        this.m_wvMain.loadUrl(WebScreenInfoCollector.collectScreenInfoJs());
//        this.m_wvMain.loadUrl(WebScreenInfoCollector.collectScrollInfoJs());
//        this.m_wvMain.scrollTo(1024, 2048);

        TouchEventUtils.dispatchScrollEvent(this.m_wvMain, 500, 1800, 450, 100);
    }
}
