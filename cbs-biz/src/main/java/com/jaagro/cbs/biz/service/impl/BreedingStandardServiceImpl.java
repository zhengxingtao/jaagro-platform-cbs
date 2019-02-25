package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.standard.BreedingStandardDto;
import com.jaagro.cbs.api.dto.standard.BreedingStandardParameterDto;
import com.jaagro.cbs.api.service.BreedingStandardService;
import com.jaagro.cbs.biz.mapper.BreedingStandardMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingStandardParameterMapperExt;
import com.jaagro.cbs.api.model.BreedingStandard;
import com.jaagro.cbs.api.model.BreedingStandardParameter;
import com.jaagro.cbs.api.model.BreedingStandardParameterExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 养殖大脑管理
 *
 * @author gavin
 * @date :2019/02/22
 */
@Slf4j
@Service
public class BreedingStandardServiceImpl implements BreedingStandardService {

    //    @Autowired
//    private CurrentUserService currentUserService;
    @Autowired
    private BreedingStandardMapperExt breedingStandardMapper;

    @Autowired
    private BreedingStandardParameterMapperExt standardParameterMapper;

    /**
     * 创建养殖模版与参数
     *
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createBreedingTemplate(BreedingStandardDto dto) {
        try {
            log.info("o BreedingStardandServiceImpl.createBreedingTemplate input BreedingStandardDto:{}", dto);
            Assert.notNull(dto.getStandardName(), "模板名称不能为空");
            Assert.notNull(dto.getBreedingDays(), "养殖天数不能为空");
            Assert.notNull(dto.getStandardParameterDtos(), "养殖参数不能为空");
            Assert.notEmpty(dto.getStandardParameterDtos(), "养殖参数不能为空");


            BreedingStandard breedingStandard = new BreedingStandard();
            breedingStandard.setBreedingType(dto.getBreedingType())
                    .setBreedingDays(dto.getBreedingDays())
                    .setStandardName(dto.getStandardName())
                    .setCreateUserId(99999999)
                    .setCreateTime(new Date());
            breedingStandardMapper.insertSelective(breedingStandard);

            int standardId = breedingStandard.getId();

            for (BreedingStandardParameterDto breedingStandardParameterDto : dto.getStandardParameterDtos()) {
                BreedingStandardParameter breedingStandardParameter = new BreedingStandardParameter();
                BeanUtils.copyProperties(breedingStandardParameterDto, breedingStandardParameter);
                breedingStandardParameter.setStandardId(standardId)
                        .setCreateUserId(999999)
                        .setCreateTime(new Date());

                standardParameterMapper.insertSelective(breedingStandardParameter);

            }
            log.info("o BreedingStardandServiceImpl.createBreedingTemplate standard_id:{} standardParams.size:{}", standardId, dto.getStandardParameterDtos().size());
        } catch (Exception e) {
            log.error("R BreedingStardandServiceImpl.createBreedingTemplate  error:" + e);
            return false;

        }
        return true;

    }

    /**
     * 修改养殖模版与参数
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBreedingTemplate(BreedingStandardDto dto) {
        try {
            log.info("o BreedingStardandServiceImpl.updateBreedingTemplate input BreedingStandardDto:{}", dto);
            Assert.notNull(dto.getId(), "模板Id不能为空");
            Assert.notNull(dto.getStandardName(), "模板名称不能为空");
            Assert.notNull(dto.getBreedingDays(), "养殖天数不能为空");
            Assert.notNull(dto.getStandardParameterDtos(), "养殖参数不能为空");
            Assert.notEmpty(dto.getStandardParameterDtos(), "养殖参数不能为空");


            BreedingStandard breedingStandard = new BreedingStandard();
            breedingStandard.setId(dto.getId())
                    .setBreedingType(dto.getBreedingType())
                    .setBreedingDays(dto.getBreedingDays())
                    .setStandardName(dto.getStandardName())
                    .setModifyUserId(99999999)
                    .setModifyTime(new Date());
            breedingStandardMapper.updateByPrimaryKeySelective(breedingStandard);
            //先删掉
            standardParameterMapper.deleteByStandardId(dto.getId());
            //再插入
            for (BreedingStandardParameterDto breedingStandardParameterDto : dto.getStandardParameterDtos()) {
                BreedingStandardParameter breedingStandardParameter = new BreedingStandardParameter();
                BeanUtils.copyProperties(breedingStandardParameterDto, breedingStandardParameter);
                breedingStandardParameter.setStandardId(dto.getId())
                        .setCreateUserId(999999)
                        .setCreateTime(new Date());

                standardParameterMapper.insertSelective(breedingStandardParameter);

            }
            log.info("o BreedingStardandServiceImpl.updateBreedingTemplate standard_id:{} standardParams.size:{}", dto.getId(), dto.getStandardParameterDtos().size());
        } catch (Exception e) {
            log.error("R BreedingStardandServiceImpl.updateBreedingTemplate  error:" + e);
            return false;

        }
        return true;
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
        BeanUtils.copyProperties(breedingStandard, breedingStandardDto);

        BreedingStandardParameterExample example = new BreedingStandardParameterExample();
        example.createCriteria().andStandardIdEqualTo(standardId);
        List<BreedingStandardParameter> parameterList = standardParameterMapper.selectByExample(example);

        List<BreedingStandardParameterDto> parameterDtos = new ArrayList<>();
        for (BreedingStandardParameter breedingStandardParameter : parameterList) {
            BreedingStandardParameterDto parameterDto = new BreedingStandardParameterDto();
            BeanUtils.copyProperties(breedingStandardParameter, parameterDto);
            parameterDtos.add(parameterDto);
        }
        breedingStandardDto.setStandardParameterDtos(parameterDtos);

        return breedingStandardDto;
    }


}
