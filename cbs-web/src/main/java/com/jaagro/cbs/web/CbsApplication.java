package com.jaagro.cbs.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tony
 */
@EnableEurekaClient
@EnableCircuitBreaker
@MapperScan("com.jaagro.cbs.biz.mapper")
//@EnableFeignClients(basePackages = {"com.jaagro.tms.biz"})
@SpringBootApplication(scanBasePackages = {"com.jaagro.cbs"})
@EnableTransactionManagement
public class CbsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CbsApplication.class, args);
    }
}