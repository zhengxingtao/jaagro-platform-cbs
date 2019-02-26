package com.jaagro.cbs.web.controller;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.plan.BreedingPlanParamDto;
import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;
import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.dto.plan.UpdateBreedingPlanDto;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.web.vo.plan.BreedingPlanVo;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author @Gao.
 */
@RestController
@Api(description = "养殖计划管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreedingPlanController {
    @Autowired
    private BreedingPlanService breedingPlanService;

    @PostMapping("/createBreedingPlan")
    @ApiOperation("创建养殖计划")
    public BaseResponse createBreedingPlan(@RequestBody CreateBreedingPlanDto dto) {
        if (CollectionUtils.isEmpty(dto.getPlantIds())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场不能为空");
        }
        if (dto.getCustomerId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "客户不能为空");
        }
        if (dto.getTechnicianId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "技术员不能为空");
        }
        breedingPlanService.createBreedingPlan(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

    @PostMapping("/listBreedingPlan")
    @ApiOperation("养殖计划列表")
    public BaseResponse listBreedingPlan(@RequestBody BreedingPlanParamDto dto) {
        if (dto.getPageNum() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "起始页不能为空");
        }
        if (dto.getPageSize() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "每页条数不能为空");
        }
        PageInfo pageInfo = breedingPlanService.listBreedingPlan(dto);
        List<BreedingPlanVo> breedingPlanVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            List<ReturnBreedingPlanDto> ReturnBreedingPlanDtos = pageInfo.getList();
            for (ReturnBreedingPlanDto returnBreedingPlanDto : ReturnBreedingPlanDtos) {
                BreedingPlanVo breedingPlanVo = new BreedingPlanVo();
                BeanUtils.copyProperties(returnBreedingPlanDto, breedingPlanVo);
                breedingPlanVos.add(breedingPlanVo);
            }
        }
        pageInfo.setList(breedingPlanVos);
        return BaseResponse.successInstance(pageInfo);
    }

    @PostMapping("/upDateBreedingPlanDetails")
    @ApiOperation("养殖计划列表")
    public BaseResponse upDateBreedingPlanDetails(@RequestBody UpdateBreedingPlanDto dto) {
        if (dto.getId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖计划id不能为空");
        }
        breedingPlanService.upDateBreedingPlanDetails(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

}
