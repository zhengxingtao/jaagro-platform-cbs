package com.jaagro.cbs.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.constant.CertificateStatus;
import com.jaagro.cbs.api.constant.ContractStatus;
import com.jaagro.cbs.api.dto.base.CustomerContactsReturnDto;
import com.jaagro.cbs.api.dto.plan.*;
import com.jaagro.cbs.api.dto.base.Employee;
import com.jaagro.cbs.api.dto.plan.BreedingPlanParamDto;
import com.jaagro.cbs.api.dto.plan.CreateBreedingPlanDto;
import com.jaagro.cbs.api.dto.plan.ReturnBreedingPlanDto;
import com.jaagro.cbs.api.dto.plan.UpdateBreedingPlanDto;
import com.jaagro.cbs.api.dto.standard.BreedingParameterDto;
import com.jaagro.cbs.api.enums.*;
import com.jaagro.cbs.api.model.*;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.biz.mapper.*;
import com.jaagro.cbs.biz.service.CustomerClientService;
import com.jaagro.cbs.biz.service.UserClientService;
import com.jaagro.cbs.biz.utils.DateUtil;
import com.jaagro.cbs.biz.utils.MathUtil;
import com.jaagro.cbs.biz.utils.SequenceCodeUtils;
import com.jaagro.constant.UserInfo;
import com.jaagro.utils.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 养殖计划管理
 *
 * @author @Gao.
 */
@Slf4j
@Service
public class BreedingPlanServiceImpl implements BreedingPlanService {

    @Autowired
    private SequenceCodeUtils sequenceCodeUtils;
    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private BatchPlantCoopMapperExt batchPlantCoopMapper;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private BatchContractMapperExt batchContractMapper;
    @Autowired
    private ContractSourceMapperExt contractSourceMapper;
    @Autowired
    private ContractPriceSectionMapperExt contractPriceSectionMapper;
    @Autowired
    private CustomerClientService customerClientService;
    @Autowired
    private BreedingBatchParameterMapperExt breedingBatchParameterMapper;
    @Autowired
    private UserClientService userClientService;
    @Autowired
    private BreedingPlantServiceImpl breedingPlantService;
    @Autowired
    private PurchaseOrderMapperExt purchaseOrderMapper;
    @Autowired
    private BatchInfoMapperExt batchInfoMapper;
    @Autowired
    private DateUtil dateUtil;
    @Autowired
    private MathUtil mathUtil;
    @Autowired
    private CoopMapperExt coopMapper;
    @Autowired
    private DeviceAlarmLogMapperExt deviceAlarmLogMapper;
    @Autowired
    private TechConsultRecordMapperExt techConsultRecordMapper;
    @Autowired
    private BreedingStandardMapperExt breedingStandardMapper;

