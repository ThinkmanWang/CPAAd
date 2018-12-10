package com.thinkman.tbsx5test;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;
import com.thinkman.thinkutils.ThinkLog;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ThinkLog.debug("THINKMAN", " FXXK ");

        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                ThinkLog.debug("THINKMAN", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
                ThinkLog.debug("THINKMAN", " FXXK ");
            }
        });
    }

}
