package com.thinkman.thinkutils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static Date getCurrentTime() {
        return new Date();
    }

    public static Date getCurrentTime1() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date get5minAgoTime() {
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        if (minute >= 5) {
            calendar.set(Calendar.MINUTE, minute - 5);
        } else {
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 1);
            calendar.set(Calendar.MINUTE, minute - 5);
        }
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getYesterdayTime(Date date) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static String yesterday(Date date) {
        if (null == date) {
            return "";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(calendar.getTime());
    }

    public static int getDiffDay(Date src, Date dst) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(src);
        int srcDay = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(dst);
        int dstDay = calendar.get(Calendar.DAY_OF_YEAR);
        return dstDay - srcDay;
    }

    public static String getDiffDay(Date date, int nDiff) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, nDiff);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(calendar.getTime());
    }

    public static String getDiffDayStart(Date date, int nDiff) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, nDiff);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%s 00:00:00", format.format(calendar.getTime()));
    }

    public static String getDiffDayEnd(Date date, int nDiff) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, nDiff);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%s 23:59:59", format.format(calendar.getTime()));
    }

    public static String getTodayStart() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%s 00:00:00",  format.format(new Date()));
    }

    public static String getTodayEnd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%s 23:59:59",  format.format(new Date()));
    }

    public static String getDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static String getDateTimeStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public static long getTimeStamp() {
        Date date = new Date();
        return date.getTime() / 1000L;
    }

    public static long date2TimeStamp(Date date) {
        return date.getTime() / 1000L;
    }

    public static String date2Str(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

//	public static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls()
//			.setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static Date getDefaultDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, 1, 1, 12, 0, 0);
        return calendar.getTime();
    }
}
