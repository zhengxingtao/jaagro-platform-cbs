package com.jaagro.cbs.biz.schedule;

import com.jaagro.cbs.api.enums.PlanStatusEnum;
import com.jaagro.cbs.api.enums.PurchaseOrderStatusEnum;
import com.jaagro.cbs.api.model.BreedingPlan;
import com.jaagro.cbs.api.model.PurchaseOrder;
import com.jaagro.cbs.api.model.PurchaseOrderExample;
import com.jaagro.cbs.biz.mapper.BreedingPlanMapperExt;
import com.jaagro.cbs.biz.mapper.PurchaseOrderMapperExt;
import com.jaagro.cbs.biz.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 养殖计划定时
 *
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
    private RedisLock redisLock;

    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void breedingPlanProcess() {
        try {
            //加锁
            long time = System.currentTimeMillis() + 10 * 1000;
            boolean success = redisLock.lock("Scheduled:redisLock:" + "breedingPlanProcess", String.valueOf(time), 5, TimeUnit.MINUTES);
            if (!success) {
                return;
            }
            log.info("S breedingPlanProcess begin");
            long start = System.currentTimeMillis();
            List<BreedingPlan> breedingPlanList = breedingPlanMapper.listToSlaughterConfirm();
            if (!CollectionUtils.isEmpty(breedingPlanList)) {
                for (BreedingPlan breedingPlan : breedingPlanList) {
                    Integer planId = breedingPlan.getId();
                    // 当养殖计划进行到养殖结束日期前7天后并且所有的采购订单都已签收将养殖计划状态改成待出栏确认
                    PurchaseOrderExample purchaseOrderExample = new PurchaseOrderExample();
                    purchaseOrderExample.createCriteria().andPlanIdEqualTo(planId)
                            .andEnableEqualTo(Boolean.TRUE);
                    List<PurchaseOrder> purchaseOrderList = purchaseOrderMapper.selectByExample(purchaseOrderExample);
                    if (!purchaseOrderList.isEmpty()) {
                        boolean allSign = true;
                        for (PurchaseOrder purchaseOrder : purchaseOrderList) {
                            if (PurchaseOrderStatusEnum.ALREADY_SIGNED.getCode() != purchaseOrder.getPurchaseOrderStatus()) {
                                allSign = false;
                            }
                        }
                        if (allSign) {
                            breedingPlan.setPlanStatus(PlanStatusEnum.SLAUGHTER_CONFIRM.getCode());
                            breedingPlan.setModifyUserId(1);
                            breedingPlanMapper.updateByPrimaryKeySelective(breedingPlan);
                        }
                    }

                }
            } else {
                log.info("S breedingPlanProcess there is no plan to update");
            }
            long end = System.currentTimeMillis();
            log.info("S breedingPlanProcess end useTime=" + (end - start));
        } catch (Exception ex) {
            log.info("S breedingPlanProcess error", ex);
        }

    }
}
