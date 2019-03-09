package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BreedingStandardDrugExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public BreedingStandardDrugExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdIsNull() {
            addCriterion("breeding_standard_id is null");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdIsNotNull() {
            addCriterion("breeding_standard_id is not null");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdEqualTo(Integer value) {
            addCriterion("breeding_standard_id =", value, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdNotEqualTo(Integer value) {
            addCriterion("breeding_standard_id <>", value, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdGreaterThan(Integer value) {
            addCriterion("breeding_standard_id >", value, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("breeding_standard_id >=", value, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdLessThan(Integer value) {
            addCriterion("breeding_standard_id <", value, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdLessThanOrEqualTo(Integer value) {
            addCriterion("breeding_standard_id <=", value, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdIn(List<Integer> values) {
            addCriterion("breeding_standard_id in", values, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdNotIn(List<Integer> values) {
            addCriterion("breeding_standard_id not in", values, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdBetween(Integer value1, Integer value2) {
            addCriterion("breeding_standard_id between", value1, value2, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andBreedingStandardIdNotBetween(Integer value1, Integer value2) {
            addCriterion("breeding_standard_id not between", value1, value2, "breedingStandardId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andSkuNoIsNull() {
            addCriterion("sku_no is null");
            return (Criteria) this;
        }

        public Criteria andSkuNoIsNotNull() {
            addCriterion("sku_no is not null");
            return (Criteria) this;
        }

        public Criteria andSkuNoEqualTo(String value) {
            addCriterion("sku_no =", value, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoNotEqualTo(String value) {
            addCriterion("sku_no <>", value, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoGreaterThan(String value) {
            addCriterion("sku_no >", value, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoGreaterThanOrEqualTo(String value) {
            addCriterion("sku_no >=", value, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoLessThan(String value) {
            addCriterion("sku_no <", value, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoLessThanOrEqualTo(String value) {
            addCriterion("sku_no <=", value, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoLike(String value) {
            addCriterion("sku_no like", value, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoNotLike(String value) {
            addCriterion("sku_no not like", value, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoIn(List<String> values) {
            addCriterion("sku_no in", values, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoNotIn(List<String> values) {
            addCriterion("sku_no not in", values, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoBetween(String value1, String value2) {
            addCriterion("sku_no between", value1, value2, "skuNo");
            return (Criteria) this;
        }

        public Criteria andSkuNoNotBetween(String value1, String value2) {
            addCriterion("sku_no not between", value1, value2, "skuNo");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartIsNull() {
            addCriterion("day_age_start is null");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartIsNotNull() {
            addCriterion("day_age_start is not null");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartEqualTo(Integer value) {
            addCriterion("day_age_start =", value, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartNotEqualTo(Integer value) {
            addCriterion("day_age_start <>", value, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartGreaterThan(Integer value) {
            addCriterion("day_age_start >", value, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_age_start >=", value, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartLessThan(Integer value) {
            addCriterion("day_age_start <", value, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartLessThanOrEqualTo(Integer value) {
            addCriterion("day_age_start <=", value, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartIn(List<Integer> values) {
            addCriterion("day_age_start in", values, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartNotIn(List<Integer> values) {
            addCriterion("day_age_start not in", values, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartBetween(Integer value1, Integer value2) {
            addCriterion("day_age_start between", value1, value2, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeStartNotBetween(Integer value1, Integer value2) {
            addCriterion("day_age_start not between", value1, value2, "dayAgeStart");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndIsNull() {
            addCriterion("day_age_end is null");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndIsNotNull() {
            addCriterion("day_age_end is not null");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndEqualTo(Integer value) {
            addCriterion("day_age_end =", value, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndNotEqualTo(Integer value) {
            addCriterion("day_age_end <>", value, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndGreaterThan(Integer value) {
            addCriterion("day_age_end >", value, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_age_end >=", value, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndLessThan(Integer value) {
            addCriterion("day_age_end <", value, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndLessThanOrEqualTo(Integer value) {
            addCriterion("day_age_end <=", value, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndIn(List<Integer> values) {
            addCriterion("day_age_end in", values, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndNotIn(List<Integer> values) {
            addCriterion("day_age_end not in", values, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndBetween(Integer value1, Integer value2) {
            addCriterion("day_age_end between", value1, value2, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andDayAgeEndNotBetween(Integer value1, Integer value2) {
            addCriterion("day_age_end not between", value1, value2, "dayAgeEnd");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagIsNull() {
            addCriterion("stop_drug_flag is null");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagIsNotNull() {
            addCriterion("stop_drug_flag is not null");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagEqualTo(Boolean value) {
            addCriterion("stop_drug_flag =", value, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagNotEqualTo(Boolean value) {
            addCriterion("stop_drug_flag <>", value, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagGreaterThan(Boolean value) {
            addCriterion("stop_drug_flag >", value, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("stop_drug_flag >=", value, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagLessThan(Boolean value) {
            addCriterion("stop_drug_flag <", value, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("stop_drug_flag <=", value, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagIn(List<Boolean> values) {
            addCriterion("stop_drug_flag in", values, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagNotIn(List<Boolean> values) {
            addCriterion("stop_drug_flag not in", values, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("stop_drug_flag between", value1, value2, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andStopDrugFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("stop_drug_flag not between", value1, value2, "stopDrugFlag");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeIsNull() {
            addCriterion("feed_volume is null");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeIsNotNull() {
            addCriterion("feed_volume is not null");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeEqualTo(BigDecimal value) {
            addCriterion("feed_volume =", value, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeNotEqualTo(BigDecimal value) {
            addCriterion("feed_volume <>", value, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeGreaterThan(BigDecimal value) {
            addCriterion("feed_volume >", value, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("feed_volume >=", value, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeLessThan(BigDecimal value) {
            addCriterion("feed_volume <", value, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("feed_volume <=", value, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeIn(List<BigDecimal> values) {
            addCriterion("feed_volume in", values, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeNotIn(List<BigDecimal> values) {
            addCriterion("feed_volume not in", values, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("feed_volume between", value1, value2, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andFeedVolumeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("feed_volume not between", value1, value2, "feedVolume");
            return (Criteria) this;
        }

        public Criteria andDaysIsNull() {
            addCriterion("days is null");
            return (Criteria) this;
        }

        public Criteria andDaysIsNotNull() {
            addCriterion("days is not null");
            return (Criteria) this;
        }

        public Criteria andDaysEqualTo(Integer value) {
            addCriterion("days =", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotEqualTo(Integer value) {
            addCriterion("days <>", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThan(Integer value) {
            addCriterion("days >", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("days >=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThan(Integer value) {
            addCriterion("days <", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThanOrEqualTo(Integer value) {
            addCriterion("days <=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysIn(List<Integer> values) {
            addCriterion("days in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotIn(List<Integer> values) {
            addCriterion("days not in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysBetween(Integer value1, Integer value2) {
            addCriterion("days between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("days not between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(Boolean value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(Boolean value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(Boolean value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(Boolean value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<Boolean> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<Boolean> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("enable not between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIsNull() {
            addCriterion("modify_user_id is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIsNotNull() {
            addCriterion("modify_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdEqualTo(Integer value) {
            addCriterion("modify_user_id =", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotEqualTo(Integer value) {
            addCriterion("modify_user_id <>", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdGreaterThan(Integer value) {
            addCriterion("modify_user_id >", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("modify_user_id >=", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdLessThan(Integer value) {
            addCriterion("modify_user_id <", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("modify_user_id <=", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIn(List<Integer> values) {
            addCriterion("modify_user_id in", values, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotIn(List<Integer> values) {
            addCriterion("modify_user_id not in", values, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdBetween(Integer value1, Integer value2) {
            addCriterion("modify_user_id between", value1, value2, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("modify_user_id not between", value1, value2, "modifyUserId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}