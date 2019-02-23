package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :tony
 * @date :2019/02/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ContractSource implements Serializable {
    /**
     * 计划合同资源表id
     */
    private Integer id;

    /**
     * 计划id
     */
    private Integer planId;

    /**
     * 计划合同表id
     */
    private Integer planContractId;

    /**
     * 资质类型(1-合同首页 2-合同签字页 3-合同报价页)
     */
    private Integer certificateType;

    /**
     * 资质图片地址
     */
    private String certificateImageUrl;

    /**
     * 证件状态(0-未审核 1-正常 2-审核未通过 3-停止合作)
     */
    private Integer certificateStatus;

    /**
     * 备注
     */
    private String notes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createUserId;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 更新人
     */
    private Integer modifyUserId;

    private static final long serialVersionUID = 1L;
}