package com.thinkman.ad.view.utils;

import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

public class ScrollUtils {
    public static Handler mHandler = new Handler();

    public static void dispatchScrollEvent(final View view, final int nStartX, final int nStartY, final int nEndX, final int nEndY) {
        try {
            int nStepX = (nEndX - nStartX) / 10;
            int nStepY = (nEndY - nStartY) / 10;

            final long downTime = SystemClock.uptimeMillis();
            long eventTime = SystemClock.uptimeMillis();
            MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, nStartX, nStartY, 0);
            view.dispatchTouchEvent(event);

            final int nStep = 80;
            for (int i = 1; i < 9; ++i) {
                eventTime += nStep;
                final int x = nStartX + i * nStepX;
                final int y = nStartY + i * nStepY;

                final long _eventTime = eventTime;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MotionEvent _event = MotionEvent.obtain(downTime, _eventTime, MotionEvent.ACTION_MOVE, x, y, 0);
                        view.dispatchTouchEvent(_event);
                    }
                }, nStep * i);
            }

            eventTime += nStep;

            final long _eventTime = eventTime;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MotionEvent _event = MotionEvent.obtain(downTime, _eventTime, MotionEvent.ACTION_UP, nEndX, nEndY, 0);
                    view.dispatchTouchEvent(_event);
                }
            }, nStep * 10);

        } catch (Exception ex) {

        }
    }
}
