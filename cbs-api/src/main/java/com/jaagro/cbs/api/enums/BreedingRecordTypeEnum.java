package com.jaagro.cbs.api.enums;

/**
 * @author gavin
 * 喂养类型枚举类
 */

public enum BreedingRecordTypeEnum {
    /**
     * 喂养类型 1-投料 2-喂药 3-通风 4-死淘 5-喂水
     */
    FEED_FOOD(1, "FEED_FOOD", "喂料"),
    FEED_MEDICINE(2, "FEED_MEDICINE", "喂药"),
    AIR_WIND(3, "AIR_WIND", "通风"),
    DEATH_AMOUNT(4, "DEATH_AMOUNT", "死淘"),
    FEED_WATER(5, "FEED_WATER", "喂水");
    private int code;
    private String type;
    private String desc;

    BreedingRecordTypeEnum(int code, String type, String desc) {
        this.code = code;
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (BreedingRecordTypeEnum type : BreedingRecordTypeEnum.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }

    public static String getTypeByCode(int code) {
        for (BreedingRecordTypeEnum type : BreedingRecordTypeEnum.values()) {
            if (type.getCode() == code) {
                return type.getType();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        for (BreedingRecordTypeEnum type : BreedingRecordTypeEnum.values()) {
            if (type.getDesc().equalsIgnoreCase(desc)) {
                return type.getCode();
            }
        }
        return null;
    }

    public static BreedingRecordTypeEnum toEnum(int code) {
        for (BreedingRecordTypeEnum type : BreedingRecordTypeEnum.values()) {
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
