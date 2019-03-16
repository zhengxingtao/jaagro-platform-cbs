package com.jaagro.cbs.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.plan.CustomerInfoParamDto;
import com.jaagro.cbs.api.dto.standard.*;
import com.jaagro.cbs.api.enums.PlanStatusEnum;
import com.jaagro.cbs.api.enums.SortTypeEnum;
import com.jaagro.cbs.api.model.BreedingStandard;
import com.jaagro.cbs.api.model.BreedingStandardParameter;
import com.jaagro.cbs.api.model.BreedingStandardParameterExample;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.api.service.BreedingStandardService;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingStandardDrugMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingStandardMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingStandardParameterMapperExt;
import com.jaagro.constant.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 养殖大脑管理
 *
 * @author yj
 * @date :2019/02/22
 */
@Slf4j
@Service
public class BreedingStandardServiceImpl implements BreedingStandardService {

    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private BreedingStandardMapperExt breedingStandardMapper;
    @Autowired
    private BreedingStandardParameterMapperExt standardParameterMapper;
    @Autowired
    private BreedingStandardDrugMapperExt breedingStandardDrugMapper;
    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private BreedingPlanService breedingPlanService;

    /**
     * 创建养殖模版与参数
     *
     * @param dto
     */
    @Override
    public Integer createBreedingTemplate(CreateBreedingStandardDto dto) {
        log.info("O BreedingStandardServiceImpl.createBreedingTemplate input BreedingStandardDto:{}", dto);
        Integer currentUserId = getCurrentUserId();
        BreedingStandard breedingStandard = new BreedingStandard();
        BeanUtils.copyProperties(dto, breedingStandard);
        breedingStandard.setCreateUserId(currentUserId)
                .setCreateTime(new Date())
                .setEnable(Boolean.TRUE);
        breedingStandardMapper.insertSelective(breedingStandard);
        return breedingStandard.getId();
    }

    /**
     * 修改养殖模版与参数
     *
     * @param dto
     * @return
     */
    @Override
    public Integer updateBreedingTemplate(CreateBreedingStandardDto dto) {
        log.info("O BreedingStandardServiceImpl.updateBreedingTemplate input BreedingStandardDto:{}", dto);
        Integer currentUserId = getCurrentUserId();
        BreedingStandard breedingStandard = new BreedingStandard();
        BeanUtils.copyProperties(dto, breedingStandard);
        breedingStandard.setModifyTime(new Date())
                .setModifyUserId(currentUserId);
        breedingStandardMapper.updateByPrimaryKeySelective(breedingStandard);
        return breedingStandard.getId();
    }

    /**
     * 根据养殖模板ID获取养殖模板详情
     *
     * @param standardId
     * @return
     */
    @Override
    public BreedingStandardDto getBreedingStandardById(Integer standardId) {

        BreedingStandardDto breedingStandardDto = new BreedingStandardDto();
        BreedingStandard breedingStandard = breedingStandardMapper.selectByPrimaryKey(standardId);
        Assert.notNull(breedingStandard, "模板不存在");
        BeanUtils.copyProperties(breedingStandard, breedingStandardDto);

        BreedingStandardParameterExample example = new BreedingStandardParameterExample();
        example.createCriteria().andStandardIdEqualTo(standardId);
        List<BreedingStandardParameter> parameterList = standardParameterMapper.selectByExample(example);


        breedingStandardDto.setStandardParameterDos(parameterList);

        return breedingStandardDto;
    }

    /**
     * 分页查询所有养殖模板
     *
     * @return
     * @author yj
     */
    @Override
    public List<BreedingStandard> listAllBreedingStandard() {
        return breedingStandardMapper.listAllBreedingStandard();
    }

