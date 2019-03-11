package com.jaagro.cbs.biz.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 时间工具类
 * @author: @Gao.
 * @create: 2019-02-28 13:29
 **/
@Slf4j
public class DateUtil {

    private static Calendar fromCal = Calendar.getInstance();
    private static  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 日期添加指定天数
     *
     * @return
     * @author: @Gao.
     */
    public synchronized static String accumulateDateByDay(Date date, int day) {
        String strDate = simpleDateFormat.format(date);
        Date formatDate = stringToDate(strDate);
        fromCal.setTime(formatDate);
        fromCal.add(Calendar.DATE, day);
        return simpleDateFormat.format(fromCal.getTime());
    }

    /**
     * 将时间字符串转化为Date
     *
     * @param stringDate
     * @return
     * @Author @Gao.
     */
    private static Date stringToDate(String stringDate) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            log.error("I stringToDate-{}", e);
        }
        return date;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static void main(String[] args) {
        System.out.println(getStringDateShort());
    }
}
