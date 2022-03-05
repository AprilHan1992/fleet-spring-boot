package com.fleet.excel.annotation;

import com.fleet.excel.util.value.Values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author han.pengpeng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelColumn {

    /**
     * 导出到 Excel 中的表头
     */
    String name();

    /**
     * 配置列号，对应 A,B,C,D....
     */
    String column();

    /**
     * 设置某字段转换结果值
     */
    Class<? extends Values> values() default Values.class;

    /**
     * 时间格式(如果字段类型为date，时间格式可以随意，如果字段类型为string，请保持时间格式一致)
     */
    String dateFormat() default "";

    /**
     * 是否导出数据，应对需求：有些列不导出数据
     */
    boolean isExport() default true;
}
