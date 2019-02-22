package com.jaagro.cbs.api.enums;

/**
 * @author baiyiran
 * 鸡舍状态
 */

public enum CoopStatusEnum {
    /**
     * 鸡舍状态类型
     */
    MAINTAIN(0, "MAINTAIN", "维护中"),
    LEISURE(1, "LEISURE", "空闲"),
    BREEDING(2, "BREEDING", "饲养中");
    private int code;
    private String type;
    private String desc;

    CoopStatusEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (CoopStatusEnum type : CoopStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (CoopStatusEnum type : CoopStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (CoopStatusEnum type : CoopStatusEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static CoopStatusEnum toEnum(int code) {
        for (CoopStatusEnum type : CoopStatusEnum.values()) {
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
