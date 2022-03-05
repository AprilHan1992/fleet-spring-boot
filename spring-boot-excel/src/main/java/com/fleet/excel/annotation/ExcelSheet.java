package com.fleet.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author April Han
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ExcelSheet {

    /**
     * 模板文件
     */
    String template() default "";

    /**
     * 读取指定 sheet 中数据
     */
    int sheetAt() default 0;

    /**
     * 表头行(行下标)
     */
    int headAt() default 0;

    /**
     * 读取数据起始行(行下标)
     */
    int startWith() default 1;
}
