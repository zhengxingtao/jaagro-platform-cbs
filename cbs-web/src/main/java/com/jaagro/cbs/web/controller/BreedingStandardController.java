package com.jaagro.cbs.web.controller;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.standard.BreedingParameterDto;
import com.jaagro.cbs.api.dto.standard.BreedingStandardDetailDto;
import com.jaagro.cbs.api.dto.standard.BreedingStandardDto;
import com.jaagro.cbs.api.dto.standard.ListBreedingStandardCriteria;
import com.jaagro.cbs.api.model.BreedingStandardParameter;
import com.jaagro.cbs.api.service.BreedingStandardService;
import com.jaagro.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
            Assert.notNull(dto.getStandardName(), "模板名称不能为空");
            Assert.notNull(dto.getBreedingDays(), "养殖天数不能为空");
            Assert.notNull(dto.getStandardParameterDos(), "养殖参数不能为空");
            Assert.notEmpty(dto.getStandardParameterDos(), "养殖参数不能为空");
            if (null == dto.getId() || dto.getId() == 0) {
                breedingStandardService.createBreedingTemplate(dto);
            } else {
                breedingStandardService.updateBreedingTemplate(dto);
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


    /**
     * 分页查询所有的养殖模板
     * @author yj
     * @param criteria
     * @return
     */
    @ApiOperation("分页查询所有的养殖模板")
    @PostMapping("/listBreedingStandardByCriteria")
    public BaseResponse listBreedingStandardByCriteria(@RequestBody @Validated ListBreedingStandardCriteria criteria){
        log.info("O listBreedingStandardByCriteria criteria={}",criteria);
        PageInfo pageInfo = breedingStandardService.listBreedingStandardByCriteria(criteria);
        return BaseResponse.successInstance(pageInfo);
    }

    /**
     * 查询单个养殖模板详情按日龄分组
     * @author yj
     * @param id
     * @return
     */
    @ApiOperation("查询单个养殖模板详情按日龄分组")
    @GetMapping("/getBreedingStandardDetail/{id}")
    public BaseResponse getBreedingStandardDetail(@PathVariable("id") Integer id){
        BreedingStandardDto breedingStandardDto = breedingStandardService.getBreedingStandardById(id);
        // 将养殖参数按照日龄分组
        BreedingStandardDetailDto detailDto = groupBreedingStandard(breedingStandardDto);
        return BaseResponse.successInstance(detailDto);
    }

    private BreedingStandardDetailDto groupBreedingStandard(BreedingStandardDto breedingStandardDto) {
        BreedingStandardDetailDto detailDto = new BreedingStandardDetailDto();
        List<BreedingStandardParameter> standardParameterDos = breedingStandardDto.getStandardParameterDos();
        if (!CollectionUtils.isEmpty(standardParameterDos)){
            Set<Integer> dayAgeSet = new HashSet<>();
            List<BreedingParameterDto> breedingParameterDtoList = new ArrayList<>();
            standardParameterDos.forEach(parameter->dayAgeSet.add(parameter.getDayAge()));
            for (Integer dayAge : dayAgeSet){
                BreedingParameterDto parameterDto = new BreedingParameterDto();
                parameterDto.setDayAge(dayAge);
                List<BreedingStandardParameter> parameterList = new ArrayList<>();
                parameterDto.setBreedingStandardParameterList(parameterList);
                breedingParameterDtoList.add(parameterDto);
            }
            for (BreedingStandardParameter parameter : standardParameterDos){
                for (BreedingParameterDto parameterDto : breedingParameterDtoList){
                    List<BreedingStandardParameter> breedingStandardParameterList = parameterDto.getBreedingStandardParameterList();
                    if (parameter.getDayAge().equals(parameterDto.getDayAge())){
                        breedingStandardParameterList.add(parameter);
                    }
                }
            }
            detailDto.setBreedingParameterDtoList(breedingParameterDtoList);
            detailDto.setDayAgeList(new ArrayList<>(dayAgeSet));
        }
        return detailDto;
    }
}
