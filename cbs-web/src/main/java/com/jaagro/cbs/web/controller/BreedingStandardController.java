package com.jaagro.cbs.web.controller;

import com.jaagro.cbs.api.dto.standard.BreedingStandardDto;
import com.jaagro.cbs.api.service.BreedingStandardService;
import com.jaagro.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


/**
 * @author gavin
 * @Date 20190222
 */
@RestController
@Slf4j
@Api(description = "养殖标准模板管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreedingStandardController {

    @Autowired
    private BreedingStandardService breedingStandardService;


    /**
     * 新增养殖标准模板
     *
     * @param dto
     * @return
     */
    @ApiOperation("新增、修改养殖标准模板")
    @PostMapping("/breedingStandard")
    public BaseResponse breedingStandard(@RequestBody BreedingStandardDto dto) {
        try {
            Boolean result = false;
            Assert.notNull(dto.getStandardName(), "模板名称不能为空");
            Assert.notNull(dto.getBreedingDays(), "养殖天数不能为空");
            Assert.notNull(dto.getStandardParameterDos(), "养殖参数不能为空");
            Assert.notEmpty(dto.getStandardParameterDos(), "养殖参数不能为空");
            if (null == dto.getId() || dto.getId() == 0) {
                result = breedingStandardService.createBreedingTemplate(dto);
            } else {
                result = breedingStandardService.updateBreedingTemplate(dto);
            }
        } catch (Exception ex) {
            return BaseResponse.errorInstance("保存失败：" + ex.getMessage());
        }
        return BaseResponse.successInstance("保存成功");
    }


    /**
     * 查询单个养殖模板详情
     *
     * @param id
     * @return
     */
    @ApiOperation("查询单个养殖模板详情")
    @GetMapping("/getBreedingStandardById/{id}")
    public BaseResponse getBreedingStandardById(@PathVariable("id") Integer id) {
        BreedingStandardDto breedingStandardDto = breedingStandardService.getBreedingStandardById(id);
        return BaseResponse.successInstance(breedingStandardDto);
    }


}
