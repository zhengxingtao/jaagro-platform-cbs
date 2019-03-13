package com.jaagro.cbs.api.service;


import com.jaagro.cbs.api.dto.standard.BreedingParameterListDto;
import com.jaagro.cbs.api.dto.standard.BreedingStandardDto;
import com.jaagro.cbs.api.dto.standard.CreateBreedingStandardDto;
import com.jaagro.cbs.api.model.BreedingStandard;

import java.util.List;

/**
 * 养殖大脑管理
 *
 * @author yj
 * @date :2019/02/22
 */
public interface BreedingStandardService {
    /**
     * 创建养殖模版与参数
     *
     * @param dto
     * @return
     */
    Integer createBreedingTemplate(CreateBreedingStandardDto dto);

    /**
     * 修改养殖模版与参数
     *
     * @param dto
     * @return
     */
    Integer updateBreedingTemplate(CreateBreedingStandardDto dto);

    /**
     * 根据养殖模板ID获取养殖模板详情
     *
     * @param id
     * @return
     */
    BreedingStandardDto getBreedingStandardById(Integer id);

    /**
     * 查询所有养殖模板
     *
     * @return
     * @author yj
     */
    List<BreedingStandard> listAllBreedingStandard();

    /**
     * 创建或者更新养殖模板参数
     * @param dto
     */
    void saveOrUpdateParameter(BreedingParameterListDto dto);

}
