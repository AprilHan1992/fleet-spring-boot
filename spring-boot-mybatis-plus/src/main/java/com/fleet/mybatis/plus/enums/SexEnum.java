package com.fleet.mybatis.plus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author April Han
 */
public enum SexEnum {

    WOMAN(0, "女"),

    MAN(1, "男");

    SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