    /**
     * 创建或者更新养殖模板参数
     *
     * @param dto
     */
    @Override
    public void saveOrUpdateParameter(BreedingParameterListDto dto) {
        List<BreedingStandardParameterItemDto> breedingStandardParameterList = dto.getBreedingStandardParameterList();
        if (!CollectionUtils.isEmpty(breedingStandardParameterList)) {
            List<BreedingStandardParameterItemDto> newParameterList = new ArrayList<>();
            List<BreedingStandardParameterItemDto> oldParameterList = new ArrayList<>();
            for (BreedingStandardParameterItemDto itemDto : breedingStandardParameterList) {
                if (itemDto.getId() != null) {
                    oldParameterList.add(itemDto);
                } else {
                    newParameterList.add(itemDto);
                }
            }
            Integer currentUserId = getCurrentUserId();
            if (!CollectionUtils.isEmpty(newParameterList)) {
                List<BreedingStandardParameter> parameterList = new ArrayList<>();
                for (BreedingStandardParameterItemDto itemDto : newParameterList) {
                    BreedingStandardParameter parameter = new BreedingStandardParameter();
                    BeanUtils.copyProperties(dto, parameter);
                    BeanUtils.copyProperties(itemDto, parameter);
                    if (dto.getAlarm() == null) {
                        parameter.setAlarm(Boolean.FALSE);
                    }
                    parameter.setEnable(Boolean.TRUE)
                            .setCreateTime(new Date())
                            .setCreateUserId(currentUserId);
                    parameterList.add(parameter);
                }
                standardParameterMapper.batchInsert(parameterList);
            }
            if (!CollectionUtils.isEmpty(oldParameterList)) {
                List<BreedingStandardParameter> parameterList = new ArrayList<>();
                for (BreedingStandardParameterItemDto itemDto : oldParameterList) {
                    BreedingStandardParameter parameter = new BreedingStandardParameter();
                    BeanUtils.copyProperties(dto, parameter);
                    BeanUtils.copyProperties(itemDto, parameter);
                    parameter
                            .setModifyTime(new Date())
                            .setModifyUserId(currentUserId);
                    parameterList.add(parameter);
                }
                standardParameterMapper.batchUpdateByPrimaryKeySelective(parameterList);
            }
        }
    }

    /**
     * 养殖大脑 养殖参数列表
     *
     * @param criteria
     * @return
     * @author @Gao.
     */
    @Override
    public PageInfo listBreedingParamTemplate(BreedingParamTemplateCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        if (criteria.getCustomerInfo() != null) {
            List<Integer> listCustomerIds = breedingPlanService.listCustomerIdsByKeyword(criteria.getCustomerInfo());
            criteria.setCustomerIds(listCustomerIds);
        }
        List<ReturnBreedingParamTemplateDto> returnBreedingParamTemplateDtos = breedingPlanMapper.listBreedingParamTemplate(criteria);
        for (ReturnBreedingParamTemplateDto returnBreedingParamTemplateDto : returnBreedingParamTemplateDtos) {
            if (returnBreedingParamTemplateDto.getPlanStatus() != null) {
                returnBreedingParamTemplateDto
                        .setStrPlanStatus(PlanStatusEnum.getDescByCode(returnBreedingParamTemplateDto.getPlanStatus()));
                if (returnBreedingParamTemplateDto.getCustomerId() != null) {
                    CustomerInfoParamDto customerInfo = breedingPlanService.getCustomerInfo(returnBreedingParamTemplateDto.getCustomerId());
                    if (customerInfo != null) {
                        if (customerInfo.getCustomerName() != null) {
                            returnBreedingParamTemplateDto.setCustomerName(customerInfo.getCustomerName());
                        }
                        if (customerInfo.getCustomerPhone() != null) {
                            returnBreedingParamTemplateDto.setCustomerPhone(customerInfo.getCustomerPhone());
                        }
                    }
                }
            }
        }
        return new PageInfo(returnBreedingParamTemplateDtos);
    }

