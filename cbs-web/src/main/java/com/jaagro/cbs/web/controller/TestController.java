package com.jaagro.cbs.web.controller;

import com.jaagro.cbs.api.model.BatchPlantCoop;
import com.jaagro.cbs.api.model.Product;
import com.jaagro.cbs.api.model.ProductExample;
import com.jaagro.cbs.biz.mapper.BatchPlantCoopMapperExt;
import com.jaagro.cbs.biz.mapper.ProductMapperExt;
import com.jaagro.cbs.biz.utils.SequenceCodeUtils;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tonyZheng
 * @date 2019-02-21 14:45
 */
@RestController
public class TestController {

    @Autowired
    private ProductMapperExt productMapperExt;
    @Autowired
    private SequenceCodeUtils sequenceCodeUtils;
    @Autowired
    private BatchPlantCoopMapperExt batchPlantCoopMapper;

    @PostMapping("/createProduct")
    public void createProduct() {
        Product product = new Product();
        product
                .setProductName("毒鼠强")
                .setProductType(1)
                .setManufacturer("卖毒药的");

        productMapperExt.insertSelective(product);
        System.out.println(product.getId());
    }

    @GetMapping("/getProduct")
    public List<Product> getProduct() {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria()
                .andProductNameLike("毒%")
                .andProductTypeEqualTo(1);
        return productMapperExt.selectByExample(productExample);
    }

    @GetMapping("/test1")
    public void test1() {
        String at = sequenceCodeUtils.genSeqCode("AT");
        System.out.println(at);
        return;
    }

    @GetMapping("/testInsertBatchCoop")
    public void testInsertBatchCoop(){
        List<BatchPlantCoop> list = new ArrayList<>();
        BatchPlantCoop coop = new BatchPlantCoop();
        coop.setCreateTime(new Date())
                .setCreateUserId(1)
                .setEnable(Boolean.TRUE)
                .setPlantId(999)
                .setCoopId(999)
                .setPlanId(999);
        BatchPlantCoop coop1 = new BatchPlantCoop();
        BeanUtils.copyProperties(coop,coop1);
        list.add(coop);
        list.add(coop1);
        batchPlantCoopMapper.insertBatch(list);
    }
}
