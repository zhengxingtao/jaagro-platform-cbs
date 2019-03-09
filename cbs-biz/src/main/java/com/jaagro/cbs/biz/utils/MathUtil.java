package com.jaagro.cbs.biz.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 计算工具类
 * @author: @Gao.
 * @create: 2019-02-28 14:34
 **/
@Service
@Slf4j
public class MathUtil {

    private static Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");


    /**
     * 求百分数
     *
     * @param minNum
     * @param maxNum
     * @return
     * @author @Gao.
     */
    public String percentage(Integer minNum, Integer maxNum) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = "";
        if (maxNum > 0) {
            result = numberFormat.format((float) minNum / (float) maxNum * 100);
        }
        return result.concat("%");
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     * @author @Gao.
     */
    public static boolean isNum(String str) {
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
