package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BatchInfoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public BatchInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andStartDayIsNull() {
            addCriterion("start_day is null");
            return (Criteria) this;
        }

        public Criteria andStartDayIsNotNull() {
            addCriterion("start_day is not null");
            return (Criteria) this;
        }

        public Criteria andStartDayEqualTo(Date value) {
            addCriterionForJDBCDate("start_day =", value, "startDay");
            return (Criteria) this;
        }

        public Criteria andStartDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_day <>", value, "startDay");
            return (Criteria) this;
        }

        public Criteria andStartDayGreaterThan(Date value) {
            addCriterionForJDBCDate("start_day >", value, "startDay");
            return (Criteria) this;
        }

        public Criteria andStartDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_day >=", value, "startDay");
            return (Criteria) this;
        }

        public Criteria andStartDayLessThan(Date value) {
            addCriterionForJDBCDate("start_day <", value, "startDay");
            return (Criteria) this;
        }

        public Criteria andStartDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_day <=", value, "startDay");
            return (Criteria) this;
        }

        public Criteria andStartDayIn(List<Date> values) {
            addCriterionForJDBCDate("start_day in", values, "startDay");
            return (Criteria) this;
        }

        public Criteria andStartDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_day not in", values, "startDay");
            return (Criteria) this;
        }

        public Criteria andStartDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_day between", value1, value2, "startDay");
            return (Criteria) this;
        }

        public Criteria andStartDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_day not between", value1, value2, "startDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayIsNull() {
            addCriterion("finish_day is null");
            return (Criteria) this;
        }

        public Criteria andFinishDayIsNotNull() {
            addCriterion("finish_day is not null");
            return (Criteria) this;
        }

        public Criteria andFinishDayEqualTo(Date value) {
            addCriterionForJDBCDate("finish_day =", value, "finishDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("finish_day <>", value, "finishDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayGreaterThan(Date value) {
            addCriterionForJDBCDate("finish_day >", value, "finishDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("finish_day >=", value, "finishDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayLessThan(Date value) {
            addCriterionForJDBCDate("finish_day <", value, "finishDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("finish_day <=", value, "finishDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayIn(List<Date> values) {
            addCriterionForJDBCDate("finish_day in", values, "finishDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("finish_day not in", values, "finishDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("finish_day between", value1, value2, "finishDay");
            return (Criteria) this;
        }

        public Criteria andFinishDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("finish_day not between", value1, value2, "finishDay");
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

        public Criteria andTechnicianIsNull() {
            addCriterion("technician is null");
            return (Criteria) this;
        }

        public Criteria andTechnicianIsNotNull() {
            addCriterion("technician is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicianEqualTo(String value) {
            addCriterion("technician =", value, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianNotEqualTo(String value) {
            addCriterion("technician <>", value, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianGreaterThan(String value) {
            addCriterion("technician >", value, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianGreaterThanOrEqualTo(String value) {
            addCriterion("technician >=", value, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianLessThan(String value) {
            addCriterion("technician <", value, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianLessThanOrEqualTo(String value) {
            addCriterion("technician <=", value, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianLike(String value) {
            addCriterion("technician like", value, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianNotLike(String value) {
            addCriterion("technician not like", value, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianIn(List<String> values) {
            addCriterion("technician in", values, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianNotIn(List<String> values) {
            addCriterion("technician not in", values, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianBetween(String value1, String value2) {
            addCriterion("technician between", value1, value2, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianNotBetween(String value1, String value2) {
            addCriterion("technician not between", value1, value2, "technician");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdIsNull() {
            addCriterion("technician_id is null");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdIsNotNull() {
            addCriterion("technician_id is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdEqualTo(Integer value) {
            addCriterion("technician_id =", value, "technicianId");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdNotEqualTo(Integer value) {
            addCriterion("technician_id <>", value, "technicianId");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdGreaterThan(Integer value) {
            addCriterion("technician_id >", value, "technicianId");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("technician_id >=", value, "technicianId");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdLessThan(Integer value) {
            addCriterion("technician_id <", value, "technicianId");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdLessThanOrEqualTo(Integer value) {
            addCriterion("technician_id <=", value, "technicianId");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdIn(List<Integer> values) {
            addCriterion("technician_id in", values, "technicianId");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdNotIn(List<Integer> values) {
            addCriterion("technician_id not in", values, "technicianId");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdBetween(Integer value1, Integer value2) {
            addCriterion("technician_id between", value1, value2, "technicianId");
            return (Criteria) this;
        }

        public Criteria andTechnicianIdNotBetween(Integer value1, Integer value2) {
            addCriterion("technician_id not between", value1, value2, "technicianId");
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

        public Criteria andFodderValueIsNull() {
            addCriterion("fodder_value is null");
            return (Criteria) this;
        }

        public Criteria andFodderValueIsNotNull() {
            addCriterion("fodder_value is not null");
            return (Criteria) this;
        }

        public Criteria andFodderValueEqualTo(BigDecimal value) {
            addCriterion("fodder_value =", value, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andFodderValueNotEqualTo(BigDecimal value) {
            addCriterion("fodder_value <>", value, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andFodderValueGreaterThan(BigDecimal value) {
            addCriterion("fodder_value >", value, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andFodderValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fodder_value >=", value, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andFodderValueLessThan(BigDecimal value) {
            addCriterion("fodder_value <", value, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andFodderValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fodder_value <=", value, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andFodderValueIn(List<BigDecimal> values) {
            addCriterion("fodder_value in", values, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andFodderValueNotIn(List<BigDecimal> values) {
            addCriterion("fodder_value not in", values, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andFodderValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fodder_value between", value1, value2, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andFodderValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fodder_value not between", value1, value2, "fodderValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueIsNull() {
            addCriterion("babychick_value is null");
            return (Criteria) this;
        }

        public Criteria andBabychickValueIsNotNull() {
            addCriterion("babychick_value is not null");
            return (Criteria) this;
        }

        public Criteria andBabychickValueEqualTo(BigDecimal value) {
            addCriterion("babychick_value =", value, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueNotEqualTo(BigDecimal value) {
            addCriterion("babychick_value <>", value, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueGreaterThan(BigDecimal value) {
            addCriterion("babychick_value >", value, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("babychick_value >=", value, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueLessThan(BigDecimal value) {
            addCriterion("babychick_value <", value, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("babychick_value <=", value, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueIn(List<BigDecimal> values) {
            addCriterion("babychick_value in", values, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueNotIn(List<BigDecimal> values) {
            addCriterion("babychick_value not in", values, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("babychick_value between", value1, value2, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andBabychickValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("babychick_value not between", value1, value2, "babychickValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueIsNull() {
            addCriterion("drug_value is null");
            return (Criteria) this;
        }

        public Criteria andDrugValueIsNotNull() {
            addCriterion("drug_value is not null");
            return (Criteria) this;
        }

        public Criteria andDrugValueEqualTo(BigDecimal value) {
            addCriterion("drug_value =", value, "drugValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueNotEqualTo(BigDecimal value) {
            addCriterion("drug_value <>", value, "drugValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueGreaterThan(BigDecimal value) {
            addCriterion("drug_value >", value, "drugValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("drug_value >=", value, "drugValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueLessThan(BigDecimal value) {
            addCriterion("drug_value <", value, "drugValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("drug_value <=", value, "drugValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueIn(List<BigDecimal> values) {
            addCriterion("drug_value in", values, "drugValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueNotIn(List<BigDecimal> values) {
            addCriterion("drug_value not in", values, "drugValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("drug_value between", value1, value2, "drugValue");
            return (Criteria) this;
        }

        public Criteria andDrugValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("drug_value not between", value1, value2, "drugValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueIsNull() {
            addCriterion("sale_value is null");
            return (Criteria) this;
        }

        public Criteria andSaleValueIsNotNull() {
            addCriterion("sale_value is not null");
            return (Criteria) this;
        }

        public Criteria andSaleValueEqualTo(BigDecimal value) {
            addCriterion("sale_value =", value, "saleValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueNotEqualTo(BigDecimal value) {
            addCriterion("sale_value <>", value, "saleValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueGreaterThan(BigDecimal value) {
            addCriterion("sale_value >", value, "saleValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_value >=", value, "saleValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueLessThan(BigDecimal value) {
            addCriterion("sale_value <", value, "saleValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_value <=", value, "saleValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueIn(List<BigDecimal> values) {
            addCriterion("sale_value in", values, "saleValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueNotIn(List<BigDecimal> values) {
            addCriterion("sale_value not in", values, "saleValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_value between", value1, value2, "saleValue");
            return (Criteria) this;
        }

        public Criteria andSaleValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_value not between", value1, value2, "saleValue");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
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