package com.jaagro.cbs.api.service;


import com.jaagro.cbs.api.dto.standard.BreedingStandardDto;

import java.util.Map;

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
    Map<String,Object> createBreedingTemplate(BreedingStandardDto dto);

    /**
     * 修改养殖模版与参数
     * @param dto
     * @return
     */
    Map<String,Object> updateBreedingTemplate(BreedingStandardDto dto);

    BreedingStandardDto getBreedingStandardById(Integer id);


}
