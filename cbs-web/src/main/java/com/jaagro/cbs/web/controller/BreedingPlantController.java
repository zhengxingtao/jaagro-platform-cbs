package com.jaagro.cbs.web.controller;

import com.jaagro.cbs.api.dto.plant.CreatePlantDto;
import com.jaagro.cbs.api.dto.plant.UpdatePlantDto;
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
        if (StringUtils.isEmpty(plantDto.getPlantType())) {
            return BaseResponse.errorInstance(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "养殖场类型不能为空");
        }
        return BaseResponse.service(breedingPlantService.createPlant(plantDto));
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
    public BaseResponse getPlant(@PathVariable("id") Integer id) {
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
    public BaseResponse listPlantByCustomer(@PathVariable("customerId") Integer customerId) {
        return BaseResponse.successInstance(breedingPlantService.listPlantByCustomerId(customerId));
    }
}
