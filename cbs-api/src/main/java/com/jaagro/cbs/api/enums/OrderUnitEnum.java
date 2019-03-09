package com.jaagro.cbs.api.enums;

/**
 * @author @Gao.
 * purchase_order的订单单位
 */

public enum OrderUnitEnum {
    /**
     * 单位(1-千克｜2-只｜3-个｜ 4-吨等)
     */
    KG(1, "KG", "千克"),
    AN(2, "AN", "只"),
    GE(3, "GE", "个"),
    TONS(4, "TONS", "吨");
    private int code;
    private String type;
    private String desc;

    OrderUnitEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (OrderUnitEnum type : OrderUnitEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (OrderUnitEnum type : OrderUnitEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (OrderUnitEnum type : OrderUnitEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static OrderUnitEnum toEnum(int code) {
        for (OrderUnitEnum type : OrderUnitEnum.values()) {
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
