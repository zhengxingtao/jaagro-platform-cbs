package com.jaagro.cbs.api.enums;

/**
 * 资源类型枚举
 * @author yj
 * @date 2019/3/1 17:44
 */
public enum SourceTypeEnum {
    IMAGE(1,"IMAGE","图片"),
    DOCUMENT(2,"DOCUMENT","文档");
    private int code;
    private String type;
    private String desc;

    SourceTypeEnum(int code, String type, String desc) {
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
