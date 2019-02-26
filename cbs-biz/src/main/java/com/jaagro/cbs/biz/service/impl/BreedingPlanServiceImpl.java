package com.jaagro.cbs.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.constant.CertificateStatus;
import com.jaagro.cbs.api.constant.ContractStatus;
import com.jaagro.cbs.api.dto.base.CustomerContactsReturnDto;
import com.jaagro.cbs.api.dto.plan.*;
import com.jaagro.cbs.api.enums.PlanStatusEnum;
import com.jaagro.cbs.api.model.*;
import com.jaagro.cbs.api.service.BatchPlantCoopService;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.biz.mapper.*;
import com.jaagro.cbs.biz.service.CustomerClientService;
import com.jaagro.cbs.biz.utils.SequenceCodeUtils;
import com.jaagro.constant.UserInfo;
import com.jaagro.utils.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private CoopMapperExt coopMapper;
    @Autowired
    private BatchPlantCoopService batchPlantCoopService;
    @Autowired
    private BreedingBatchParameterMapperExt breedingBatchParameterMapperExt;


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
            List<Integer> plantIds = dto.getPlantIds();
            for (Integer plantId : plantIds) {
                BatchPlantCoop batchPlantCoop = new BatchPlantCoop();
                batchPlantCoop
                        .setCreateUserId(currentUser.getId())
                        .setPlantId(plantId);
                batchPlantCoopMapper.insertSelective(batchPlantCoop);
            }
        }
    }

    /**
     * 养殖计划列表
     *
     * @param dto
     * @return
     * @author @Gao.
     */
    @Override
    public PageInfo<List<ReturnBreedingPlanDto>> listBreedingPlan(BreedingPlanParamDto dto) {
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
            //填充鸡舍
            List<Integer> coopId = batchPlantCoopService.listCoopIdByPlanId(returnBreedingPlanDto.getId());
            if (!CollectionUtils.isEmpty(coopId)) {
                CoopExample coopExample = new CoopExample();
                coopExample.createCriteria()
                        .andIdIn(coopId)
                        .andEnableEqualTo(true);
                returnBreedingPlanDto.setCoops(coopMapper.selectByExample(coopExample));
            }
            //填充进度
            BreedingBatchParameterExample parameterExample = new BreedingBatchParameterExample();
            parameterExample.createCriteria()
                    .andEnableEqualTo(true)
                    .andPlanIdEqualTo(returnBreedingPlanDto.getId())
                    .andBatchNoEqualTo(returnBreedingPlanDto.getBatchNo());
            List<BreedingBatchParameter> breedingBatchParameters = breedingBatchParameterMapperExt.selectByExample(parameterExample);
            if (!CollectionUtils.isEmpty(breedingBatchParameters)) {
                BreedingBatchParameter parameter = breedingBatchParameters.get(0);
                returnBreedingPlanDto.setAllDayAge(parameter.getDayAge());
                try {
                    long day = getInterval(new Date(), returnBreedingPlanDto.getPlanTime());
                    returnBreedingPlanDto.setAlreadyDayAge((int) day);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return new PageInfo(planDtoList);
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
        if (breedingPlan == null){
            throw new RuntimeException("养殖计划id="+planId+"不存在");
        }
        UserInfo currentUser = currentUserService.getCurrentUser();
        Integer currentUserId = currentUser == null ? null : currentUser.getId();
        // 插入计划合同
        BatchContract batchContract = new BatchContract();
        BeanUtils.copyProperties(createPlanContractDto,batchContract);
        batchContract.setContractNumber(sequenceCodeUtils.genSeqCode("HT"))
                .setContractDate(new Date())
                .setCreateTime(new Date())
                .setContractStatus(ContractStatus.UNAUDITED)
                .setCreateUserId(currentUserId)
                .setCustomerId(breedingPlan.getCustomerId())
                .setEnable(true);
        batchContractMapper.insertSelective(batchContract);
        // 插入计划合同图片
        if (!CollectionUtils.isEmpty(createPlanContractDto.getImageUrlList())){
            List<ContractSource> contractSourceList = new ArrayList<>();
            for (String imageUrl : createPlanContractDto.getImageUrlList()){
                ContractSource contractSource = new ContractSource();
                contractSource.setCertificateImageUrl(imageUrl)
                        .setCertificateStatus(CertificateStatus.UNCHECKED)
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
        if (!CollectionUtils.isEmpty(contractPriceSectionDtoList)){
            List<ContractPriceSection> contractPriceSectionList = new ArrayList<>();
            for (ContractPriceSectionDto dto : contractPriceSectionDtoList){
                ContractPriceSection contractPriceSection = new ContractPriceSection();
                BeanUtils.copyProperties(dto,contractPriceSection);
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

    private long getInterval(Date begin_date, Date end_date) throws Exception {
        long day = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (begin_date != null) {
            String begin = sdf.format(begin_date);
            begin_date = sdf.parse(begin);
        }
        if (end_date != null) {
            String end = sdf.format(end_date);
            end_date = sdf.parse(end);
        }
        day = (end_date.getTime() - begin_date.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

}
