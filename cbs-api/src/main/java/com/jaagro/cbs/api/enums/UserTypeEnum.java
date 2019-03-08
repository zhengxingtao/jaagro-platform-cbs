package com.jaagro.cbs.api.enums;

/**
 * @description: 用户类型
 * @author: @Gao.
 * @create: 2019-03-08 16:09
 **/
public enum UserTypeEnum {
    /**
     * 用户类型
     */
    CUSTOMER(1, "CUSTOMER", "客户"),

    EMPLOYEE(2, "EMPLOYEE", "员工"),

    DRIVER(3, "DRIVER", "司机");


    private int code;
    private String type;
    private String desc;

    UserTypeEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (UserTypeEnum type : UserTypeEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (UserTypeEnum type : UserTypeEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (UserTypeEnum type : UserTypeEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static UserTypeEnum toEnum(int code) {
        for (UserTypeEnum type : UserTypeEnum.values()) {
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
