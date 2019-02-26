package com.jaagro.cbs.web.controller;

import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;
import com.jaagro.cbs.api.dto.plan.CreatePlanContractDto;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @Gao.
 */
@RestController
@Api(description = "养殖计划管理", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class BreedingPlanController {
    @Autowired
    private BreedingPlanService breedingPlanService;

    @PostMapping("/createBreedingPlan")
    @ApiOperation("创建养殖计划")
    public BaseResponse createBreedingPlan(@RequestBody CreateBreedingPlanDto dto) {
        if (CollectionUtils.isEmpty(dto.getPlantsId())) {
            BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场不能为空");
        }
        if (dto.getCustomerId() == null) {
            BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "客户不能为空");
        }
        if (dto.getTechnicianId() == null) {
            BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "技术员不能为空");
        }
        breedingPlanService.createBreedingPlan(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

    @PostMapping("/createPlanContract")
    @ApiOperation("录入合同")
    public BaseResponse createPlanContract(@RequestBody @Validated  CreatePlanContractDto createPlanContractDto){
        log.info("O createPlanContract createPlanContractDto={}",createPlanContractDto);
        breedingPlanService.createPlanContract(createPlanContractDto);
        return BaseResponse.successInstance("录入合同成功");
    }
}
