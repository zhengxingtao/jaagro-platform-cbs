package com.jaagro.cbs.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BreedingRecordItemsExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public BreedingRecordItemsExample() {
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

        public Criteria andBreedingRecordIdIsNull() {
            addCriterion("breeding_record_id is null");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdIsNotNull() {
            addCriterion("breeding_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdEqualTo(Integer value) {
            addCriterion("breeding_record_id =", value, "breedingRecordId");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdNotEqualTo(Integer value) {
            addCriterion("breeding_record_id <>", value, "breedingRecordId");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdGreaterThan(Integer value) {
            addCriterion("breeding_record_id >", value, "breedingRecordId");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("breeding_record_id >=", value, "breedingRecordId");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdLessThan(Integer value) {
            addCriterion("breeding_record_id <", value, "breedingRecordId");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("breeding_record_id <=", value, "breedingRecordId");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdIn(List<Integer> values) {
            addCriterion("breeding_record_id in", values, "breedingRecordId");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdNotIn(List<Integer> values) {
            addCriterion("breeding_record_id not in", values, "breedingRecordId");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("breeding_record_id between", value1, value2, "breedingRecordId");
            return (Criteria) this;
        }

        public Criteria andBreedingRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("breeding_record_id not between", value1, value2, "breedingRecordId");
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

        public Criteria andBreedingValueIsNull() {
            addCriterion("breeding_value is null");
            return (Criteria) this;
        }

        public Criteria andBreedingValueIsNotNull() {
            addCriterion("breeding_value is not null");
            return (Criteria) this;
        }

        public Criteria andBreedingValueEqualTo(BigDecimal value) {
            addCriterion("breeding_value =", value, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingValueNotEqualTo(BigDecimal value) {
            addCriterion("breeding_value <>", value, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingValueGreaterThan(BigDecimal value) {
            addCriterion("breeding_value >", value, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("breeding_value >=", value, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingValueLessThan(BigDecimal value) {
            addCriterion("breeding_value <", value, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("breeding_value <=", value, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingValueIn(List<BigDecimal> values) {
            addCriterion("breeding_value in", values, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingValueNotIn(List<BigDecimal> values) {
            addCriterion("breeding_value not in", values, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("breeding_value between", value1, value2, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("breeding_value not between", value1, value2, "breedingValue");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeIsNull() {
            addCriterion("breeding_time is null");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeIsNotNull() {
            addCriterion("breeding_time is not null");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeEqualTo(Date value) {
            addCriterion("breeding_time =", value, "breedingTime");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeNotEqualTo(Date value) {
            addCriterion("breeding_time <>", value, "breedingTime");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeGreaterThan(Date value) {
            addCriterion("breeding_time >", value, "breedingTime");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("breeding_time >=", value, "breedingTime");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeLessThan(Date value) {
            addCriterion("breeding_time <", value, "breedingTime");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeLessThanOrEqualTo(Date value) {
            addCriterion("breeding_time <=", value, "breedingTime");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeIn(List<Date> values) {
            addCriterion("breeding_time in", values, "breedingTime");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeNotIn(List<Date> values) {
            addCriterion("breeding_time not in", values, "breedingTime");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeBetween(Date value1, Date value2) {
            addCriterion("breeding_time between", value1, value2, "breedingTime");
            return (Criteria) this;
        }

        public Criteria andBreedingTimeNotBetween(Date value1, Date value2) {
            addCriterion("breeding_time not between", value1, value2, "breedingTime");
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