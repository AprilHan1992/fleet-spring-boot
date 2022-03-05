package com.fleet.mybatis.plus.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author April Han
 */
public enum StatusEnum implements IEnum<Integer> {

    INVALID(0, "无效"),

    VALID(1, "有效");

    StatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;

    @JsonValue
    private String desc;

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
