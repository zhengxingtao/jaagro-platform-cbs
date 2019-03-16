package com.jaagro.cbs.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tony
 */
@EnableEurekaClient
@EnableCircuitBreaker
@MapperScan("com.jaagro.cbs.biz.mapper")
@EnableFeignClients(basePackages = {"com.jaagro.cbs.biz"})
@SpringBootApplication(scanBasePackages = {"com.jaagro.cbs"})
@EnableTransactionManagement
@EnableScheduling
public class CbsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CbsApplication.class, args);
    }
}