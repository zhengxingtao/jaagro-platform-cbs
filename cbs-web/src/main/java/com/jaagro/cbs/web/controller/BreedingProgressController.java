package com.jaagro.cbs.web.controller;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.plan.BreedingPlanParamDto;
import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.dto.progress.BreedingBatchParamTrackingDto;
import com.jaagro.cbs.api.dto.progress.BreedingProgressDto;
import com.jaagro.cbs.api.dto.progress.BreedingRecordDto;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.api.service.BreedingProgressService;
import com.jaagro.cbs.web.vo.plan.BreedingPlanBatchVo;
import com.jaagro.cbs.web.vo.progress.BreedingProgressParamVo;
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
@Api(description = "养殖过程管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreedingProgressController {

    @Autowired
    private BreedingPlanService breedingPlanService;

    @Autowired
    private BreedingProgressService breedingProgressService;

    /**
     * 养殖场-通过养殖户id获得养殖场列表
     *
     * @return
     * @throws
     * @author baiyiran
     * @date 2019/2/26
     */
    @ApiOperation("养殖场-通过养殖户id获得养殖场列表")
    @PostMapping("/listPlanByCriteria")
    public BaseResponse<PageInfo> listPlanByDto(@RequestBody BreedingPlanParamDto criteriaDto) {
        if (StringUtils.isEmpty(criteriaDto.getPageNum())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "pageNum不能为空");
        }
        if (StringUtils.isEmpty(criteriaDto.getPageSize())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "pageSize不能为空");
        }
        PageInfo pageInfo = breedingPlanService.listBreedingPlan(criteriaDto);
        if (pageInfo != null) {
            List<BreedingPlanBatchVo> voList = new ArrayList<>();
            List<ReturnBreedingPlanDto> dtoList = pageInfo.getList();
            if (!CollectionUtils.isEmpty(dtoList)) {
                for (ReturnBreedingPlanDto planDto : dtoList) {
                    BreedingPlanBatchVo batchVo = new BreedingPlanBatchVo();
                    BeanUtils.copyProperties(planDto, batchVo);
                    voList.add(batchVo);
                }
            }
            pageInfo.setList(voList);
        }
        return BaseResponse.successInstance(pageInfo);
    }

    /**
     *  养殖过程管理-根据养殖计划Id、养殖厂Id获取养殖过程头信息
     * @param paramVo
     * @author gavin
     * @return
     */
    @ApiOperation("根据养殖计划Id、养殖厂Id获取养殖过程头信息")
    @PostMapping("/getBreedingProgressHeader")
    public BaseResponse getBreedingProgressById(@RequestBody BreedingProgressParamVo paramVo) {

        Assert.notNull(paramVo.getPlanId(),"养殖计划id不能为空");
        Assert.notNull(paramVo.getPlantId(),"养殖厂id不能为空");
        BreedingProgressDto breedingProgressDto;
        try {
            breedingProgressDto= breedingProgressService.getBreedingProgressById(paramVo.getPlanId(),paramVo.getPlantId());

        } catch (Exception ex) {
            return BaseResponse.errorInstance("获取养殖过程头信息：" + ex.getMessage());
        }
        return BaseResponse.successInstance(breedingProgressDto);
    }

    /**
     * 获取养殖过程中某个鸡舍每天养殖参数详情
     * @param paramVo
     * @author gavin
     * @return
     */
    @ApiOperation("获取鸡舍某天养殖参数详情")
    @PostMapping("/getBreedingBatchParam")
    public BaseResponse getBreedingBatchParamById(@RequestBody BreedingProgressParamVo paramVo) {

        Assert.notNull(paramVo.getPlanId(),"养殖计划id不能为空");
        Assert.notNull(paramVo.getCoopId(),"鸡舍id不能为空");
        Assert.notNull(paramVo.getDayAge(),"日龄不能为空");
        Assert.notNull(paramVo.getStrDate(),"日龄对应的日期不能为空");

        List<BreedingBatchParamTrackingDto> paramTrackingDtos;
        Integer planId = paramVo.getPlanId();
        Integer coopId = paramVo.getCoopId();
        Integer dayAge = paramVo.getDayAge();
        String strDate = paramVo.getStrDate();
        try {
            paramTrackingDtos= breedingProgressService.getBreedingBatchParamById(planId,coopId,dayAge,strDate);

        } catch (Exception ex) {
            return BaseResponse.errorInstance("获取每天养殖参数详情失败：" + ex.getMessage());
        }
        return BaseResponse.successInstance(paramTrackingDtos);
    }
    /**
     * 获取养殖过程中某天养殖喂养情况
     * @param paramVo
     * @author gavin
     * @return
     */
    @ApiOperation("获取养殖过程中某天养殖喂养情况")
    @PostMapping("/getBreedingRecordsById")
    public BaseResponse getBreedingRecordsById(@RequestBody BreedingProgressParamVo paramVo) {

        Assert.notNull(paramVo.getPlanId(),"养殖计划id不能为空");
        Assert.notNull(paramVo.getCoopId(),"鸡舍id不能为空");
        Assert.notNull(paramVo.getDayAge(),"日龄不能为空");

        BreedingRecordDto breedingRecordDto;
        Integer planId = paramVo.getPlanId();
        Integer coopId = paramVo.getCoopId();
        Integer dayAge = paramVo.getDayAge();
        try {
            breedingRecordDto= breedingProgressService.getBreedingRecordsById(planId,coopId,dayAge);

        } catch (Exception ex) {
            return BaseResponse.errorInstance("获取某天养殖喂养情况失败：" + ex.getMessage());
        }
        return BaseResponse.successInstance(breedingRecordDto);
    }
}
