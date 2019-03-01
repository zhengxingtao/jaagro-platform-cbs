package com.jaagro.cbs.api.enums;

/**
 * @author gavin
 * 技术询问状态枚举
 * 20190228
 */
public enum TechConsultStatusEnum {
    /**
     *  技术咨询状态(0-待处理,1-已处理)
     */

    STATUS_PENDING(0, "STATUS_PENDING", "待处理"),
    STATUS_SOLVED(1, "STATUS_SOLVED", "已处理");


    private int code;
    private String type;
    private String desc;

    TechConsultStatusEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (TechConsultStatusEnum type : TechConsultStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (TechConsultStatusEnum type : TechConsultStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (TechConsultStatusEnum type : TechConsultStatusEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static TechConsultStatusEnum toEnum(int code) {
        for (TechConsultStatusEnum type : TechConsultStatusEnum.values()) {
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
