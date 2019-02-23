package com.jaagro.cbs.biz.service.impl;

import com.jaagro.cbs.api.dto.standard.BreedingStandardDto;
import com.jaagro.cbs.api.dto.standard.BreedingStandardParameterDto;
import com.jaagro.cbs.api.service.BreedingStandardService;
import com.jaagro.cbs.biz.mapper.BreedingStandardMapperExt;
import com.jaagro.cbs.biz.mapper.BreedingStandardParameterMapperExt;
import com.jaagro.cbs.biz.model.BreedingStandard;
import com.jaagro.cbs.biz.model.BreedingStandardParameter;
import com.jaagro.utils.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Map;

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
    public Boolean createBreedingTemplate(BreedingStandardDto dto) {
        try {
            log.info("o BreedingStardandServiceImpl.createBreedingTemplate input BreedingStandardDto:{}", dto);
            Assert.notNull(dto.getStandardName(), "模板名称不能为空");
            Assert.notNull(dto.getBreedingDays(), "养殖天数不能为空");
            Assert.notNull(dto.getStandardParameterDtos(), "养殖参数不能为空");
            if (CollectionUtils.isEmpty(dto.getStandardParameterDtos())) {
                throw new RuntimeException("养殖参数不能为空");
            }

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
    public Boolean updateBreedingTemplate(BreedingStandardDto dto) {
        return null;
    }

    @Override
    public BreedingStandardDto getBreedingStandardById(Integer id) {
        return null;
    }
}
