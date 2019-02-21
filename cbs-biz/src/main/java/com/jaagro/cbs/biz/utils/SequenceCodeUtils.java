package com.jaagro.cbs.biz.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author gaoxin
 * <p>
 * 生成序列单号
 */
@Slf4j
@Component
public class SequenceCodeUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final int startLength = 9;

    /**
     * 生成唯一长度的序列号
     * @param prefix
     * @return
     */
    public String genSeqCode(String prefix) {
        StringBuilder genSeqCode = new StringBuilder();
        String currentDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(prefix + currentDate, redisTemplate.getConnectionFactory());
        if (entityIdCounter == null) {
            log.info("O genSeqCode entityIdCounter param is null");
            throw new RuntimeException("生成序列号失败");
        }
        Long incrementId = entityIdCounter.getAndIncrement() + 1;
        if (null == incrementId || incrementId.longValue() == 1) {
            entityIdCounter.expire(48, TimeUnit.HOURS);
        }
        int strLength = startLength - incrementId.toString().length();
        String fixLenthString = getFixLenthString(strLength);
        genSeqCode.append(prefix).append(currentDate).append(fixLenthString).append(incrementId);
        return genSeqCode.toString();
    }

    private String getFixLenthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        // 返回固定的长度的随机数
        return fixLenthString.substring(2, strLength + 1);
    }
}
