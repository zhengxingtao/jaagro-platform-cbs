package com.jaagro.cbs.api.enums;

/**
 * @author gavin
 * 产品的容量单位
 */

public enum CapacityUnitEnum {
    /**
     * 容量单位(1-ml,2-g)
     */
    ML(1, "ml", "毫升"),
    G(2, "g", "克");
    private int code;
    private String type;
    private String desc;

    CapacityUnitEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (CapacityUnitEnum type : CapacityUnitEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (CapacityUnitEnum type : CapacityUnitEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (CapacityUnitEnum type : CapacityUnitEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static CapacityUnitEnum toEnum(int code) {
        for (CapacityUnitEnum type : CapacityUnitEnum.values()) {
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
