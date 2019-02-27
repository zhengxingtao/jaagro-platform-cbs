package com.jaagro.cbs.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaagro.cbs.api.dto.product.ListProductCriteria;
import com.jaagro.cbs.api.model.Product;
import com.jaagro.cbs.api.service.ProductService;
import com.jaagro.cbs.biz.mapper.ProductMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yj
 * @date 2019/2/27 11:31
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapperExt productMapper;
    /**
     * 根据条件查询产品
     *
     * @param criteria
     * @return
     */
    @Override
    public PageInfo listByCriteria(ListProductCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(),criteria.getPageSize());
        List<Product> productList = productMapper.listByCriteria(criteria);
        return new PageInfo(productList);
    }
}
