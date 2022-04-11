package com.hebust.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 获取日期对象格式化后的字符串
     * @return String
     */
    public static String getCurrentDateString(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 获取时间格式化后的字符串
     * @return String
     */
    public static String getCurrentTimeString(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取当前日期对象
     * @return Date
     * @throws ParseException
     */
    public static Date getCurrentDate(){
        String currentDateString = getCurrentDateString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(currentDateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间对象
     * @return Time
     */
    public static Time getCurrentTime(){
        String currentTimeString = getCurrentTimeString();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date parse = null;
        try {
            parse = sdf.parse(currentTimeString);
            return new Time(parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
