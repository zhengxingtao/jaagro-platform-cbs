package com.jaagro.cbs.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.techconsult.ReturnTechConsultRecordDto;
import com.jaagro.cbs.api.dto.techconsult.TechConsultParamDto;
import com.jaagro.cbs.api.dto.techconsult.UpdateTechConsultDto;
import com.jaagro.cbs.api.enums.TechConsultStatusEnum;
import com.jaagro.cbs.api.model.TechConsultRecord;
import com.jaagro.cbs.api.model.TechConsultRecordExample;
import com.jaagro.cbs.api.service.TechConsultService;
import com.jaagro.cbs.biz.mapper.TechConsultRecordMapperExt;
import com.jaagro.cbs.biz.service.CustomerClientService;
import com.jaagro.constant.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author gavin
 * @date 2019/2/28 13:31
 */
@Service
@Slf4j
public class TechConsultServiceImpl implements TechConsultService {
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private TechConsultRecordMapperExt techConsultRecordMapper;

    /**
     * 技术询问分页列表
     *
     * @param dto
     * @return
     */
    @Override
    public PageInfo listTechConsultRecords(TechConsultParamDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        TechConsultRecordExample example = new TechConsultRecordExample();
        TechConsultRecordExample.Criteria criteriaOne = example.createCriteria();

        criteriaOne.andTechConsultStatusEqualTo(dto.getStatus());
        criteriaOne.andBatchNoLike(dto.getBatchNo() + "%");
        criteriaOne.andCustomerNameLike(dto.getKeyWord() + "%");

        TechConsultRecordExample.Criteria criteriaTwo = example.createCriteria();
        criteriaTwo.andTechConsultStatusEqualTo(dto.getStatus());
        criteriaOne.andBatchNoLike(dto.getBatchNo() + "%");
        criteriaTwo.andCustomerPhoneNumberLike(dto.getKeyWord() + "%");
        example.or(criteriaTwo);
        List<TechConsultRecord> techConsultRecoreDos = techConsultRecordMapper.selectByExample(example);

        return new PageInfo(techConsultRecoreDos);
    }

    /**
     * 根据技术询问主键Id获取详情
     *
     * @param id
     * @return
     */
    @Override
    public ReturnTechConsultRecordDto getDetailTechConsultDtoById(Integer id) {

        ReturnTechConsultRecordDto returnDto = new ReturnTechConsultRecordDto();
        TechConsultRecord techConsultRecordDo =  techConsultRecordMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(techConsultRecordDo,returnDto);
        returnDto.setStrTechConsultStatus(TechConsultStatusEnum.getDescByCode(techConsultRecordDo.getTechConsultStatus()));

        return returnDto;
    }

    /**
     * 处理技术申请
     *
     * @param updateDto
     * @return
     */
    @Override
    public boolean HandleTechConsultRecord(UpdateTechConsultDto updateDto) {
        try {
            log.info("O TechConsultServiceImpl.HandleTechConsultRecord input updateDto:{}", updateDto);
            UserInfo currentUser = currentUserService.getCurrentUser();
            TechConsultRecord techConslutRecordDo = new TechConsultRecord();
            BeanUtils.copyProperties(updateDto, techConslutRecordDo);
            techConslutRecordDo.setModifyTime(new Date());
            techConslutRecordDo.setModifyUserId(currentUser != null ? currentUser.getId() : null);
            techConsultRecordMapper.updateByPrimaryKeySelective(techConslutRecordDo);
        }catch(Exception e){
            log.error("R TechConsultServiceImpl.HandleTechConsultRecord error:" + e);
            return false;
        }
        return true;
    }


}
