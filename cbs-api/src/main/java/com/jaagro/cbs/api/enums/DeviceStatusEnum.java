package com.jaagro.cbs.api.enums;

/**
 * @author gavin
 * 鸡舍设备状态
 */

public enum DeviceStatusEnum {
    /**
     * 设备状态
     */
    DEMOLISH(0, "DEMOLISH", "拆除"),
    NORMAL(1, "NORMAL", "正常"),
    MAINTAIN(2, "MAINTAIN", "维护、维修中");
    private int code;
    private String type;
    private String desc;

    DeviceStatusEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (DeviceStatusEnum type : DeviceStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (DeviceStatusEnum type : DeviceStatusEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (DeviceStatusEnum type : DeviceStatusEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static DeviceStatusEnum toEnum(int code) {
        for (DeviceStatusEnum type : DeviceStatusEnum.values()) {
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

    public static void main(String[] args) {
        System.out.println(DeviceStatusEnum.NORMAL.getCode());
    }
}
