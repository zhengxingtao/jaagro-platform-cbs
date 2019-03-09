package com.jaagro.cbs.api.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 创建养殖计划合同参数
 * @author yj
 * @date 2019/2/25 15:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreatePlanContractDto implements Serializable{
    /**
     * 合同图片列表
     */
    @NotEmpty(message = "{imageUrlList.NotEmpty}")
    private List<String> imageUrlList;

    /**
     * 计划id
     */
    @NotNull(message = "{planId.NotNull}")
    private Integer planId;

    /**
     * 合同开始时间
     */
    @NotNull(message = "{startDate.NotNull}")
    private Date startDate;

    /**
     * 合同结束时间
     */
    @NotNull(message = "{endDate.NotNull}")
    private Date endDate;

    /**
     * 鸡苗数量
     */
    @NotNull(message = "{babychickQuantity.NotNull}")
    private Integer babychickQuantity;

    /**
     * 鸡苗单价
     */
    @NotNull(message = "{babychickPrice.NotNull}")
    @Min(value = 0,message = "{babychickPrice.Min}")
    private BigDecimal babychickPrice;

    /**
     * 付款方式(1-授信,2-预付,3-现金,4-保证金,5-代扣)
     */
    @NotNull(message = "{paymentMethod.NotNull}")
    private Integer paymentMethod;

    /**
     * 预付金额
     */
    @Min(value = 0,message = "{prepaidAmount.Min}")
    private BigDecimal prepaidAmount;

    /**
     * 饲料单价
     */
    @NotNull(message = "{fodderPrice.NotNull}")
    @Min(value = 0,message = "{fodderPrice.Min}")
    private BigDecimal fodderPrice;

    /**
     * 回收保护单价（kg）
     */
    @NotNull(message = "{protectionPrice.NotNull}")
    @Min(value = 0,message = "{protectionPrice.Min}")
    private BigDecimal protectionPrice;

    /**
     * 配送方式(1-系统自动,2-专车专送)
     */
    private Integer deliveryMethod;

    /**
     * 备注
     */
    private String notes;

    /**
     * 回收价格区间列表
     */
    @NotEmpty(message = "{contractPriceSectionDtoList.NotEmpty}")
    private List<ContractPriceSectionDto> contractPriceSectionDtoList;
}
