package com.jaagro.cbs.api.enums;

/**
 * @author @Gao.
 * 采购订单状态
 */
public enum PurchaseOrderStatusEnum {
    /**
     * 采购订单状态
     */
    TO_AUDIT(0, "TO_AUDIT", "待审核"),
    APPROVED(1, "APPROVED", "审核通过"),
    DELIVERY(2, "DELIVERY", "已完成送货"),
    AUDIT_REFUSED(3, "AUDIT_REFUSED", "审核拒绝");

    private int code;
    private String type;
    private String desc;

    PurchaseOrderStatusEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (PlanStatusEnum type : PlanStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (PlanStatusEnum type : PlanStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (PlanStatusEnum type : PlanStatusEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static PlanStatusEnum toEnum(int code) {
        for (PlanStatusEnum type : PlanStatusEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
