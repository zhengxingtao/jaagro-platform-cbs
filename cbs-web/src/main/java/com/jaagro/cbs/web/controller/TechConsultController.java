package com.jaagro.cbs.web.controller;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.plan.BreedingPlanParamDto;
import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.dto.progress.BreedingBatchParamTrackingDto;
import com.jaagro.cbs.api.dto.progress.BreedingProgressDto;
import com.jaagro.cbs.api.dto.progress.BreedingRecordDto;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.api.service.BreedingProgressService;
import com.jaagro.cbs.api.service.TechConsultService;
import com.jaagro.cbs.web.vo.plan.BreedingPlanBatchVo;
import com.jaagro.cbs.web.vo.progress.BreedingProgressParamVo;
import com.jaagro.cbs.web.vo.techConsult.TechConsultParamDto;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Api(description = "技术询问管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class TechConsultController {

    @Autowired
    private TechConsultService techConsultService;

    /**
     * 获取技术询问列表
     *
     * @return
     * @throws
     * @author gavin
     * @date 2019/2/28
     */
    @ApiOperation("获取技术询问列表")
    @PostMapping("/listTechConsult")
    public BaseResponse<PageInfo> listTechConsult(@RequestBody TechConsultParamDto criteriaDto) {
        if (StringUtils.isEmpty(criteriaDto.getPageNum())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "pageNum不能为空");
        }
        if (StringUtils.isEmpty(criteriaDto.getPageSize())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "pageSize不能为空");
        }
//        PageInfo pageInfo = breedingPlanService.listBreedingPlan(criteriaDto);
//        if (pageInfo != null) {
//            List<BreedingPlanBatchVo> voList = new ArrayList<>();
//            List<ReturnBreedingPlanDto> dtoList = pageInfo.getList();
//            if (!CollectionUtils.isEmpty(dtoList)) {
//                for (ReturnBreedingPlanDto planDto : dtoList) {
//                    BreedingPlanBatchVo batchVo = new BreedingPlanBatchVo();
//                    BeanUtils.copyProperties(planDto, batchVo);
//                    voList.add(batchVo);
//                }
//            }
//            pageInfo.setList(voList);
//        }
//        return BaseResponse.successInstance(pageInfo);
        return null;
    }
}
