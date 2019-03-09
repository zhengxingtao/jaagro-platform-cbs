package com.jaagro.cbs.web.controller.app;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.base.GetCustomerUserDto;
import com.jaagro.cbs.api.dto.farmer.BreedingBatchParamDto;
import com.jaagro.cbs.api.dto.farmer.BreedingPlanDetailDto;
import com.jaagro.cbs.api.dto.farmer.CreateTechnicalInquiriesDto;
import com.jaagro.cbs.api.dto.message.MessageCriteriaDto;
import com.jaagro.cbs.api.dto.order.PurchaseOrderListParamDto;
import com.jaagro.cbs.api.dto.order.UpdatePurchaseOrderParamDto;
import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;
import com.jaagro.cbs.api.dto.plant.ReturnPlantDto;
import com.jaagro.cbs.api.enums.EmergencyLevelEnum;
import com.jaagro.cbs.api.enums.UserTypeEnum;
import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.model.Message;
import com.jaagro.cbs.api.model.TechConsultRecord;
import com.jaagro.cbs.api.service.BreedingFarmerService;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.api.service.BreedingPlantService;
import com.jaagro.cbs.biz.service.UserClientService;
import com.jaagro.cbs.biz.service.impl.CurrentUserService;
import com.jaagro.cbs.biz.service.impl.MessageServiceImpl;
import com.jaagro.cbs.web.vo.message.FarmerMessageVo;
import com.jaagro.cbs.web.vo.plan.PublishedChickenPlanVo;
import com.jaagro.cbs.web.vo.techconsult.TechnicalInquiriesVo;
import com.jaagro.constant.UserInfo;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    private UserClientService userClientService;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private BreedingPlantService breedingPlantService;
    @Autowired
    private MessageServiceImpl messageService;


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
        if (dto.getPlantId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场id不能为空");
        }
        if (dto.getCoopId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "鸡舍id不能为空");
        }
        breedingFarmerService.technicalInquiries(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

    @GetMapping("/farmerPersonalCenter")
    @ApiOperation("农户端个人中心")
    public BaseResponse farmerPersonalCenter() {
        return BaseResponse.successInstance(breedingFarmerService.farmerPersonalCenter());
    }

    @PostMapping("/listPurchaseOrder")
    @ApiOperation("商品采购列表")
    public BaseResponse listPurchaseOrder(@RequestBody PurchaseOrderListParamDto dto) {
        return BaseResponse.successInstance(breedingFarmerService.listPurchaseOrder(dto));
    }

    @GetMapping("/purchaseOrderDetails/{purchaseOrderId}")
    @ApiOperation("采购订单详情")
    public BaseResponse purchaseOrderDetails(@PathVariable("purchaseOrderId") Integer purchaseOrderId) {
        if (purchaseOrderId == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "采购订单id详情");
        }
        return BaseResponse.successInstance(breedingFarmerService.purchaseOrderDetails(purchaseOrderId));
    }

    @PostMapping("/updatePurchaseOrder")
    @ApiOperation("更新采购订单状态")
    public BaseResponse updatePurchaseOrder(@RequestBody UpdatePurchaseOrderParamDto dto) {
        if (dto.getPurchaseOrderId() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "采购订单id详情");
        }
        if (dto.getPurchaseOrderStatus() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "采购订单状态不能为空");
        }
        breedingFarmerService.updatePurchaseOrder(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

    @PostMapping("/listPublishedChickenPlan")
    @ApiOperation("上鸡计划列表")
    public BaseResponse listPublishedChickenPlan(@RequestBody BreedingBatchParamDto dto) {
        if (dto.getPageNum() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "起始页不能为空");
        }
        if (dto.getPageSize() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "每页条数不能为空");
        }
        PageInfo pageInfo = breedingFarmerService.listPublishedChickenPlan(dto);
        List<PublishedChickenPlanVo> publishedChickenPlanVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            List<BreedingPlan> breedingPlans = pageInfo.getList();
            for (BreedingPlan breedingPlan : breedingPlans) {
                PublishedChickenPlanVo publishedChickenPlanVo = new PublishedChickenPlanVo();
                BeanUtils.copyProperties(breedingPlan, publishedChickenPlanVo);
                publishedChickenPlanVos.add(publishedChickenPlanVo);
            }
        }
        pageInfo.setList(publishedChickenPlanVos);
        return BaseResponse.successInstance(pageInfo);
    }

    @PostMapping("/listTechnicalInquiries")
    @ApiOperation("技术询问列表")
    public BaseResponse listTechnicalInquiries(@RequestBody BreedingBatchParamDto dto) {
        if (dto.getPageNum() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "起始页不能为空");
        }
        if (dto.getPageSize() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "每页条数不能为空");
        }
        PageInfo pageInfo = breedingFarmerService.listTechnicalInquiries(dto);
        List<TechnicalInquiriesVo> technicalInquiriesVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            List<TechConsultRecord> techConsultRecords = pageInfo.getList();
            for (TechConsultRecord techConsultRecord : techConsultRecords) {
                StringBuilder sb = new StringBuilder();
                TechnicalInquiriesVo technicalInquiriesVo = new TechnicalInquiriesVo();
                BeanUtils.copyProperties(techConsultRecord, technicalInquiriesVo);
                if (techConsultRecord.getHandleUserId() != null) {
                    BaseResponse<UserInfo> globalUser = userClientService.getGlobalUser(techConsultRecord.getHandleUserId());
                    if (globalUser != null && globalUser.getData() != null) {
                        UserInfo userInfo = globalUser.getData();
                        technicalInquiriesVo
                                .setHandlePhone(userInfo.getPhoneNumber())
                                .setHandleUser(userInfo.getName());
                    }
                }
                if (techConsultRecord.getPlantId() != null) {
                    ReturnPlantDto plant = breedingPlantService.getPlantDetailsById(techConsultRecord.getPlantId());
                    if (plant != null && plant.getProvince() != null) {
                        sb.append(plant.getProvince());
                    }
                    if (plant != null && plant.getCity() != null) {
                        sb.append(plant.getCity());
                    }
                    if (plant != null && plant.getCounty() != null) {
                        sb.append(plant.getCounty());
                    }
                }
                technicalInquiriesVo
                        .setAddress(sb.toString());
                technicalInquiriesVo
                        .setEmergencyLevel(EmergencyLevelEnum.getDescByCode(techConsultRecord.getEmergencyLevel()));
                technicalInquiriesVos.add(technicalInquiriesVo);
            }
        }
        pageInfo.setList(technicalInquiriesVos);
        return BaseResponse.successInstance(technicalInquiriesVos);
    }

    @PostMapping("/listFarmerMessage")
    @ApiOperation("农户端消息列表")
    public BaseResponse listFarmerMessage(@RequestBody BreedingBatchParamDto dto) {
        if (dto.getPageNum() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "起始页不能为空");
        }
        if (dto.getPageSize() == null) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "每页条数不能为空");
        }
        List<FarmerMessageVo> farmerMessageVos = new ArrayList<>();
        UserInfo currentUser = currentUserService.getCurrentUser();
        MessageCriteriaDto messageCriteriaDto = new MessageCriteriaDto();
        if (currentUser != null && currentUser.getId() != null) {
            GetCustomerUserDto customerUser = userClientService.getCustomerUserById(currentUser.getId());
            if (customerUser != null && customerUser.getRelevanceId() != null) {
                messageCriteriaDto
                        .setPageNum(dto.getPageNum())
                        .setPageSize(dto.getPageSize())
                        .setToUserType(UserTypeEnum.CUSTOMER.getCode())
                        .setToUserId(customerUser.getRelevanceId());
            }
        }
        PageInfo pageInfo = messageService.listMessageByCriteria(messageCriteriaDto);
        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            List<Message> messages = pageInfo.getList();
            for (Message message : messages) {
                FarmerMessageVo farmerMessageVo = new FarmerMessageVo();
                BeanUtils.copyProperties(message, farmerMessageVo);
                farmerMessageVos.add(farmerMessageVo);
            }
        }
        pageInfo.setList(farmerMessageVos);
        return BaseResponse.successInstance(pageInfo);
    }

    /**
     * 批次详情
     *
     * @param planId
     * @return
     * @author yj
     */
    @PostMapping("/getBatchDetail/{planId}")
    @ApiOperation("批次详情")
    public BaseResponse getBatchDetail(@PathVariable("planId") Integer planId) {
        log.info("O getBatchDetail planId={}", planId);
        BreedingPlanDetailDto breedingPlanDetailDto = breedingPlanService.getBatchDetail(planId);
        if (breedingPlanDetailDto != null) {
            return BaseResponse.successInstance(breedingPlanDetailDto);
        }
        return BaseResponse.queryDataEmpty();
    }
}
