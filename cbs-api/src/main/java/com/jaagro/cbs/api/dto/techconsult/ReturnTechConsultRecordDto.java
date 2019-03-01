package com.jaagro.cbs.api.dto.techconsult;


import com.jaagro.cbs.api.model.Coop;
import com.jaagro.cbs.api.model.Plant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author gavin
 * @Date 20190301
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturnTechConsultRecordDto implements Serializable {
    /**
     * 技术咨询记录表id
     */
    private Integer id;

    /**
     * 养殖户id
     */
    private Integer customerId;

    /**
     * 养殖户名称
     */
    private String customerName;

    /**
     * 客户手机号
     */
    private String customerPhoneNumber;

    /**
     * 计划id
     */
    private Integer planId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 紧急程度(1-一般,2-次要,3-重要,4-紧急)
     */
    private Integer emergencyLevel;

    private String strEmergencyLevel;
    /**E
     * 问题描述
     */
    private String problemDesc;

    /**
     * 技术咨询状态(0-待处理,1-已处理)
     */
    private Integer techConsultStatus;

    private String strTechConsultStatus;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 处理人
     */
    private Integer handleUserId;

    /**
     * 处理描述
     */
    private String handleDesc;

    /**
     * 处理类型(1-电话询问,2-上门查看,3-已经解决,4-暂时搁置)
     */
    private Integer handleType;

    private String strHandleType;

    /**
     * 养殖场列表
     */
    List<Plant> plantList;
    /**
     * 鸡舍列表
     */
    List<Coop> copps;
    /**
     * 养殖场跟对应的鸡舍
     */
    Map<Integer,List<Coop>> plantCoopMap;
    /**
     * 创建时间
     */
    private Date createTime;
}
