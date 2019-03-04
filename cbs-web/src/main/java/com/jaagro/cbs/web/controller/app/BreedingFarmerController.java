package com.jaagro.cbs.web.controller.app;

import com.jaagro.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @Gao.
 */
@RestController
@Api(description = "农户端养殖管理", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class BreedingFarmerController {

    @PostMapping("/breedingFarmerIndex")
    @ApiOperation("农户端首页")
    public BaseResponse breedingFarmerIndex() {
        return BaseResponse.successInstance("参数配置成功");
    }
}