    /**
     * 创建养殖计划
     *
     * @param dto
     * @author @Gao.
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBreedingPlan(CreateBreedingPlanDto dto) {
        UserInfo currentUser = currentUserService.getCurrentUser();
        String batchNo = sequenceCodeUtils.genSeqCode("AT");
        BreedingPlan breedingPlan = new BreedingPlan();
        breedingPlan
                .setBatchNo(batchNo)
                .setCreateUserId(currentUser.getId())
                .setCreateUserName(currentUser.getName())
                .setPlanStatus(PlanStatusEnum.ENTER_CONTRACT.getCode());
        BeanUtils.copyProperties(dto, breedingPlan);
        //插入养殖计划
        breedingPlanMapper.insertSelective(breedingPlan);
        //插入养殖关联表
        if (!CollectionUtils.isEmpty(dto.getPlantIds())) {
            List<BatchPlantCoop> batchPlantCoops = new ArrayList<>();
            List<Integer> plantIds = dto.getPlantIds();
            for (Integer plantId : plantIds) {
                BatchPlantCoop batchPlantCoop = new BatchPlantCoop();
                batchPlantCoop
                        .setPlanId(breedingPlan.getId())
                        .setCreateUserId(currentUser.getId())
                        .setPlantId(plantId);
                batchPlantCoops.add(batchPlantCoop);
            }
            batchPlantCoopMapper.insertBatch(batchPlantCoops);
        }
    }

    /**
     * 养殖计划列表 批次列表 养殖列表（复用）
     * (返回数据类型相同但计划状态不同 用flag区分
     * flag=1表示养殖计划列表数据
     * flag=2 表示批次列表数据）
     *
     * @param dto
     * @return
     * @author @Gao.
     */
    @Override
    public PageInfo listBreedingPlan(BreedingPlanParamDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
//        dto.setTenantId();    先注释,等待从userInfo获取租户id
        if (dto.getCustomerInfo() != null) {
            BaseResponse<List<Integer>> listBaseResponse = customerClientService.listCustomerIdByKeyWord(dto.getCustomerInfo());
            if (!CollectionUtils.isEmpty(listBaseResponse.getData())) {
                List<Integer> customerIds = listBaseResponse.getData();
                dto.setCustomerIds(customerIds);
            }
        }
        List<ReturnBreedingPlanDto> planDtoList = breedingPlanMapper.listBreedingPlan(dto);
        for (ReturnBreedingPlanDto returnBreedingPlanDto : planDtoList) {
            //填充养殖户信息
            CustomerContactsReturnDto contactsReturnDto = customerClientService.getCustomerContactByCustomerId(returnBreedingPlanDto.getCustomerId());
            if (contactsReturnDto != null) {
                returnBreedingPlanDto
                        .setCustomerName(contactsReturnDto.getContact())
                        .setCustomerPhone(contactsReturnDto.getPhone());
            }
            //填充养殖场信息
            List<Plant> plants = breedingPlantService.listPlantInfoByPlanId(returnBreedingPlanDto.getId());
            returnBreedingPlanDto.setPlants(plants);
            //填充进度
            BreedingBatchParameterExample parameterExample = new BreedingBatchParameterExample();
            parameterExample.createCriteria()
                    .andEnableEqualTo(true)
                    .andPlanIdEqualTo(returnBreedingPlanDto.getId())
                    .andBatchNoEqualTo(returnBreedingPlanDto.getBatchNo());
            List<BreedingBatchParameter> breedingBatchParameters = breedingBatchParameterMapper.selectByExample(parameterExample);
            if (!CollectionUtils.isEmpty(breedingBatchParameters)) {
                BreedingBatchParameter parameter = breedingBatchParameters.get(0);
                returnBreedingPlanDto.setAllDayAge(parameter.getDayAge());
                try {
                    long day = getInterval(returnBreedingPlanDto.getPlanTime(), new Date());
                    returnBreedingPlanDto.setAlreadyDayAge((int) day);
                } catch (Exception ex) {
                    log.error("计算批次进度失败:BreedingPlanServiceImpl.getInterval()");
                    ex.printStackTrace();
                }
            }
        }
        return new PageInfo(planDtoList);
    }


