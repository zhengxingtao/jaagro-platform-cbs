package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.farmer.ReturnBreedingFarmerIndexDto;
import com.jaagro.cbs.api.service.BreedingFarmerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 农户端app 相关api
 * @author: @Gao.
 * @create: 2019-03-04 10:48
 **/
@Slf4j
@Service
public class BreedingFarmerServiceImpl implements BreedingFarmerService {
    /**
     * 农户端app 首页
     *
     * @return
     */
    @Override
    public ReturnBreedingFarmerIndexDto breedingFarmerIndex() {
        return null;
    }
}
