package com.jaagro.cbs.web.controller;

import com.jaagro.cbs.api.dto.standard.BreedingStandardDto;
import com.jaagro.cbs.api.service.BreedingStandardService;
import com.jaagro.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author gavin
 *
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
    @ApiOperation("新增养殖标准模板")
    @PostMapping("/createBreedingStandard")
    public BaseResponse createBreedingStandard(@RequestBody BreedingStandardDto dto) {

        Boolean result = false;
        try {
            result = breedingStandardService.createBreedingTemplate(dto);
        } catch (Exception ex) {
            return BaseResponse.errorInstance(ex.getMessage());
        }
        return BaseResponse.successInstance(result);
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
        BreedingStandardDto breedingStandardDto  =breedingStandardService.getBreedingStandardById(id);
        return BaseResponse.successInstance(breedingStandardDto);
    }


}
