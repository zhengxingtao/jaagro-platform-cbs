package com.jaagro.cbs.api.dto.plant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author baiyiran
 * @Date 2019/2/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreatePlantDto implements Serializable {
    /**
     * 养殖户id
     */
    private Integer customerId;

    /**
     * 养殖场名称
     */
    private String plantName;

    /**
     * 养殖场类型(1-平养,2-笼养,3-网养)
     */
    private Integer plantType;

    /**
     * 产权情况(1-全民,2-集体,3-私有)
     */
    private String equityType;

    /**
     * 使用年限
     */
    private Integer durableYears;

    /**
     * 是否可以扩建(0-不可扩建,1-可扩建)
     */
    private Boolean expandable;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 养殖场照片
     */
    private List<CreatePlantImageDto> imageDtoList;

}
