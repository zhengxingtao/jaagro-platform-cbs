package com.jaagro.cbs.biz.service;

import com.jaagro.cbs.api.dto.base.ShowCustomerDto;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tony
 */
@FeignClient(value = "${feignClient.application.crm}")
public interface CustomerClientService {

    /**
     * 从crm项目获取显示客户对象
     *
     * @param id
     * @return
     */
    @GetMapping("/getShowCustomer/{id}")
    ShowCustomerDto getShowCustomerById(@PathVariable("id") Integer id);


    /**
     * 根据养殖户查询租户
     *
     * @param customerId
     * @return
     */
    @Ignore
    @GetMapping("/getTenantByCustomer/{customerId}")
    Integer getTenantByCustomer(@PathVariable("customerId") Integer customerId);
}
