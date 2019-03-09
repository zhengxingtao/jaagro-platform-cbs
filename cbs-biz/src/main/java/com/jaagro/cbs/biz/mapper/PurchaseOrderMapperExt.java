package com.jaagro.cbs.biz.mapper;

import javax.annotation.Resource;

import com.jaagro.cbs.api.dto.order.PurchaseOrderParamDto;
import com.jaagro.cbs.api.dto.order.ReturnPurchaseOrderStatisticalDto;
import com.jaagro.cbs.api.model.PurchaseOrder;
import com.jaagro.cbs.api.model.PurchaseOrderExample;
import com.jaagro.cbs.biz.bo.PurchaseOrderBo;
import com.jaagro.cbs.biz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


/**
 * PurchaseOrderMapperExt接口
 *Ø
 * @author :generator
 * @date :2019/2/21
 */
@Resource
public interface PurchaseOrderMapperExt extends BaseMapper<PurchaseOrder, PurchaseOrderExample> {

    /**
     * 根据不同商品类型 统计不同类型值
     *
     * @param dto
     * @return
     */
    BigDecimal calculateTotalPlanFeedWeight(PurchaseOrderParamDto dto);

    /**
     * 查询要删除的订单
     * @param orderBo
     * @return
     */
    List<Integer> queryPurchaseOrderByCondition(PurchaseOrderBo orderBo);

}