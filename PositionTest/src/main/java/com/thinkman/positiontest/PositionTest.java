package com.thinkman.positiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thinkman.thinkutils.ThinkLog;
import com.thinkman.thinkwebviewutils.view.ThinkWebView;

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
    }
}
