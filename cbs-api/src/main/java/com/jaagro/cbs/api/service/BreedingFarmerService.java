package com.jaagro.cbs.api.service;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.farmer.BreedingBatchParamDto;
import com.jaagro.cbs.api.dto.farmer.CreateTechnicalInquiriesDto;
import com.jaagro.cbs.api.dto.farmer.ReturnBreedingFarmerIndexDto;


/**
 * 农户端app 相关api
 *
 * @author @Gao.
 */
public interface BreedingFarmerService {

    /**
     * 农户端app 首页数据统计
     *
     * @return
     * @author @Gao.
     */
    ReturnBreedingFarmerIndexDto breedingFarmerIndexStatistical();

    /**
     * 农户端app 首页
     *
     * @return
     * @author @Gao.
     */
    PageInfo breedingFarmerIndex(BreedingBatchParamDto dto);

    /**
     * 技术询问
     *
     * @param dto
     */
    void technicalInquiries(CreateTechnicalInquiriesDto dto);
}
