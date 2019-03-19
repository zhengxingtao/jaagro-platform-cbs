package com.jaagro.cbs.biz.schedule;

import com.jaagro.cbs.api.enums.PlanStatusEnum;
import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.model.BreedingPlanExample;
import com.jaagro.cbs.api.service.BreedingPlanService;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.mapper.PurchaseOrderMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 养殖计划定时
 * @author yj
 * @date 2019/3/18 17:30
 */
@Service
@Slf4j
public class BreedingPlanScheduleService {
    @Autowired
    private BreedingPlanMapperExt breedingPlanMapper;
    @Autowired
    private PurchaseOrderMapperExt purchaseOrderMapper;
    @Autowired
    private BreedingPlanService breedingPlanService;
    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void breedingPlanProcess(){
        try {
            log.info("S breedingPlanProcess begin");
            long start = System.currentTimeMillis();
            BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
            breedingPlanExample.createCriteria().andEnableEqualTo(Boolean.TRUE)
                    .andPlanStatusEqualTo(PlanStatusEnum.BREEDING.getCode());
            List<BreedingPlan> breedingPlanList = breedingPlanMapper.selectByExample(breedingPlanExample);
            if (!CollectionUtils.isEmpty(breedingPlanList)){
                for (BreedingPlan breedingPlan : breedingPlanList){
                    Integer planId = breedingPlan.getId();
                    int dayAge = (int)breedingPlanService.getDayAge(planId);
                    // 当养殖计划进行到养殖结束日期前7天后并且所有的采购订单都已签收将养殖计划状态改成待出栏确认
                    if (dayAge >= breedingPlan.getBreedingDays()-7){

                    }
                }
            }else {
                log.info("S breedingPlanProcess there is no plan to update");
            }
            long end = System.currentTimeMillis();
            log.info("S breedingPlanProcess end useTime="+(end-start));
        }catch (Exception ex){
            log.info("S breedingPlanProcess error",ex);
        }

    }
}
