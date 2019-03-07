package com.jaagro.cbs.api.enums;

/**
 * @author @Gao.
 * 采购订单状态
 */
public enum PurchaseOrderPhaseEnum {
    /**
     * 订单阶段：1-第一阶段；2-第二阶段；3-第三阶段
     */
    PHASE_ONE(1, "PHASE_ONE", "第一阶段"),
    PHASE_TWO(2, "PHASE_TWO", "第二阶段"),
    PHASE_THREE(3, "PHASE_THREE", "第三阶段");

    private int code;
    private String type;
    private String desc;

    PurchaseOrderPhaseEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (PurchaseOrderPhaseEnum type : PurchaseOrderPhaseEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (PurchaseOrderPhaseEnum type : PurchaseOrderPhaseEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (PurchaseOrderPhaseEnum type : PurchaseOrderPhaseEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static PurchaseOrderPhaseEnum toEnum(int code) {
        for (PurchaseOrderPhaseEnum type : PurchaseOrderPhaseEnum.values()) {
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
