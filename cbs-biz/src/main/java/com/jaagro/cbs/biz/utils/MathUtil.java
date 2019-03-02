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
     * @author @Gao.
     * @param minNum
     * @param maxNum2
     * @return
     */
    public String percentage(Integer minNum, Integer maxNum2) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = null;
        if (maxNum2 > 0) {
            numberFormat.format((float) minNum / (float) maxNum2 * 100);
        }
        return result.concat("%");
    }

    /**
     * 判断字符串是否是数字
     * @author @Gao.
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
