package com.jaagro.cbs.api.enums;

/**
 * @author @Gao.
 * 单位
 */

public enum UnitEnum {
    /**
     * 单位
     */
    KG(1, "KG", "千克"),
    AN(2, "AN", "只"),
    ONE(3, "ONE", "个"),
    TONS(4, "TONS", "吨");
    private int code;
    private String type;
    private String desc;

    UnitEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (UnitEnum type : UnitEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (UnitEnum type : UnitEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (UnitEnum type : UnitEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static UnitEnum toEnum(int code) {
        for (UnitEnum type : UnitEnum.values()) {
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
