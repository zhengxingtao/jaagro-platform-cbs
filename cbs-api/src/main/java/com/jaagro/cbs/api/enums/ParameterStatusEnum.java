package com.jaagro.cbs.api.enums;

/**
 * 参数状态
 * @author yj
 * @date 2019/3/2 15:36
 */
public enum ParameterStatusEnum {
    UN_USEFUL(0,"UN_USEFUL","未启用"),
    USEFUL(1,"USEFUL","启用");
    private int code;
    private String type;
    private String desc;

    ParameterStatusEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (ParameterStatusEnum type : ParameterStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (ParameterStatusEnum type : ParameterStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (ParameterStatusEnum type : ParameterStatusEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static ParameterStatusEnum toEnum(int code) {
        for (ParameterStatusEnum type : ParameterStatusEnum.values()) {
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
