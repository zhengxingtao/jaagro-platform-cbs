package com.jaagro.cbs.web.controller.app;

import com.jaagro.cbs.api.dto.farmer.BreedingBatchParamDto;
import com.jaagro.cbs.api.service.BreedingFarmerService;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @GetMapping("/breedingFarmerIndexStatistical")
    @ApiOperation("农户端首页数据统计")
    public BaseResponse breedingFarmerIndexStatistical() {
        return BaseResponse.successInstance(breedingFarmerService.breedingFarmerIndexStatistical());
    }

    @GetMapping("/breedingFarmerIndex")
    @ApiOperation("农户端首页列表")
    public BaseResponse breedingFarmerIndex(@RequestBody BreedingBatchParamDto dto) {
        if (dto.getPageNum() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "起始页不能为空");
        }
        if (dto.getPageSize() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "每页条数不能为空");
        }
        return BaseResponse.successInstance(breedingFarmerService.breedingFarmerIndex(dto));
    }
}
