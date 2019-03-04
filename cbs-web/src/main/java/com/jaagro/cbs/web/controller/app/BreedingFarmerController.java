package com.jaagro.cbs.web.controller.app;

import com.jaagro.cbs.api.service.BreedingFarmerService;
import com.jaagro.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @Gao.
 */
@RestController
@Api(description = "农户端养殖管理", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class BreedingFarmerController {
    @Autowired
    private BreedingFarmerService breedingFarmerService;


    @PostMapping("/breedingFarmerIndexStatistical")
    @ApiOperation("农户端首页数据统计")
    public BaseResponse breedingFarmerIndexStatistical() {
        return BaseResponse.successInstance(breedingFarmerService.breedingFarmerIndexStatistical());
    }

    @PostMapping("/breedingFarmerIndex")
    @ApiOperation("农户端首页列表")
    public BaseResponse breedingFarmerIndex() {
        return BaseResponse.successInstance(breedingFarmerService.breedingFarmerIndex());
    }
}
