package com.jaagro.cbs.api.dto.plant;

import com.jaagro.cbs.api.model.Coop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author baiyiran
 * @Date 2019/2/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturnPlantDto implements Serializable {
    /**
     * 养殖场id
     */
    private Integer id;

    /**
     * 租户id
     */
    private Integer tenantId;

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
     * 是否有效(0-无效,1-有效)
     */
    private Byte enable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private Integer modifyUserId;

    /**
     * 养殖场图片
     */
    private List<ReturnPlantImageDto> plantImageDtoList;

    /**
     * 鸡舍状态
     */
    private Integer coopStatus;

    /**
     * 鸡舍数量
     */
    private Integer coopCount;

    /**
     * 查询鸡舍信息
     */
    private List<ReturnCoopDto> returnCoopDtos;
}
