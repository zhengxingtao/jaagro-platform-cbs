package com.jaagro.cbs.api.enums;

/**
 * @author @Gao.
 * 养殖模板数值类型
 */
public enum BreedingStandardValueTypeEnum {
    /**
     * 养殖参数状态
     * 1-区间值,2-标准值,3-临界值
     */
    INTERVAL_VALUE(1, "INTERVAL_VALUE", "区间值"),
    STANDARD_VALUE(2, "STANDARD_VALUE", "标准值"),
    CRITICAL_VALUE(3, "CRITICAL_VALUE", "临界值");
    private int code;
    private String type;
    private String desc;

    BreedingStandardValueTypeEnum(int code, String type, String desc) {
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
