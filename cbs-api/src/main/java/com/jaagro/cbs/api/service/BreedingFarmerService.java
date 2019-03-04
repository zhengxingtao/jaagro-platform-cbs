package com.jaagro.cbs.api.service;

import com.jaagro.cbs.api.dto.farmer.ReturnBreedingFarmerIndexDto;

import java.util.List;

/**
 * 农户端app 相关api
 *
 * @author @Gao.
 */
public interface BreedingFarmerService {

    /**
     * 农户端app 首页
     * @return
     */
    ReturnBreedingFarmerIndexDto breedingFarmerIndex();
}
