package com.jaagro.cbs.api.enums;

/**
 * @author @Gao.
 * 采购订单状态
 */
public enum PurchaseOrderStatusEnum {
    /**
     * 采购订单状态 状态(1-已下单 ,2－待送达 ,3-已签收)
     */
    ORDER_PLACED(1, "ORDER_PLACED", "已下单"),
    PENDING_ARRIVED(2, "PENDING_ARRIVED", "待送达"),
    ALREADY_SIGNED(3, "ALREADY_SIGNED", "已签收");

    private int code;
    private String type;
    private String desc;

    PurchaseOrderStatusEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (PurchaseOrderStatusEnum type : PurchaseOrderStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (PurchaseOrderStatusEnum type : PurchaseOrderStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (PurchaseOrderStatusEnum type : PurchaseOrderStatusEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static PurchaseOrderStatusEnum toEnum(int code) {
        for (PurchaseOrderStatusEnum type : PurchaseOrderStatusEnum.values()) {
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