    /**
     * 查询养殖模板下的参数分类列表
     *
     * @param standardId
     * @return
     */
    @Override
    public List<ParameterTypeDto> listParameterNameByStandardId(Integer standardId) {
        BreedingStandard breedingStandard = breedingStandardMapper.selectByPrimaryKey(standardId);
        Assert.notNull(breedingStandard, "模板不存在");
        BreedingStandardParameterExample example = new BreedingStandardParameterExample();
        example.createCriteria().andStandardIdEqualTo(standardId).andEnableEqualTo(Boolean.TRUE);
        List<BreedingStandardParameter> parameterList = standardParameterMapper.selectByExample(example);
        Set<ParameterTypeDto> parameterTypeDtoSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(parameterList)) {
            for (BreedingStandardParameter parameter : parameterList) {
                // 同一养殖模板下相同参数名称相同参数类型展示顺序一样
                parameterTypeDtoSet.add(new ParameterTypeDto(standardId, parameter.getParamName(), parameter.getParamType(),parameter.getDisplayOrder()));
            }
        }
        return new ArrayList<>(parameterTypeDtoSet);
    }

    /**
     * 根据模板id参数名称参数类型查看养殖模板参数
     *
     * @param standardId
     * @param paramName
     * @param paramType
     * @return
     */
    @Override
    public BreedingParameterListDto listParameterListByName(Integer standardId, String paramName, Integer paramType) {
        BreedingStandardParameterExample parameterExample = new BreedingStandardParameterExample();
        parameterExample.createCriteria().andEnableEqualTo(Boolean.TRUE)
                .andStandardIdEqualTo(standardId).andParamTypeEqualTo(paramType).andParamNameEqualTo(paramName);
        parameterExample.setOrderByClause("day_age asc");
        List<BreedingStandardParameter> parameterList = standardParameterMapper.selectByExample(parameterExample);
        if (!CollectionUtils.isEmpty(parameterList)){
            BreedingParameterListDto dto = new BreedingParameterListDto();
            BreedingStandardParameter parameter = parameterList.get(0);
            dto.setAlarm(parameter.getAlarm())
                    .setNecessary(parameter.getNecessary())
                    .setParamName(paramName)
                    .setParamType(paramType)
                    .setStandardId(standardId)
                    .setStatus(parameter.getStatus())
                    .setUnit(parameter.getUnit())
                    .setValueType(parameter.getValueType());
            List<BreedingStandardParameterItemDto> breedingStandardParameterList = new ArrayList<>();
            for (BreedingStandardParameter parameterIn : parameterList){
                BreedingStandardParameterItemDto itemDto = new BreedingStandardParameterItemDto();
                itemDto.setDayAge(parameterIn.getDayAge())
                        .setId(parameterIn.getId())
                        .setLowerLimit(parameterIn.getLowerLimit())
                        .setUpperLimit(parameterIn.getUpperLimit())
                        .setParamValue(parameterIn.getParamValue());
                breedingStandardParameterList.add(itemDto);
            }
            dto.setBreedingStandardParameterList(breedingStandardParameterList);
            return dto;
        }
        return null;
    }

    /**
     * 根据模板id查询药品配置信息
     *
     * @param standardId
     * @return
     */
    @Override
    public List<BreedingStandardDrugDto> listBreedingStandardDrugs(Integer standardId) {
        return breedingStandardDrugMapper.listBreedingStandardDrugs(standardId);
    }

    /**
     * 更新参数排序
     * @param dto
     * @return
     */
    @Override
    public List<ParameterTypeDto> changeParameterDisplayOrder(ChangeParameterDisplayOrderDto dto) {
        List<ParameterTypeDto> result = new ArrayList<>();
        Integer sortType = dto.getSortType();
        if (!SortTypeEnum.UP.equals(sortType) && !SortTypeEnum.DOWN.equals(sortType)){
            throw new RuntimeException("排序类型不正确");
        }
        List<ParameterTypeDto> parameterTypeDtoList = listParameterNameByStandardId(dto.getStandardId());
        if (CollectionUtils.isEmpty(parameterTypeDtoList)){
            throw new RuntimeException("养殖模板参数为空");
        }
        if (SortTypeEnum.UP.equals(sortType)){

        }else {

        }
        return result;
    }

    private Integer getCurrentUserId() {
        UserInfo userInfo = currentUserService.getCurrentUser();
        return userInfo == null ? null : userInfo.getId();
    }
}
