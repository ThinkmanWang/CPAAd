package com.thinkman.thinkwebviewutils.view.utils;

import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

public class TouchEventUtils {
    public static Handler mHandler = new Handler();

    public static void scrollDown(final View view) {
        scrollDown(view, 1080, 1920);
    }

    public static void scrollDown(final View view, int nWidth, int nHeight) {

        final int nStartX = nWidth / 2;
        final int nStartY = nHeight * 2 / 10;

        final int nStopX = nWidth * 8 / 10;
        final int nStopY = nHeight * 9 / 10;

        dispatchScrollEvent(view, nStartX, nStartY, nStopX, nStopY);
    }

    public static void scrollUp(final View view) {
        scrollUp(view, 1080, 1920);
    }

    public static void scrollUp(final View view, int nWidth, int nHeight) {
        final int nStartX = nWidth * 8 / 10;
        final int nStartY = nHeight * 9 / 10;

        final int nStopX = nWidth / 2;
        final int nStopY = nHeight * 2 / 10;

        dispatchScrollEvent(view, nStartX, nStartY, nStopX, nStopY);
    }

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

    public static void dispatchClieckEvent(final View view, final int nX, final int nY) {
        final long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, nX, nY, 0);
        view.dispatchTouchEvent(event);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MotionEvent _event = MotionEvent.obtain(downTime, SystemClock.uptimeMillis() + 100, MotionEvent.ACTION_UP, nX, nY, 0);
                view.dispatchTouchEvent(_event);
            }
        }, 100);
    }
}
