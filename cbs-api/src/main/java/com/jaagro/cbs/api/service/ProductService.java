package com.jaagro.cbs.api.service;

import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.product.ListProductCriteria;
import com.jaagro.cbs.api.model.Product;

import java.util.List;

/**
 * 产品管理
 *
 * @author yj
 * @date 2019/2/27 11:11
 */
public interface ProductService {
    /**
     * 根据条件查询产品
     *
     * @param criteria
     * @return
     */
    PageInfo listByCriteria(ListProductCriteria criteria);
}
