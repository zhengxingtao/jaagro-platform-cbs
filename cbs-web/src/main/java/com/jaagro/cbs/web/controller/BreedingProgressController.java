package com.jaagro.cbs.web.controller;

import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.dto.progress.ListBatchCriteriaDto;
import com.jaagro.cbs.api.service.BreedingProgressService;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(description = "养殖过程管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreedingProgressController {

    @Autowired
    private BreedingProgressService progressService;

    /**
     * 批次管理列表
     *
     * @param criteriaDto
     * @return
     */
    @ApiOperation("养殖场-通过养殖户id获得养殖场列表")
    @GetMapping("/listPlanByCriteria")
    public BaseResponse<List<ReturnBreedingPlanDto>> listPlanByDto(@RequestBody ListBatchCriteriaDto criteriaDto) {
        if (StringUtils.isEmpty(criteriaDto.getPageNum())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "pageNum不能为空");
        }
        if (StringUtils.isEmpty(criteriaDto.getPageSize())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "pageSize不能为空");
        }
        return BaseResponse.successInstance(progressService.listPlanByCriteria(criteriaDto));
    }

}