    /**
     * 计算上鸡时间进度
     *
     * @param beginDate
     * @param endDate
     * @return
     * @throws Exception
     */
    private long getInterval(Date beginDate, Date endDate) throws Exception {
        long day = 0;
        if (beginDate == null && endDate == null) {
            return day;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        beginDate = sdf.parse(sdf.format(beginDate));
        endDate = sdf.parse(sdf.format(endDate));
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 录入合同
     *
     * @param createPlanContractDto
     * @author yj
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPlanContract(CreatePlanContractDto createPlanContractDto) {
        Integer planId = createPlanContractDto.getPlanId();
        BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
        if (breedingPlan == null) {
            throw new RuntimeException("养殖计划id=" + planId + "不存在");
        }
        UserInfo currentUser = currentUserService.getCurrentUser();
        Integer currentUserId = currentUser == null ? null : currentUser.getId();
        // 插入计划合同
        BatchContract batchContract = new BatchContract();
        BeanUtils.copyProperties(createPlanContractDto, batchContract);
        batchContract.setContractNumber(sequenceCodeUtils.genSeqCode("HT"))
                .setContractDate(new Date())
                .setCreateTime(new Date())
                .setContractStatus(ContractStatus.UNAUDITED)
                .setCreateUserId(currentUserId)
                .setCustomerId(breedingPlan.getCustomerId())
                .setEnable(true);
        batchContractMapper.insertSelective(batchContract);
        // 插入计划合同图片
        if (!CollectionUtils.isEmpty(createPlanContractDto.getImageUrlList())) {
            List<ContractSource> contractSourceList = new ArrayList<>();
            for (String imageUrl : createPlanContractDto.getImageUrlList()) {
                ContractSource contractSource = new ContractSource();
                contractSource.setSourceUrl(imageUrl)
                        .setSourceStatus(CertificateStatus.UNCHECKED)
                        .setCreateTime(new Date())
                        .setCreateUserId(currentUserId)
                        .setEnable(true)
                        .setPlanId(createPlanContractDto.getPlanId())
                        .setPlanContractId(batchContract.getId());
                contractSourceList.add(contractSource);
            }
            contractSourceMapper.batchInsert(contractSourceList);
        }
        // 插入回收价格区间
        List<ContractPriceSectionDto> contractPriceSectionDtoList = createPlanContractDto.getContractPriceSectionDtoList();
        if (!CollectionUtils.isEmpty(contractPriceSectionDtoList)) {
            List<ContractPriceSection> contractPriceSectionList = new ArrayList<>();
            for (ContractPriceSectionDto dto : contractPriceSectionDtoList) {
                ContractPriceSection contractPriceSection = new ContractPriceSection();
                BeanUtils.copyProperties(dto, contractPriceSection);
                contractPriceSection.setContractId(batchContract.getId())
                        .setCreateTime(new Date())
                        .setCreateUserId(currentUserId)
                        .setEnable(true)
                        .setPlanId(createPlanContractDto.getPlanId());
                contractPriceSectionList.add(contractPriceSection);
            }
            contractPriceSectionMapper.batchInsert(contractPriceSectionList);
        }
        // 更新养殖计划状态
        breedingPlan.setModifyTime(new Date())
                .setModifyUserId(currentUserId)
                .setPlanStatus(PlanStatusEnum.PARAM_CORRECT.getCode());
        breedingPlanMapper.updateByPrimaryKeySelective(breedingPlan);
    }

    /**
     * 更新养殖计划
     *
     * @param dto
     * @author @Gao.
     */
    @Override
    public void upDateBreedingPlanDetails(UpdateBreedingPlanDto dto) {
        BreedingPlan breedingPlan = new BreedingPlan();
        BeanUtils.copyProperties(dto, breedingPlan);
        breedingPlanMapper.updateByPrimaryKeySelective(breedingPlan);
    }

    /**
     * 养殖计划列表详情
     *
     * @param planId
     * @return
     * @author @Gao.
     */
    @Override
    public ReturnBreedingPlanDetailsDto breedingPlanDetails(Integer planId) {
        ReturnBreedingPlanDetailsDto returnBreedingPlanDto = new ReturnBreedingPlanDetailsDto();
        //养殖计划信息
        BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
        BeanUtils.copyProperties(breedingPlan, returnBreedingPlanDto);
        //养殖场信息
        List<Plant> plants = breedingPlantService.listPlantInfoByPlanId(returnBreedingPlanDto.getId());
        returnBreedingPlanDto.setPlants(plants);
        //养殖户信息
        CustomerContactsReturnDto contactsReturnDto = customerClientService.getCustomerContactByCustomerId(breedingPlan.getCustomerId());
        if (contactsReturnDto != null) {
            returnBreedingPlanDto
                    .setCustomerName(contactsReturnDto.getContact())
                    .setCustomerPhone(contactsReturnDto.getPhone());
        }
        //技术员信息
        BaseResponse<List<Employee>> empByDeptId = userClientService.getEmpByDeptId(1);
        if (!CollectionUtils.isEmpty(empByDeptId.getData())) {
            List<Employee> employees = empByDeptId.getData();
            returnBreedingPlanDto.setTechnicianList(employees);
        }
        return returnBreedingPlanDto;
    }

    /**
     * 待上鸡签收详情
     *
     * @param plantId
     * @return
     * @author @Gao.
     */
    @Override
    public ReturnChickenSignDetailsDto chickenSignDetails(Integer plantId) {
        ReturnChickenSignDetailsDto returnChickenSignDetailsDto = new ReturnChickenSignDetailsDto();
        //养殖计划详情信息
        ReturnBreedingPlanDetailsDto returnBreedingPlanDetailsDto = breedingPlanDetails(plantId);
        returnChickenSignDetailsDto.setReturnBreedingPlanDetails(returnBreedingPlanDetailsDto);
        PurchaseOrderExample purchaseOrderExample = new PurchaseOrderExample();
        purchaseOrderExample.createCriteria().andPlanIdEqualTo(plantId);
        //商品采购列表信息
        List<ReturnPurchaseOrderDto> returnPurchaseOrderDtos = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectByExample(purchaseOrderExample);
        if (!CollectionUtils.isEmpty(purchaseOrders)) {
            for (PurchaseOrder purchaseOrder : purchaseOrders) {
                ReturnPurchaseOrderDto returnPurchaseOrderDto = new ReturnPurchaseOrderDto();
                BeanUtils.copyProperties(purchaseOrder, returnPurchaseOrderDto);
                returnPurchaseOrderDto
                        .setUnit(UnitEnum.getDescByCode(purchaseOrder.getUnit()))
                        .setProductType(ProductTypeEnum.getTypeByCode(purchaseOrder.getProductType()))
                        .setPurchaseOrderStatus(PurchaseOrderStatusEnum.getDescByCode(purchaseOrder.getPurchaseOrderStatus()));
                //签收人信息
                BaseResponse<UserInfo> globalUser = userClientService.getGlobalUser(purchaseOrder.getSignerId());
                if (globalUser.getData() != null) {
                    UserInfo userInfo = globalUser.getData();
                    if (userInfo.getName() != null) {
                        returnPurchaseOrderDto
                                .setSignerName(userInfo.getName());
                    }
                    if (userInfo.getPhoneNumber() != null) {
                        returnPurchaseOrderDto
                                .setSignerPhone(userInfo.getPhoneNumber());
                    }
                    returnPurchaseOrderDtos.add(returnPurchaseOrderDto);
                }
            }
        }
        returnChickenSignDetailsDto.setReturnPurchaseOrderDtos(returnPurchaseOrderDtos);
        return returnChickenSignDetailsDto;
    }

    /**
     * 获取养殖计划鸡舍信息
     *
     * @param planId
     * @return
     * @author yj
     */
    @Override
    public List<BreedingPlanCoopDto> listBreedingPlanCoopsForChoose(Integer planId) {
        return batchPlantCoopMapper.listBreedingPlanCoopsForChoose(planId);
    }

    /**
     * 养殖计划参数配置
     *
     * @param dto
     * @author yj
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void breedingPlanParamConfiguration(BreedingPlanParamConfigurationDto dto) {
        List<BreedingPlanCoopDto> breedingPlanCoopDtoList = dto.getBreedingPlanCoopDtoList();
        UserInfo currentUser = currentUserService.getCurrentUser();
        Integer currentUserId = currentUser == null ? null : currentUser.getId();
        // 插入养殖计划和鸡舍关系更新鸡舍在养数量和状态
        BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(dto.getPlanId());
        if (breedingPlan == null) {
            throw new RuntimeException("养殖计划id=" + dto.getPlanId() + "不存在");
        }
        if (!CollectionUtils.isEmpty(breedingPlanCoopDtoList)) {
            List<BatchPlantCoop> batchPlantCoopList = new ArrayList<>();
            List<Coop> coopList = new ArrayList<>();
            for (BreedingPlanCoopDto coopDto : breedingPlanCoopDtoList) {
                if (coopDto.getBreedingValue() != null && coopDto.getBreedingValue() > 0) {
                    BatchPlantCoop batchPlantCoop = new BatchPlantCoop();
                    batchPlantCoop.setCoopId(coopDto.getId())
                            .setCreateTime(new Date())
                            .setCreateUserId(currentUserId)
                            .setEnable(Boolean.TRUE)
                            .setPlanId(dto.getPlanId())
                            .setPlantId(coopDto.getPlantId());
                    batchPlantCoopList.add(batchPlantCoop);
                    Coop coop = new Coop();
                    coop.setId(coopDto.getId())
                            .setBreedingValue(coopDto.getBreedingValue())
                            .setCoopStatus(CoopStatusEnum.BREEDING.getCode())
                            .setModifyTime(new Date())
                            .setModifyUserId(currentUserId);
                    coopList.add(coop);
                }
            }
            batchPlantCoopMapper.insertBatch(batchPlantCoopList);
            coopMapper.batchUpdateByPrimaryKeySelective(coopList);
        }
        // 插入养殖计划参数
        List<BreedingParameterDto> breedingParameterDtoList = dto.getBreedingParameterDtoList();
        Integer standardId = null;
        if (!CollectionUtils.isEmpty(breedingParameterDtoList)) {
            List<BreedingBatchParameter> batchParameterList = new ArrayList<>();
            for (BreedingParameterDto parameterDto : breedingParameterDtoList) {
                List<BreedingStandardParameter> breedingStandardParameterList = parameterDto.getBreedingStandardParameterList();
                if (!CollectionUtils.isEmpty(breedingStandardParameterList)) {
                    if (standardId == null) {
                        standardId = breedingStandardParameterList.get(0).getStandardId();
                    }
                    for (BreedingStandardParameter standardParameter : breedingStandardParameterList) {
                        BreedingBatchParameter batchParameter = new BreedingBatchParameter();
                        BeanUtils.copyProperties(standardParameter, batchParameter);
                        batchParameter.setParamId(standardParameter.getId())
                                .setBatchNo(dto.getBatchNo())
                                .setCreateTime(new Date())
                                .setCreateUserId(currentUserId)
                                .setCustomerId(breedingPlan.getCustomerId())
                                .setEnable(Boolean.TRUE);
                        batchParameterList.add(batchParameter);
                    }
                }
            }
            breedingBatchParameterMapper.batchInsert(batchParameterList);
        }
        // 更新养殖计划
        BreedingStandard breedingStandard = breedingStandardMapper.selectByPrimaryKey(standardId);
        breedingPlan.setPlanStatus(PlanStatusEnum.SIGN_CHICKEN.getCode())
                .setModifyTime(new Date())
                .setModifyUserId(currentUserId)
                .setBreedingDays(breedingStandard == null ? null : breedingStandard.getBreedingDays());
        breedingPlanMapper.updateByPrimaryKeySelective(breedingPlan);
    }

    /**
     * 养殖中详情 确认出栏详情
     *
     * @param planId
     * @author @Gao.
     */
    @Override
    public ReturnBreedingDetailsDto breedingDetails(Integer planId) {
        HashMap<Integer, BigDecimal> calculatePurchaseOrderAllMap = null;
        ReturnBreedingDetailsDto returnBreedingDetailsDto = new ReturnBreedingDetailsDto();
        //送料情况信息
        BreedingPlan breedingPlan = breedingPlanMapper.selectByPrimaryKey(planId);
        if (breedingPlan == null) {
            throw new RuntimeException("当前养殖计划" + breedingPlan.getId() + "不存在");
        }
        BatchInfo batchInfo = batchInfoMapper.getTheLatestBatchInfo(planId);
        if (batchInfo != null) {
            //计算成活率
            String percentage = mathUtil.percentage(breedingPlan.getPlanChickenQuantity() - batchInfo.getDeadAmount(), breedingPlan.getPlanChickenQuantity());
            //计算预计出栏时间
            String expectSuchTime = dateUtil.accumulateDateByDay(breedingPlan.getPlanTime(), breedingPlan.getBreedingDays());
            //计算剩余饲料
            BigDecimal accumulativeFeed = batchInfoMapper.accumulativeFeed(planId);
            //计算异常次数
            DeviceAlarmLogExample deviceAlarmLogExample = new DeviceAlarmLogExample();
            deviceAlarmLogExample.createCriteria().andPlanIdEqualTo(planId);
            int abnormalWarn = deviceAlarmLogMapper.countByExample(deviceAlarmLogExample);
            //计算询问总次数
            TechConsultRecordExample techConsultRecordExample = new TechConsultRecordExample();
            techConsultRecordExample.createCriteria().andPlanIdEqualTo(planId);
            int askQues = techConsultRecordMapper.countByExample(techConsultRecordExample);
            //计算询问解决次数
            techConsultRecordExample.createCriteria().andTechConsultStatusEqualTo(TechConsultStatusEnum.STATUS_SOLVED.getCode());
            int solveQuestions = techConsultRecordMapper.countByExample(techConsultRecordExample);
            //计算计划采购 数量 重量
            List<CalculatePurchaseOrderDto> calculatePurchaseOrderDtos = new ArrayList<>();
            for (PlanStatusEnum planStatusEnum : PlanStatusEnum.values()) {
                CalculatePurchaseOrderDto calculatePurchaseOrderDto = new CalculatePurchaseOrderDto();
                int productType = planStatusEnum.getCode();
                calculatePurchaseOrder(planId, productType, null);
                BigDecimal planPurchaseValue = calculatePurchaseOrderAllMap.get(productType);
                HashMap<Integer, BigDecimal> calculatePurchaseOrderMap = calculatePurchaseOrder(planId, productType, PurchaseOrderStatusEnum.DELIVERY.getCode());
                BigDecimal deliverPurchaseValue = calculatePurchaseOrderMap.get(productType);
                calculatePurchaseOrderDto
                        .setProductType(productType)
                        .setPlanPurchaseValue(planPurchaseValue)
                        .setDeliverPurchaseValue(deliverPurchaseValue);
                calculatePurchaseOrderDtos.add(calculatePurchaseOrderDto);
            }
            //计算计划饲料 剩余饲料
            if (calculatePurchaseOrderAllMap.get(ProductTypeEnum.FEED.getCode()) != null) {
                BigDecimal totalPlanFeedWeight = calculatePurchaseOrderAllMap.get(ProductTypeEnum.FEED.getCode());
                returnBreedingDetailsDto
                        .setPlanFeed(totalPlanFeedWeight)
                        .setRemainFeed(totalPlanFeedWeight.subtract(accumulativeFeed));
            }
            //计算理论体重值

            //计算理论料肉比
            //养殖场信息
            List<Plant> plants = breedingPlantService.listPlantInfoByPlanId(planId);
            returnBreedingDetailsDto
                    .setBreedingDays(breedingPlan.getBreedingDays())
                    .setDayAge(batchInfo.getDayAge())
                    .setExpectSuchTime(expectSuchTime)
                    .setBreedingStock(batchInfo.getCurrentAmount())
                    .setSurvivalRate(percentage)
                    .setAbnormalWarn(abnormalWarn)
                    .setAskQuestions(askQues)
                    .setPlants(plants)
                    .setSolveQuestions(solveQuestions);
        }
        //养殖计划详情信息
        ReturnBreedingPlanDetailsDto returnBreedingPlanDetailsDto = breedingPlanDetails(planId);
        returnBreedingDetailsDto.setReturnBreedingPlanDetails(returnBreedingPlanDetailsDto);
        return returnBreedingDetailsDto;
    }

    /**
     * 根据不同商品类型 统计不同类型值
     *
     * @param planId
     * @param productType
     * @param purchaseOrderStatus
     * @author @Gao.
     */
    private HashMap<Integer, BigDecimal> calculatePurchaseOrder(Integer planId, Integer productType, Integer purchaseOrderStatus) {
        BigDecimal totalPlanFeedStatistics = BigDecimal.ZERO;
        HashMap<Integer, BigDecimal> calculatePurchaseOrderMap = new HashMap<>(16);
        //查询计划用料
        PurchaseOrderExample purchaseOrderExample = new PurchaseOrderExample();
        purchaseOrderExample
                .createCriteria()
                .andEnableEqualTo(true)
                .andPlanIdEqualTo(planId)
                .andProductTypeEqualTo(productType)
                .andPurchaseOrderStatusEqualTo(purchaseOrderStatus);
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectByExample(purchaseOrderExample);
        //药品 种苗
        boolean flag = ProductTypeEnum.DRUG.getCode() == productType || ProductTypeEnum.SPROUT.getCode() == productType;
        if (flag) {
            //统计数量
            if (!CollectionUtils.isEmpty(purchaseOrders)) {
                purchaseOrders.forEach(purchaseOrder -> totalPlanFeedStatistics.add(purchaseOrder.getAmount()));
                calculatePurchaseOrderMap.put(productType, totalPlanFeedStatistics);
            }
        }
        //饲料
        if (ProductTypeEnum.FEED.getCode() == productType) {
            //统计重量
            if (!CollectionUtils.isEmpty(purchaseOrders)) {
                purchaseOrders.forEach(purchaseOrder -> totalPlanFeedStatistics.add(purchaseOrder.getWeight()));
                calculatePurchaseOrderMap.put(productType, totalPlanFeedStatistics);
            }
        }
        return calculatePurchaseOrderMap;
    }
}


