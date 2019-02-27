package com.jaagro.cbs.api.dto.progress;

import com.jaagro.cbs.api.model.Coop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author :Gavin
 * @date :2019/02/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreedingProgressDto implements Serializable {


    private static final long serialVersionUID = 9140829806877500166L;
    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 租户id
     */
    private Integer tenantId;

    /**
     * 养殖户id
     */
    private Integer customerId;

    /**
     * 养殖户名称
     */
    private String customerName;

    /**
     * 养殖户联系人电话
     */
    private String contactPhone;

    /**
     * 养殖户详细地址
     */
    private String customerAddress;

    /**
     * 养殖天数
     */
    private Integer breedingDays;
    /**
     * 计划状态(0-待录入合同,1-待参数纠偏,2-待上鸡签收,3-养殖中,4-待出栏确认,5-已完成,6-已取消)
     */
    private Integer planStatus;

    /**
     * 计划上鸡数量
     */
    private Integer planChickenQuantity;
    /**
     * 存栏量
     */
    private BigDecimal livingChickenQuantity;

    /**
     * 上鸡时间
     */
    private Date planTime;
    /**
     *鸡舍位置:养殖厂对应的鸡舍信息
     */

    private List<Coop> coops;

}
