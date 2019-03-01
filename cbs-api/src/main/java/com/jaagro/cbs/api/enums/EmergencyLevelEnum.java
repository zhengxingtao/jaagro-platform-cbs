package com.jaagro.cbs.api.enums;

/**
 * @author gavin
 * 技术询问紧急程度枚举
 * 20190228
 */
public enum EmergencyLevelEnum {
    /**
     * 紧急程度(1-一般,2-次要,3-重要,4-紧急)
     */
    EMERGENCY_ONE(1, "EMERGENCY_ONE", "一般"),
    EMERGENCY_SECOND(2, "EMERGENCY_SECOND", "次要"),
    EMERGENCY_THREE(3, "EMERGENCY_THREE", "重要"),
    EMERGENCY_FOUR(4, "EMERGENCY_FOUR", "紧急");



    private int code;
    private String type;
    private String desc;

    EmergencyLevelEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (EmergencyLevelEnum type : EmergencyLevelEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (EmergencyLevelEnum type : EmergencyLevelEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (EmergencyLevelEnum type : EmergencyLevelEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static EmergencyLevelEnum toEnum(int code) {
        for (EmergencyLevelEnum type : EmergencyLevelEnum.values()) {
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
