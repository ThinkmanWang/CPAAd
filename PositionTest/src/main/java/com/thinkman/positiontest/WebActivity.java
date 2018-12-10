package com.thinkman.positiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {

    public static final String URL = "https://se.nuomi.com/phasterse/middle/smindex?version=2.0.0&format=1&eid=100850&query=%E5%AE%B6%E8%A3%85&nmtradeid=2369&from=cps&us=cpstpsj413&nmcid=cpstpsj413&cid=cpstpsj413&prod=nmwbdl&ip=222.95.250.239&gps=&nmcityid=700010000&nuomisid=1007&fegps=118.8000587%2C31.948233300000002";

    @BindView(R.id.wv_main_02)
    WebView m_wvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_web);

        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void initView() {
        WebSettings webSettings = m_wvMain.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSettings.setAllowFileAccess(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webSettings.setLoadWithOverviewMode(true);
//
////        m_wvMain.setWebChromeClient(new WebChromeClient());
//        webSettings.setDomStorageEnabled(true);
//        m_wvMain.getSettings().setLoadWithOverviewMode(true);
//
//        m_wvMain.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return false;
//            }
//
//        });

        m_wvMain.loadUrl(URL);
    }
}
