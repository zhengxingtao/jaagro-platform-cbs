package com.jaagro.cbs.api.enums;

/**
 * @author gavin
 * 产品product的包装单位
 */

public enum PackageUnitEnum {
    /**
     * 包装单位(1-瓶,2-袋,3-只,4-桶,5-盒,6-吨)
     */
    BOTTLE(1, "BOTTLE", "瓶"),
    BAG(2, "BAG", "袋"),
    PIECE(3, "PIECE", "只"),
    BARREL(4, "BARREL", "桶"),
    BOX(5, "BOX", "盒"),
    TONS(6, "TONS", "吨");
    private int code;
    private String type;
    private String desc;

    PackageUnitEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (PackageUnitEnum type : PackageUnitEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (PackageUnitEnum type : PackageUnitEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (PackageUnitEnum type : PackageUnitEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static PackageUnitEnum toEnum(int code) {
        for (PackageUnitEnum type : PackageUnitEnum.values()) {
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
