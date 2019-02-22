package com.jaagro.cbs.web.controller;

import com.jaagro.cbs.api.dto.plant.*;
import com.jaagro.cbs.api.service.BreedingPlantService;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author baiyiran
 * @Date 2019/2/21
 */
@RestController
@Slf4j
@Api(description = "养殖户管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreedingPlantController {

    @Autowired
    private BreedingPlantService breedingPlantService;

    /**
     * 养殖场-新增
     *
     * @param plantDto
     * @return
     */
    @ApiOperation("养殖场-新增")
    @PostMapping("/plant")
    public BaseResponse createPlant(@RequestBody CreatePlantDto plantDto) {
        if (StringUtils.isEmpty(plantDto.getPlantName())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场名称不能为空");
        }
        if (StringUtils.isEmpty(plantDto.getPlantType())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场类型不能为空");
        }
        if (StringUtils.isEmpty(plantDto.getDurableYears())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "使用年限不能为空");
        }
        if (StringUtils.isEmpty(plantDto.getExpandable())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "未选择是否可以扩建");
        }
        if (StringUtils.isEmpty(plantDto.getDurableYears())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "截止年份不能为空");
        }
        if (StringUtils.isEmpty(plantDto.getProvince())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "省不能为空");
        }
        if (StringUtils.isEmpty(plantDto.getCity())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "市不能为空");
        }
        if (StringUtils.isEmpty(plantDto.getCounty())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "区不能为空");
        }
        Map<String, Object> result;
        try {
            result = breedingPlantService.createPlant(plantDto);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.errorInstance(e.getMessage());
        }

        return BaseResponse.service(result);
    }

    /**
     * 养殖场-修改
     *
     * @param plantDto
     * @return
     */
    @ApiOperation("养殖场-修改")
    @PutMapping("/plant")
    public BaseResponse updatePlant(@RequestBody UpdatePlantDto plantDto) {
        if (StringUtils.isEmpty(plantDto.getId())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场id不能为空");
        }
        return BaseResponse.service(breedingPlantService.updatePlant(plantDto));
    }

    /**
     * 养殖场-获取单条
     *
     * @param id
     * @return
     */
    @ApiOperation("养殖场-获取单条")
    @GetMapping("/plant/{id}")
    public BaseResponse<ReturnPlantDto> getPlant(@PathVariable("id") Integer id) {
        return BaseResponse.successInstance(breedingPlantService.getPlantDetailsById(id));
    }

    /**
     * 养殖场-通过养殖户id获得养殖场列表
     *
     * @param customerId
     * @return
     */
    @ApiOperation("养殖场-通过养殖户id获得养殖场列表")
    @GetMapping("/listPlantByCustomer/{customerId}")
    public BaseResponse<List<ReturnPlantDto>> listPlantByCustomer(@PathVariable("customerId") Integer customerId) {
        return BaseResponse.successInstance(breedingPlantService.listPlantByCustomerId(customerId));
    }

    /**
     * 鸡舍-新增
     *
     * @param coopDto
     * @return
     */
    @ApiOperation("鸡舍-新增")
    @PostMapping("/coop")
    public BaseResponse createPlant(@RequestBody CreateCoopDto coopDto) {
        if (StringUtils.isEmpty(coopDto.getPlantId())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场id不能为空");
        }
        if (StringUtils.isEmpty(coopDto.getHomeNumber())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "鸡舍名称不能为空");
        }
        if (StringUtils.isEmpty(coopDto.getCapacity())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "可养数量不能为空");
        }
        Map<String, Object> result;
        try {
            result = breedingPlantService.createCoop(coopDto);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.errorInstance(e.getMessage());
        }
        return BaseResponse.service(result);
    }

    /**
     * 鸡舍-通过养殖场id获得列表
     *
     * @param coopDto
     * @return
     */
    @ApiOperation("鸡舍-通过养殖场id获得列表")
    @GetMapping("/coop/{plantId}")
    public BaseResponse<List<ReturnCoopDto>> createPlant(@PathVariable("plantId") Integer plantId) {
        return BaseResponse.successInstance(breedingPlantService.listCoopByPlantId(plantId));
    }

}