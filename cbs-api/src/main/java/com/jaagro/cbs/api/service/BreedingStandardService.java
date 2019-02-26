package com.jaagro.cbs.api.service;


import com.jaagro.cbs.api.dto.standard.BreedingStandardDto;

/**
 * 养殖大脑管理
 *
 * @author gavin
 * @date :2019/02/22
 */
public interface BreedingStandardService {
    /**
     * 创建养殖模版与参数
     *
     * @param dto
     */
    Boolean createBreedingTemplate(BreedingStandardDto dto);

    /**
     * 修改养殖模版与参数
     * @param dto
     * @return
     */
    Boolean updateBreedingTemplate(BreedingStandardDto dto);

    /**
     * 根据养殖模板ID获取养殖模板详情
     * @param id
     * @return
     */
    BreedingStandardDto getBreedingStandardById(Integer id);

    /**
     * 根据标准模板的ID获取该模板所有的参数
     * @param standardId
     * @return
     */
    //List<BreedingStandardParameterDto> getStandardParameterById(Integer standardId);

}
