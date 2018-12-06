package com.thinkman.thinkutils;

import android.util.Log;

public class ThinkLog {

    public static void debug(String szTag, String szMsg) {
        Exception ex = new Exception();
        StackTraceElement stack = ex.getStackTrace()[1];

        String fullClassName = stack.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = stack.getMethodName();
        int lineNumber = stack.getLineNumber();

        //[2018-12-04 17:55:42,011] MainThread - log.py <module>():173  DEBUG 	Hello World 1234
        String _szMsg = String.format("[%s] %s.%s():%d    %s", DateTimeUtils.getDateTimeStr(), className, methodName, lineNumber, szMsg);
        Log.d(szTag, _szMsg);
    }

    public static void info(String szTag, String szMsg) {
        Exception ex = new Exception();
        StackTraceElement stack = ex.getStackTrace()[1];

        String fullClassName = stack.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = stack.getMethodName();
        int lineNumber = stack.getLineNumber();

        //[2018-12-04 17:55:42,011] MainThread - log.py <module>():173  DEBUG 	Hello World 1234
        String _szMsg = String.format("[%s] %s.%s():%d    %s", DateTimeUtils.getDateTimeStr(), className, methodName, lineNumber, szMsg);
        Log.i(szTag, _szMsg);
    }

    public static void warn(String szTag, String szMsg) {
        Exception ex = new Exception();
        StackTraceElement stack = ex.getStackTrace()[1];

        String fullClassName = stack.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = stack.getMethodName();
        int lineNumber = stack.getLineNumber();

        //[2018-12-04 17:55:42,011] MainThread - log.py <module>():173  DEBUG 	Hello World 1234
        String _szMsg = String.format("[%s] %s.%s():%d    %s", DateTimeUtils.getDateTimeStr(), className, methodName, lineNumber, szMsg);
        Log.w(szTag, _szMsg);
    }

    public static void error(String szTag, String szMsg) {
        Exception ex = new Exception();
        StackTraceElement stack = ex.getStackTrace()[1];

        String fullClassName = stack.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = stack.getMethodName();
        int lineNumber = stack.getLineNumber();

        //[2018-12-04 17:55:42,011] MainThread - log.py <module>():173  DEBUG 	Hello World 1234
        String _szMsg = String.format("[%s] %s.%s():%d    %s", DateTimeUtils.getDateTimeStr(), className, methodName, lineNumber, szMsg);
        Log.e(szTag, _szMsg);
    }
}
