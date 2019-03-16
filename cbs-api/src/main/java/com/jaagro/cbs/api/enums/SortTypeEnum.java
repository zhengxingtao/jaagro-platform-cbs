package com.jaagro.cbs.api.enums;

/**
 * 排序类型
 * @author yj
 * @date 2019/3/16 11:24
 */
public enum SortTypeEnum {
    /**
     * 排序类型枚举
     */
    UP(1,"UP","向上"),
    DOWN(2,"DOWN","向下");
    private int code;
    private String type;
    private String desc;

    SortTypeEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (SourceTypeEnum type : SourceTypeEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (SourceTypeEnum type : SourceTypeEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (SourceTypeEnum type : SourceTypeEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static SourceTypeEnum toEnum(int code) {
        for (SourceTypeEnum type : SourceTypeEnum.values()) {
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
