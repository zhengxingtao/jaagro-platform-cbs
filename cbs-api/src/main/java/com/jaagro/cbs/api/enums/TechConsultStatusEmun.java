package com.jaagro.cbs.api.enums;

/**
 * @author @Gao.
 * 技术支持状态
 */
public enum TechConsultStatusEmun {
    /**
     * 商品类型
     */
    PROCESSED(1, "PROCESSED", "已处理"),
    UNTREATED(3, "UNTREATED", "未处理");

    private int code;
    private String type;
    private String desc;

    TechConsultStatusEmun(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (PlanStatusEnum type : PlanStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (PlanStatusEnum type : PlanStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (PlanStatusEnum type : PlanStatusEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static PlanStatusEnum toEnum(int code) {
        for (PlanStatusEnum type : PlanStatusEnum.values()) {
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
