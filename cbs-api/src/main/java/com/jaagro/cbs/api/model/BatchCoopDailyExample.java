package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatchCoopDailyExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public BatchCoopDailyExample() {
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

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("batch_no like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("batch_no not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andCoopIdIsNull() {
            addCriterion("coop_id is null");
            return (Criteria) this;
        }

        public Criteria andCoopIdIsNotNull() {
            addCriterion("coop_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoopIdEqualTo(Integer value) {
            addCriterion("coop_id =", value, "coopId");
            return (Criteria) this;
        }

        public Criteria andCoopIdNotEqualTo(Integer value) {
            addCriterion("coop_id <>", value, "coopId");
            return (Criteria) this;
        }

        public Criteria andCoopIdGreaterThan(Integer value) {
            addCriterion("coop_id >", value, "coopId");
            return (Criteria) this;
        }

        public Criteria andCoopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coop_id >=", value, "coopId");
            return (Criteria) this;
        }

        public Criteria andCoopIdLessThan(Integer value) {
            addCriterion("coop_id <", value, "coopId");
            return (Criteria) this;
        }

        public Criteria andCoopIdLessThanOrEqualTo(Integer value) {
            addCriterion("coop_id <=", value, "coopId");
            return (Criteria) this;
        }

        public Criteria andCoopIdIn(List<Integer> values) {
            addCriterion("coop_id in", values, "coopId");
            return (Criteria) this;
        }

        public Criteria andCoopIdNotIn(List<Integer> values) {
            addCriterion("coop_id not in", values, "coopId");
            return (Criteria) this;
        }

        public Criteria andCoopIdBetween(Integer value1, Integer value2) {
            addCriterion("coop_id between", value1, value2, "coopId");
            return (Criteria) this;
        }

        public Criteria andCoopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coop_id not between", value1, value2, "coopId");
            return (Criteria) this;
        }

        public Criteria andDayAgeIsNull() {
            addCriterion("day_age is null");
            return (Criteria) this;
        }

        public Criteria andDayAgeIsNotNull() {
            addCriterion("day_age is not null");
            return (Criteria) this;
        }

        public Criteria andDayAgeEqualTo(Integer value) {
            addCriterion("day_age =", value, "dayAge");
            return (Criteria) this;
        }

        public Criteria andDayAgeNotEqualTo(Integer value) {
            addCriterion("day_age <>", value, "dayAge");
            return (Criteria) this;
        }

        public Criteria andDayAgeGreaterThan(Integer value) {
            addCriterion("day_age >", value, "dayAge");
            return (Criteria) this;
        }

        public Criteria andDayAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_age >=", value, "dayAge");
            return (Criteria) this;
        }

        public Criteria andDayAgeLessThan(Integer value) {
            addCriterion("day_age <", value, "dayAge");
            return (Criteria) this;
        }

        public Criteria andDayAgeLessThanOrEqualTo(Integer value) {
            addCriterion("day_age <=", value, "dayAge");
            return (Criteria) this;
        }

        public Criteria andDayAgeIn(List<Integer> values) {
            addCriterion("day_age in", values, "dayAge");
            return (Criteria) this;
        }

        public Criteria andDayAgeNotIn(List<Integer> values) {
            addCriterion("day_age not in", values, "dayAge");
            return (Criteria) this;
        }

        public Criteria andDayAgeBetween(Integer value1, Integer value2) {
            addCriterion("day_age between", value1, value2, "dayAge");
            return (Criteria) this;
        }

        public Criteria andDayAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("day_age not between", value1, value2, "dayAge");
            return (Criteria) this;
        }

        public Criteria andStartAmountIsNull() {
            addCriterion("start_amount is null");
            return (Criteria) this;
        }

        public Criteria andStartAmountIsNotNull() {
            addCriterion("start_amount is not null");
            return (Criteria) this;
        }

        public Criteria andStartAmountEqualTo(Integer value) {
            addCriterion("start_amount =", value, "startAmount");
            return (Criteria) this;
        }

        public Criteria andStartAmountNotEqualTo(Integer value) {
            addCriterion("start_amount <>", value, "startAmount");
            return (Criteria) this;
        }

        public Criteria andStartAmountGreaterThan(Integer value) {
            addCriterion("start_amount >", value, "startAmount");
            return (Criteria) this;
        }

        public Criteria andStartAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_amount >=", value, "startAmount");
            return (Criteria) this;
        }

        public Criteria andStartAmountLessThan(Integer value) {
            addCriterion("start_amount <", value, "startAmount");
            return (Criteria) this;
        }

        public Criteria andStartAmountLessThanOrEqualTo(Integer value) {
            addCriterion("start_amount <=", value, "startAmount");
            return (Criteria) this;
        }

        public Criteria andStartAmountIn(List<Integer> values) {
            addCriterion("start_amount in", values, "startAmount");
            return (Criteria) this;
        }

        public Criteria andStartAmountNotIn(List<Integer> values) {
            addCriterion("start_amount not in", values, "startAmount");
            return (Criteria) this;
        }

        public Criteria andStartAmountBetween(Integer value1, Integer value2) {
            addCriterion("start_amount between", value1, value2, "startAmount");
            return (Criteria) this;
        }

        public Criteria andStartAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("start_amount not between", value1, value2, "startAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountIsNull() {
            addCriterion("current_amount is null");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountIsNotNull() {
            addCriterion("current_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountEqualTo(Integer value) {
            addCriterion("current_amount =", value, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountNotEqualTo(Integer value) {
            addCriterion("current_amount <>", value, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountGreaterThan(Integer value) {
            addCriterion("current_amount >", value, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_amount >=", value, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountLessThan(Integer value) {
            addCriterion("current_amount <", value, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountLessThanOrEqualTo(Integer value) {
            addCriterion("current_amount <=", value, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountIn(List<Integer> values) {
            addCriterion("current_amount in", values, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountNotIn(List<Integer> values) {
            addCriterion("current_amount not in", values, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountBetween(Integer value1, Integer value2) {
            addCriterion("current_amount between", value1, value2, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("current_amount not between", value1, value2, "currentAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountIsNull() {
            addCriterion("dead_amount is null");
            return (Criteria) this;
        }

        public Criteria andDeadAmountIsNotNull() {
            addCriterion("dead_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDeadAmountEqualTo(Integer value) {
            addCriterion("dead_amount =", value, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountNotEqualTo(Integer value) {
            addCriterion("dead_amount <>", value, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountGreaterThan(Integer value) {
            addCriterion("dead_amount >", value, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("dead_amount >=", value, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountLessThan(Integer value) {
            addCriterion("dead_amount <", value, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountLessThanOrEqualTo(Integer value) {
            addCriterion("dead_amount <=", value, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountIn(List<Integer> values) {
            addCriterion("dead_amount in", values, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountNotIn(List<Integer> values) {
            addCriterion("dead_amount not in", values, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountBetween(Integer value1, Integer value2) {
            addCriterion("dead_amount between", value1, value2, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andDeadAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("dead_amount not between", value1, value2, "deadAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIsNull() {
            addCriterion("sale_amount is null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIsNotNull() {
            addCriterion("sale_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountEqualTo(Integer value) {
            addCriterion("sale_amount =", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotEqualTo(Integer value) {
            addCriterion("sale_amount <>", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountGreaterThan(Integer value) {
            addCriterion("sale_amount >", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_amount >=", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountLessThan(Integer value) {
            addCriterion("sale_amount <", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountLessThanOrEqualTo(Integer value) {
            addCriterion("sale_amount <=", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIn(List<Integer> values) {
            addCriterion("sale_amount in", values, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotIn(List<Integer> values) {
            addCriterion("sale_amount not in", values, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountBetween(Integer value1, Integer value2) {
            addCriterion("sale_amount between", value1, value2, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_amount not between", value1, value2, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andFodderTimesIsNull() {
            addCriterion("fodder_times is null");
            return (Criteria) this;
        }

        public Criteria andFodderTimesIsNotNull() {
            addCriterion("fodder_times is not null");
            return (Criteria) this;
        }

        public Criteria andFodderTimesEqualTo(Integer value) {
            addCriterion("fodder_times =", value, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderTimesNotEqualTo(Integer value) {
            addCriterion("fodder_times <>", value, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderTimesGreaterThan(Integer value) {
            addCriterion("fodder_times >", value, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("fodder_times >=", value, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderTimesLessThan(Integer value) {
            addCriterion("fodder_times <", value, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderTimesLessThanOrEqualTo(Integer value) {
            addCriterion("fodder_times <=", value, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderTimesIn(List<Integer> values) {
            addCriterion("fodder_times in", values, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderTimesNotIn(List<Integer> values) {
            addCriterion("fodder_times not in", values, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderTimesBetween(Integer value1, Integer value2) {
            addCriterion("fodder_times between", value1, value2, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("fodder_times not between", value1, value2, "fodderTimes");
            return (Criteria) this;
        }

        public Criteria andFodderAmountIsNull() {
            addCriterion("fodder_amount is null");
            return (Criteria) this;
        }

        public Criteria andFodderAmountIsNotNull() {
            addCriterion("fodder_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFodderAmountEqualTo(BigDecimal value) {
            addCriterion("fodder_amount =", value, "fodderAmount");
            return (Criteria) this;
        }

        public Criteria andFodderAmountNotEqualTo(BigDecimal value) {
            addCriterion("fodder_amount <>", value, "fodderAmount");
            return (Criteria) this;
        }

        public Criteria andFodderAmountGreaterThan(BigDecimal value) {
            addCriterion("fodder_amount >", value, "fodderAmount");
            return (Criteria) this;
        }

        public Criteria andFodderAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fodder_amount >=", value, "fodderAmount");
            return (Criteria) this;
        }

        public Criteria andFodderAmountLessThan(BigDecimal value) {
            addCriterion("fodder_amount <", value, "fodderAmount");
            return (Criteria) this;
        }

        public Criteria andFodderAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fodder_amount <=", value, "fodderAmount");
            return (Criteria) this;
        }

        public Criteria andFodderAmountIn(List<BigDecimal> values) {
            addCriterion("fodder_amount in", values, "fodderAmount");
            return (Criteria) this;
        }

        public Criteria andFodderAmountNotIn(List<BigDecimal> values) {
            addCriterion("fodder_amount not in", values, "fodderAmount");
            return (Criteria) this;
        }

        public Criteria andFodderAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fodder_amount between", value1, value2, "fodderAmount");
            return (Criteria) this;
        }

        public Criteria andFodderAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fodder_amount not between", value1, value2, "fodderAmount");
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

        public Criteria andEnableEqualTo(Byte value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(Byte value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(Byte value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Byte value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(Byte value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(Byte value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<Byte> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<Byte> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(Byte value1, Byte value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(Byte value1, Byte value2) {
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