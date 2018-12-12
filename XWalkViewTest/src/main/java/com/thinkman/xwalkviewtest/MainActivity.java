package com.thinkman.xwalkviewtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.ValueCallback;

import com.thinkman.thinkutils.RandomUtils;
import com.thinkman.thinkutils.ThinkLog;
import com.thinkman.thinkutils.TouchEventUtils;
import com.thinkman.view.ThinkWebView;

import org.json.JSONObject;
import org.xwalk.core.XWalkNavigationHistory;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    public static final String URL = "https://se.nuomi.com/phasterse/middle/smindex?version=2.0.0&format=1&eid=100850&query=%E5%AE%B6%E8%A3%85&nmtradeid=2369&from=cps&us=cpstpsj413&nmcid=cpstpsj413&cid=cpstpsj413&prod=nmwbdl&ip=222.95.250.239&gps=&nmcityid=700010000&nuomisid=1007&fegps=118.8000587%2C31.948233300000002";

    @BindView(R.id.wv_main)
    ThinkWebView m_wvMain = null;

    MyHandler mHandler = new MyHandler();

    private boolean m_bPageLoaded = false;

    private class MyHandler extends Handler {

        public static final int MSG_PAGE_LOAD_FINISHED = 0;

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_PAGE_LOAD_FINISHED:
                    MainActivity.this.startDoNuomi();
                    break;
            }
        }
    }

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

        m_wvMain.setResourceClient(new XWalkResourceClient(m_wvMain) {
            @Override
            public void onLoadFinished(XWalkView view, String url) {
                if (false == m_bPageLoaded) {
                    m_bPageLoaded = true;
                    mHandler.sendEmptyMessageAtTime(MyHandler.MSG_PAGE_LOAD_FINISHED, 1000);
                } else {

                }
            }
        });
    }

    @OnClick(R.id.btn_run_js)
    public void runJs() {
//        String szJS = "javascript: (function() { return window.localData.logid; })()";
//
//        m_wvMain.evaluateJavascript(szJS, new ValueCallback<String>() {
//            @Override
//            public void onReceiveValue(String value) {
//                ThinkLog.debug("THINKMAN", value);
//            }
//        });

        m_wvMain.findFirstVisibleElementByClassName("item-info", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                ThinkLog.debug("THINKMAN", value);
            }
        });
    }

    public void startDoNuomi() {
        ThinkLog.debug("THINKMAN", "Start do nuomi");

        int nTime = 0;
        int nRand = RandomUtils.random(0, 10);
        ThinkLog.debug("THINKMAN", "scroll " + nRand + " times");

        for (int i = 0; i < nRand; ++i) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ThinkLog.debug("THINKMAN", "scroll webview");
                    TouchEventUtils.scrollUp(m_wvMain);
                }
            }, i * 1000 + 1000);
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                m_wvMain.findFirstVisibleElementByClassName("item-info", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        ThinkLog.debug("THINKMAN", value);

                        try {
                            JSONObject jObj = new JSONObject(value);

                            ThinkLog.debug("THINKMAN", "Click at " + jObj.getString("text"));

                            TouchEventUtils.dispatchClieckEvent(m_wvMain
                                    , jObj.getInt("left")
                                    , jObj.getInt("top")
                                    , jObj.getInt("width")
                                    , jObj.getInt("height"));
                            jObj.getInt("top");

                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ThinkLog.debug("THINKMAN", "press back");
                                    if (m_wvMain.getNavigationHistory().canGoBack()) {
                                        m_wvMain.getNavigationHistory().navigate(XWalkNavigationHistory.Direction.BACKWARD, 1) ;
                                    }
                                }
                            }, 5000);
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ThinkLog.debug("THINKMAN", "press back");
                                    if (m_wvMain.getNavigationHistory().canGoBack()) {
                                        m_wvMain.getNavigationHistory().navigate(XWalkNavigationHistory.Direction.BACKWARD, 1) ;
                                    }
                                }
                            }, 6000);
                        } catch (Exception ex) {

                        }

                    }
                });
            }
        }, 10 * 1000);


    }

}
