package com.jaagro.cbs.web.controller.app;

import com.jaagro.cbs.api.dto.farmer.*;
import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;
import com.jaagro.cbs.api.dto.progress.BreedingRecordDto;
import com.jaagro.cbs.api.service.BreedingFarmerService;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.api.service.BreedingProgressService;
import com.jaagro.cbs.web.vo.progress.BreedingProgressParamVo;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author @Gao.
 */
@RestController
@Api(description = "农户端养殖管理", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class BreedingFarmerController {
    @Autowired
    private BreedingFarmerService breedingFarmerService;
    @Autowired
    private BreedingPlanService breedingPlanService;
    @Autowired
    private BreedingProgressService breedingProgressService;


    @GetMapping("/breedingFarmerIndexStatistical")
    @ApiOperation("农户端首页数据统计")
    public BaseResponse breedingFarmerIndexStatistical() {
        return BaseResponse.successInstance(breedingFarmerService.breedingFarmerIndexStatistical());
    }

    @PostMapping("/breedingFarmerIndex")
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

    @PostMapping("/publishedChickenPlan")
    @ApiOperation("发布上鸡计划")
    public BaseResponse publishedChickenPlan(@RequestBody CreateBreedingPlanDto dto) {
        if (CollectionUtils.isEmpty(dto.getPlantIds())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场不能为空");
        }
        if (dto.getCustomerId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "客户不能为空");
        }
        breedingPlanService.createBreedingPlan(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

    @PostMapping("/technicalInquiries")
    @ApiOperation("新增技术询问")
    public BaseResponse technicalInquiries(@RequestBody CreateTechnicalInquiriesDto dto) {
        if (dto.getPlanId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖计划id不能为空");
        }
        if (dto.getBatchNo() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖计划批次号不能为空");
        }
        breedingFarmerService.technicalInquiries(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

    /**
     * 批次详情
     * @author yj
     * @param planId
     * @return
     */
    @GetMapping("/getBatchDetail/{planId}")
    @ApiOperation("批次详情")
    public BaseResponse getBatchDetail(@PathVariable("planId") Integer planId){
        log.info("O getBatchDetail planId={}",planId);
        BreedingPlanDetailDto breedingPlanDetailDto = breedingPlanService.getBatchDetail(planId);
        if (breedingPlanDetailDto != null){
            return BaseResponse.successInstance(breedingPlanDetailDto);
        }
        return BaseResponse.queryDataEmpty();
    }

    /**
     * 查询鸡舍养殖过程批次参数
     * @author yj
     * @param paramVo
     * @return
     */
    @PostMapping("/getBreedingBatchParamForApp")
    @ApiOperation("查询鸡舍养殖过程批次参数")
    public BaseResponse getBreedingBatchParamForApp(@RequestBody BreedingProgressParamVo paramVo){
        log.info("O getBreedingBatchParamForApp paramVo={}",paramVo);
        Assert.notNull(paramVo.getPlanId(),"养殖计划id不能为空");
        Assert.notNull(paramVo.getCoopId(),"鸡舍id不能为空");
        Integer planId = paramVo.getPlanId();
        Integer coopId = paramVo.getCoopId();
        Integer dayAge = paramVo.getDayAge();
        String strDate = paramVo.getStrDate();
        try {
            BreedingBatchParamListDto breedingBatchParamListDto = breedingPlanService.getBreedingBatchParamForApp(planId,coopId,dayAge,strDate);
            return BaseResponse.successInstance(breedingBatchParamListDto);
        }catch (Exception ex){
            log.error("O getBreedingBatchParamForApp error paramVo="+paramVo,ex);
            return BaseResponse.errorInstance("查询鸡舍养殖过程批次参数异常");
        }
    }

    /**
     * 查询批次养殖记录
     * @author yj
     * @param planId
     * @param coopId
     * @return
     */
    @ApiOperation("查询批次养殖记录")
    @GetMapping("/listBatchBreedingRecord")
    public BaseResponse listBatchBreedingRecord(@RequestParam Integer planId,@RequestParam Integer coopId){
        log.info("O listBatchBreedingRecord planId={},coopId={}",planId,coopId);
        BreedingRecordDto breedingRecordDto;
        try {
            long dayAge = breedingPlanService.getDayAge(planId);
            breedingRecordDto= breedingProgressService.getBreedingRecordsById(planId,coopId,(int)dayAge);
        }catch (Exception ex){
            log.error("O listBatchBreedingRecord error planId="+planId+",coopId="+coopId,ex);
            return BaseResponse.errorInstance("查询批次养殖记录异常");
        }
        return BaseResponse.successInstance(breedingRecordDto);
    }

    @ApiOperation("上传养殖记录")
    @PostMapping("/uploadBreedingRecord")
    public BaseResponse uploadBreedingRecord(@RequestBody @Validated CreateBreedingRecordDto createBreedingRecordDto){
        return null;
    }
}
