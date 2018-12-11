package com.thinkman.xwalkviewtest;

import android.os.Bundle;

import org.xwalk.core.XWalkNavigationHistory;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    public static final String URL = "https://se.nuomi.com/phasterse/middle/smindex?version=2.0.0&format=1&eid=100850&query=%E5%AE%B6%E8%A3%85&nmtradeid=2369&from=cps&us=cpstpsj413&nmcid=cpstpsj413&cid=cpstpsj413&prod=nmwbdl&ip=222.95.250.239&gps=&nmcityid=700010000&nuomisid=1007&fegps=118.8000587%2C31.948233300000002";

    @BindView(R.id.wv_main)
    XWalkView m_wvMain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initWebView();
        m_wvMain.loadUrl(URL);
    }

    public void onBackPressed() {
        if (m_wvMain.getNavigationHistory().canGoBack()) {
            m_wvMain.getNavigationHistory().navigate(XWalkNavigationHistory.Direction.BACKWARD, 1) ;
        } else {
            return;
        }
    }

    public void initWebView() {
        XWalkPreferences.setValue(XWalkPreferences.ENABLE_JAVASCRIPT, true);
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);

        m_wvMain.getSettings().setSupportZoom(false);
//        m_wvMain.getSettings().setGeolocationEnabled(false);
        m_wvMain.getSettings().setBuiltInZoomControls(false);
//        webSettings.supportMultipleWindows();
        m_wvMain.getSettings().setDomStorageEnabled(true);
        m_wvMain.getSettings().setUseWideViewPort(true);
        m_wvMain.getSettings().setLoadWithOverviewMode(true);
        m_wvMain.getSettings().setLoadsImagesAutomatically(true);

//        m_wvMain.getSettings().setAppCacheEnabled(true);
        m_wvMain.getSettings().setAllowContentAccess(true);
//        m_wvMain.getSettings().setSavePassword(true);
        m_wvMain.getSettings().setSaveFormData(true);
        m_wvMain.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }
}
