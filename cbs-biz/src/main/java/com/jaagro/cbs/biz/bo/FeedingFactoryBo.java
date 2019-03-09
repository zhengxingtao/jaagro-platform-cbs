package com.jaagro.cbs.biz.bo;

import com.jaagro.cbs.api.model.BreedingRecord;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author tonyZheng
 * @date 2019-02-28 10:44
 */
@Data
@Accessors(chain = true)
public class FeedingFactoryBo implements Serializable {

    private int feedingTimes;
    private BigDecimal feedingWeight;
    private List<BreedingRecord> breedingList;
}
